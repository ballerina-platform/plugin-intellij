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

import com.intellij.openapi.diagnostic.Logger;
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

import static io.ballerina.plugins.idea.BallerinaConstants.BAL_LOG_PREFIX;
import static io.ballerina.plugins.idea.BallerinaConstants.EMPTY_STRING;

/**
 * Contains util classes related to Ballerina SDK.
 *
 * @since 2.0.0
 */
public class BallerinaSdkUtils {

    private static final Logger LOG = Logger.getInstance(BallerinaSdkUtils.class);
    private static final String BAL_VERSION_CMD = "bal -v";
    private static final String BAL_HOME_CMD = "bal home";
    private static final String BAL_NAME = "Ballerina";
    private static final String WINDOWS_BAL_EXECUTABLE = "bal.bat";
    private static final String UNIX_BAL_EXECUTABLE = "bal";
    private static final String BIN_DIR = "bin";
    private static final String BAL_DIST_DIR_NAME_START = "ballerina-";
    private static final String BAL_WINDOWS_ENV_VARIABLE = "BALLERINA_HOME";
    private static final String WINDOWS_BAL_DIST_DIR_NAME = "Program Files";
    private static final String UNIX_USR_DIR = "/usr";
    private static final String UNIX_LIB_DIR = "lib";
    private static final String BAL_DIST_DIR_NAME = "distributions";
    private static final String MAC_LIBRARY_DIR = "/Library";
    private static final String WINDOWS_C_DRIVE = "C:";

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
            LOG.info(BAL_LOG_PREFIX + " '" + cmd + "' command output: " + line);
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                LOG.info(BAL_LOG_PREFIX + " '" + cmd + "' Exit code: " + exitCode);
                return Optional.empty();
            }
            return line == null || line.isEmpty() ? Optional.empty() : Optional.of(line);
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + " '" + cmd + "' Error occurred while running the command: ", e);
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
            LOG.info(BAL_LOG_PREFIX + " '" + dir + " '" + cmd + "' command output: " + line);
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                LOG.info(BAL_LOG_PREFIX + " '" + dir + " '" + cmd + "' Exit code: " + exitCode);
                return Optional.empty();
            }
            return line == null || line.isEmpty() ? Optional.empty() : Optional.of(line);
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + " '" + cmd + "' Error occurred while running the command: ", e);
            return Optional.empty();
        }
    }

    public static Optional<String> getBallerinaVersion() {
        try {
            Optional<String> version = runCommand(BAL_VERSION_CMD);
            if (version.isPresent()) {
                LOG.info(BAL_LOG_PREFIX + " Ballerina version: " + version.get());
                return version;
            }
            Optional<String> balBinPath = getDefaultInstallationPath();
            if (balBinPath.isPresent()) {
                LOG.info(BAL_LOG_PREFIX + " Ballerina bin path: " + balBinPath.get());
                return runCommandWithDirectory(balBinPath.get(), BAL_VERSION_CMD);
            }
            LOG.info(BAL_LOG_PREFIX + "Ballerina version is not found");
            return Optional.empty();
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + "Error occurred while getting the Ballerina version: ", e);
            return Optional.empty();
        }
    }

    public static Optional<String> getBallerinaPath() {
        try {
            Optional<String> version = runCommand(BAL_VERSION_CMD);
            if (version.isEmpty()) {
                Optional<String> balBinPath = getDefaultInstallationPath();
                if (balBinPath.isPresent()) {
                    version = runCommandWithDirectory(balBinPath.get(), BAL_VERSION_CMD);
                    LOG.info(BAL_LOG_PREFIX + "Ballerina version: " + version.orElse(EMPTY_STRING));
                } else {
                    LOG.info(BAL_LOG_PREFIX + "Ballerina bin path is not found");
                    return Optional.empty();
                }
            }
            if (version.isEmpty()) {
                LOG.info(BAL_LOG_PREFIX + "Ballerina version is not found");
                return version;
            }
            if (OSUtils.isWindows()) {
                Map<String, String> env = System.getenv();
                String [] versionParts = version.get().split(" ");
                if (versionParts.length < 2) {
                    LOG.info(BAL_LOG_PREFIX + "Ballerina version is not found: " + version.get());
                    return Optional.empty();
                }
                for (Map.Entry<String, String> entry : env.entrySet()) {
                    if (Objects.equals(entry.getKey(), BAL_WINDOWS_ENV_VARIABLE)) {
                        String path = entry.getValue();
                        path = Paths.get(path, BAL_DIST_DIR_NAME, BAL_DIST_DIR_NAME_START
                                                + version.get().split(" ")[1],
                                        BIN_DIR, WINDOWS_BAL_EXECUTABLE)
                                .normalize().toString();
                        LOG.info(BAL_LOG_PREFIX + "Ballerina path: " + path);
                        return Optional.of(path);
                    }
                }
                LOG.info(BAL_LOG_PREFIX + "Ballerina path is not found");
                return Optional.empty();
            } else {
                Optional<String> home = runCommand(BAL_HOME_CMD);
                if (home.isEmpty()) {
                    Optional<String> balBinPath = getDefaultInstallationPath();
                    if (balBinPath.isPresent()) {
                        LOG.info(BAL_LOG_PREFIX + "Ballerina bin path: " + balBinPath.get());
                        home = runCommandWithDirectory(balBinPath.get(), BAL_HOME_CMD);
                    } else {
                        LOG.info(BAL_LOG_PREFIX + "Ballerina bin path is not found");
                        return Optional.empty();
                    }
                }
                LOG.info(BAL_LOG_PREFIX + "Ballerina home: " + home.orElse(EMPTY_STRING));
                return home.map(s -> Paths.get(s, BIN_DIR, UNIX_BAL_EXECUTABLE).normalize().toString());
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static boolean isValidPath(String path) {
        try {
            if (path == null || path.isEmpty()) {
                LOG.info(BAL_LOG_PREFIX + "Ballerina path is empty: " + path);
                return false;
            }
            path = BallerinaSdkUtils.getNormalizedPath(path);
            File file = new File(path);
            if (!file.exists()) {
                LOG.info(BAL_LOG_PREFIX + "Ballerina path does not exist: " + path);
                return false;
            }

            String executableName = file.getName();

            if ((OSUtils.isWindows() && !executableName.equals(WINDOWS_BAL_EXECUTABLE)) ||
                    (!OSUtils.isWindows() && !executableName.equals(UNIX_BAL_EXECUTABLE))) {
                LOG.info(BAL_LOG_PREFIX + "Ballerina executable is not found: " + path);
                return false;
            }

            return file.canExecute();
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + "Error occurred while validating the Ballerina path: " + path, e);
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
                    LOG.info(BAL_LOG_PREFIX + "Ballerina version is not found for the path: '" + path
                            + "' Version: " + version);
                    return EMPTY_STRING;
                }
                String update = version.split("\\.")[1];
                version = version + " (Swan Lake Update " + update + ")";
                return isValidVersion(version) ? version : EMPTY_STRING;
            }
            LOG.info(BAL_LOG_PREFIX + "Ballerina path is not valid: " + path);
            return EMPTY_STRING;
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + "Error occurred while getting the Ballerina version from the path: "
                    + path, e);
            return EMPTY_STRING;
        }
    }

    public static List<ballerinaSdk> getBallerinaSdks(String ballerinaPath) {
        List<ballerinaSdk> sdkList = new ArrayList<>();
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
                                Paths.get(file.getAbsolutePath(), BIN_DIR, executableName).normalize();
                        if (isValidSdk(sdkPath.toString(), version)) {
                            sdkList.add(new ballerinaSdk(sdkPath.toString(), version));
                        }
                    }
                }
            }
            return sdkList;
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + "Error occurred while getting the Ballerina SDKs from the path: "
                    + ballerinaPath, e);
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
            LOG.info(BAL_LOG_PREFIX + "Ballerina distribution folder is not found: " + initialPath);
            return EMPTY_STRING;
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + "Error occurred while finding the Ballerina distribution folder: "
                    + initialPath, e);
            return EMPTY_STRING;
        }
    }

    public static String getBalBatFromDist(String distPath) {
        try {
            Path path = Paths.get(distPath).normalize();
            String lastElement = path.getFileName().toString();

            String executableName = OSUtils.isWindows() ? WINDOWS_BAL_EXECUTABLE : UNIX_BAL_EXECUTABLE;

            if (BIN_DIR.equals(lastElement)) {
                return path.resolve(executableName).toString();
            } else {
                return path.resolve(Paths.get(BIN_DIR, executableName)).toString();
            }
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + "Error occurred while getting the Ballerina bat from the distribution: "
                    + distPath, e);
            return EMPTY_STRING;
        }
    }

    public static String getNormalizedPath(String path) {
        try {
            if (path.isEmpty()) {
                LOG.info(BAL_LOG_PREFIX + " Path is empty: " + path);
                return EMPTY_STRING;
            }
            return Paths.get(path).normalize().toString();
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + "Error occurred while normalizing the path: " + path, e);
            return EMPTY_STRING;
        }
    }

    private static Optional<String> getDefaultInstallationPathMac() {
        try {
            String path = Paths.get(MAC_LIBRARY_DIR, BAL_NAME, BIN_DIR).normalize().toString();
            if (new File(path).exists()) {
                LOG.info(BAL_LOG_PREFIX + "Ballerina bin path: " + path);
                return Optional.of(path);
            }
            LOG.info(BAL_LOG_PREFIX + "Ballerina bin path is not found: " + path);
            return Optional.empty();
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + "Error occurred while getting the Ballerina bin path for Mac: ", e);
            return Optional.empty();
        }
    }

    private static Optional<String> getDefaultInstallationPathWindows() {
        try {
            String path =
                    Paths.get(WINDOWS_C_DRIVE, WINDOWS_BAL_DIST_DIR_NAME, BAL_NAME, BIN_DIR)
                            .normalize().toString();
            if (new File(path).exists()) {
                LOG.info(BAL_LOG_PREFIX + "Ballerina bin path: " + path);
                return Optional.of(path);
            }
            LOG.info(BAL_LOG_PREFIX + "Ballerina bin path is not found: " + path);
            return Optional.empty();
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + "Error occurred while getting the Ballerina bin path for Windows: ", e);
            return Optional.empty();
        }
    }

    private static Optional<String> getDefaultInstallationPathUnix() {
        try {
            String path =
                    Paths.get(UNIX_USR_DIR, UNIX_LIB_DIR, BAL_NAME, BIN_DIR)
                            .normalize().toString();
            if (new File(path).exists()) {
                LOG.info(BAL_LOG_PREFIX + "Ballerina bin path: " + path);
                return Optional.of(path);
            }
            LOG.info(BAL_LOG_PREFIX + "Ballerina bin path is not found: " + path);
            return Optional.empty();
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + "Error occurred while getting the Ballerina bin path for Unix: ", e);
            return Optional.empty();
        }
    }

    public static Optional<String> getDefaultInstallationPath() {
        if (OSUtils.isWindows()) {
            return getDefaultInstallationPathWindows();
        } else if (OSUtils.isMac()) {
            return getDefaultInstallationPathMac();
        } else if (OSUtils.isUnix()) {
            return getDefaultInstallationPathUnix();
        }
        return Optional.empty();
    }

    public record ballerinaSdk(String path, String version) {

    }
}
