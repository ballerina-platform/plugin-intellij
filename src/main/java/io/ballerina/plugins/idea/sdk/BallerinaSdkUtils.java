/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.ballerina.plugins.idea.sdk;

import com.intellij.util.SlowOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Contains util classes related to Ballerina SDK.
 *
 * @since 2.0.0
 */
public class BallerinaSdkUtils {

    private static final String BAL_VERSION_CMD = "bal -v";
    private static final String BAL_HOME_CMD = "bal home";
    private static final String BAL_NAME = "Ballerina";
    private static final String WINDOWS_BAL_EXECUTABLE = "bal.bat";
    private static final String UNIX_BAL_EXECUTABLE = "bal";
    private static final String BAL_EXECUTABLE_DIR = "bin";
    private static final String BAL_DIST_DIR_NAME_START = "ballerina-";
    private static final String BAL_WINDOWS_ENV_VARIABLE = "BALLERINA_HOME";
    private static final String WINDOWS_BAL_DIST_DIR_NAME = "Program Files";
    private static final String UNIX_BAL_DIST_DIR_NAME_FIRST = "usr";
    private static final String UNIX_BAL_DIST_DIR_NAME_SECOND = "lib";
    private static final String BAL_DIST_DIR_NAME = "distributions";
    private static final String MAC_BAL_DIST_DIR_NAME = "Library";
    private static final String WINDOWS_BAL_DRIVE = "C:";

    private static Optional<String> runCommand(String cmd) {
        SlowOperations.assertSlowOperationsAreAllowed();
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (OSUtils.isWindows()) {
            processBuilder.command("cmd.exe", "/c", cmd);
        } else {
            processBuilder.command("sh", "-c", cmd);
        }

        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return Optional.empty();
            }
            return line == null || line.isEmpty() ? Optional.empty() : Optional.of(line);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static Optional<String> runCommandWithDirectory(String dir, String cmd) {
        SlowOperations.assertSlowOperationsAreAllowed();
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(new File(dir));
        if (OSUtils.isWindows()) {
            processBuilder.command("cmd.exe", "/c", cmd);
        } else {
            processBuilder.command("sh", "-c", cmd);
        }

        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return Optional.empty();
            }
            return line == null || line.isEmpty() ? Optional.empty() : Optional.of(line);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<String> getBallerinaVersion() {
        try {
            Optional<String> version = runCommand(BAL_VERSION_CMD);
            if (version.isPresent()) {
                return version;
            }
            Optional<String> balBinPath = getBalBinBatPath();
            if (balBinPath.isPresent()) {
                return runCommandWithDirectory(balBinPath.get(), BAL_VERSION_CMD);
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<String> getBallerinaPath() {
        try {
            Optional<String> version = runCommand(BAL_VERSION_CMD);
            if (version.isEmpty()) {
                Optional<String> balBinPath = getBalBinBatPath();
                if (balBinPath.isPresent()) {
                    version = runCommandWithDirectory(balBinPath.get(), BAL_VERSION_CMD);
                } else {
                    return Optional.empty();
                }
            }
            if (version.isEmpty()) {
                return version;
            }
            if (OSUtils.isWindows()) {
                Map<String, String> env = System.getenv();
                String [] versionParts = version.get().split(" ");
                if (versionParts.length < 2) {
                    return Optional.empty();
                }
                for (Map.Entry<String, String> entry : env.entrySet()) {
                    if (Objects.equals(entry.getKey(), BAL_WINDOWS_ENV_VARIABLE)) {
                        String path = entry.getValue();
                        path = Paths.get(path, BAL_DIST_DIR_NAME, BAL_DIST_DIR_NAME_START
                                                + version.get().split(" ")[1],
                                        BAL_EXECUTABLE_DIR, WINDOWS_BAL_EXECUTABLE)
                                .normalize().toString();
                        return Optional.of(path);
                    }
                }
                return Optional.empty();
            } else {
                Optional<String> home = runCommand(BAL_HOME_CMD);
                if (home.isEmpty()) {
                    Optional<String> balBinPath = getBalBinBatPath();
                    if (balBinPath.isPresent()) {
                        home = runCommandWithDirectory(balBinPath.get(), BAL_HOME_CMD);
                    } else {
                        return Optional.empty();
                    }
                }
                return home.map(s -> Paths.get(s, BAL_EXECUTABLE_DIR, UNIX_BAL_EXECUTABLE).normalize().toString());
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static boolean isValidPath(String path) {
        try {
            if (path == null || path.isEmpty()) {
                return false;
            }
            path = BallerinaSdkUtils.getNormalizedPath(path);
            File file = new File(path);
            if (!file.exists()) {
                return false;
            }

            String executableName = file.getName();

            if ((OSUtils.isWindows() && !executableName.equals(WINDOWS_BAL_EXECUTABLE)) ||
                    (!OSUtils.isWindows() && !executableName.equals(UNIX_BAL_EXECUTABLE))) {
                return false;
            }

            return file.canExecute();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidVersion(String version) {
        return version != null && !version.isEmpty();
    }

    public static boolean isValidSdk(String path, String version) {
        return isValidPath(path) && isValidVersion(version);
    }

    public static String getVersionFromPath(String path) {
        try {
            if (isValidPath(path)) {
                path = BallerinaSdkUtils.getNormalizedPath(path);
                String version = Paths.get(path).getParent().getParent().getFileName().toString();
                version = version.replace('b', 'B').replace('-', ' ');
                String [] parts = version.split("\\.");
                if (parts.length < 2) {
                    return EMPTY;
                }
                String update = version.split("\\.")[1];
                version = version + " (Swan Lake Update " + update + ")";
                return isValidVersion(version) ? version : EMPTY;
            }
            return EMPTY;
        } catch (Exception e) {
            return EMPTY;
        }
    }

    public static List<BallerinaSdk> getBallerinaSdks(String ballerinaPath) {
        List<BallerinaSdk> sdkList = new ArrayList<>();
        try {
            if (isValidPath(ballerinaPath)) {
                ballerinaPath = BallerinaSdkUtils.getNormalizedPath(ballerinaPath);
                File sdkDir = new File(ballerinaPath);
                File distRoot = sdkDir.getParentFile().getParentFile().getParentFile();
                File[] files = distRoot.listFiles((current, name) -> new File(current, name).isDirectory() &&
                        name.startsWith(BAL_DIST_DIR_NAME_START));
                if (files != null) {
                    for (File file : files) {
                        String version = file.getName()
                                .replace('b', 'B').replace('-', ' ');
                        String update = version.split("\\.")[1];
                        version = version + " (Swan Lake Update " + update + ")";
                        String executableName = OSUtils.isWindows() ? WINDOWS_BAL_EXECUTABLE : UNIX_BAL_EXECUTABLE;
                        Path sdkPath =
                                Paths.get(file.getAbsolutePath(), BAL_EXECUTABLE_DIR, executableName).normalize();
                        if (isValidSdk(sdkPath.toString(), version)) {
                            sdkList.add(new BallerinaSdk(sdkPath.toString(), version));
                        }
                    }
                }
            }
            return sdkList;
        } catch (Exception e) {
            return sdkList;
        }
    }

    public static String findBalDistFolder(String initialPath) {
        try {
            File current = new File(Paths.get(initialPath).normalize().toString());

            while (current != null) {
                if (current.getName().toLowerCase().contains(BAL_DIST_DIR_NAME_START)) {
                    return BallerinaSdkUtils.getNormalizedPath(current.getAbsolutePath());
                }
                current = current.getParentFile();
            }

            return EMPTY;
        } catch (Exception e) {
            return EMPTY;
        }
    }

    public static String getBalBatFromDist(String distPath) {
        try {
            Path path = Paths.get(distPath).normalize();
            String lastElement = path.getFileName().toString();

            String executableName = OSUtils.isWindows() ? WINDOWS_BAL_EXECUTABLE : UNIX_BAL_EXECUTABLE;

            if (BAL_EXECUTABLE_DIR.equals(lastElement)) {
                return path.resolve(executableName).toString();
            } else {
                return path.resolve(Paths.get(BAL_EXECUTABLE_DIR, executableName)).toString();
            }
        } catch (Exception e) {
            return EMPTY;
        }
    }

    public static String getNormalizedPath(String path) {
        try {
            if (path.isEmpty()) {
                return EMPTY;
            }
            return Paths.get(path).normalize().toString();
        } catch (Exception e) {
            return EMPTY;
        }
    }

    private static Optional<String> getBalBinPathMac() {
        try {
            String path = Paths.get(MAC_BAL_DIST_DIR_NAME, BAL_NAME, BAL_EXECUTABLE_DIR).normalize().toString();
            if (new File(path).exists()) {
                return Optional.of(path);
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static Optional<String> getBalBinPathWindows() {
        try {
            String path =
                    Paths.get(WINDOWS_BAL_DRIVE, WINDOWS_BAL_DIST_DIR_NAME, BAL_NAME, BAL_EXECUTABLE_DIR).normalize()
                            .toString();
            if (new File(path).exists()) {
                return Optional.of(path);
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static Optional<String> getBalBinPathUnix() {
        try {
            String path =
                    Paths.get(UNIX_BAL_DIST_DIR_NAME_FIRST, UNIX_BAL_DIST_DIR_NAME_SECOND, BAL_NAME, BAL_EXECUTABLE_DIR)
                            .normalize().toString();
            if (new File(path).exists()) {
                return Optional.of(path);
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<String> getBalBinBatPath() {
        if (OSUtils.isWindows()) {
            return getBalBinPathWindows();
        } else if (OSUtils.isMac()) {
            return getBalBinPathMac();
        } else if (OSUtils.isUnix()) {
            return getBalBinPathUnix();
        }
        return Optional.empty();
    }

    public static class BallerinaSdk {

        private final String path;
        private final String version;

        public BallerinaSdk(String path, String version) {
            this.path = path;
            this.version = version;
        }

        public String getPath() {
            return path;
        }

        public String getVersion() {
            return version;
        }
    }
}
