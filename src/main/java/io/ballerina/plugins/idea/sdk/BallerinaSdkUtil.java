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
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Contains util classes related to Ballerina SDK.
 *
 * @since 2.0.0
 */
public class BallerinaSdkUtil {

    private static String balVersionCmd = "bal -v";
    private static String balHomeCmd = "bal home";
    private static String windowsBallerinaExecutable = "bal.bat";
    private static String unixBalExecutable = "bal";
    private static String balExecutableFolder = "bin";
    private static String balDistFolderNameStart = "ballerina-";
    private static String balWindowsEnvVariable = "BALLERINA_HOME";

    private static String runCommand(String cmd) {
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
                return "";
            }
            return line;
        } catch (IOException | InterruptedException e) {
            return "";
        }
    }

    public static String getBallerinaVersion() {
        String version = runCommand(balVersionCmd);
        return version == null ? "" : version;
    }

    public static String getBallerinaPath() {
        String version = runCommand(balVersionCmd);
        if (Objects.equals(version, "")) {
            return "";
        }
        if (OSUtils.isWindows()) {
            Map<String, String> env = System.getenv();

            for (Map.Entry<String, String> entry : env.entrySet()) {
                if (Objects.equals(entry.getKey(), balWindowsEnvVariable)) {
                    String pth = entry.getValue();
                    pth = Paths.get(pth, "distributions", balDistFolderNameStart
                                    + version.split(" ")[1], balExecutableFolder, windowsBallerinaExecutable)
                            .toString();
                    return pth;
                }
            }
            return "";
        } else {
            return Paths.get(runCommand(balHomeCmd), balExecutableFolder, unixBalExecutable).toString();
        }
    }

    private static String getVersionNumber(String version) {
        if (isValidVersion(version)) {
            return version.split("[ \\-]")[1];
        }
        return "";
    }

    public static boolean areEqualVersions(String version1, String version2) {
        if (!isValidVersion(version1) || !isValidVersion(version2)) {
            return false;
        }
        version1 = getVersionNumber(version1);
        version2 = getVersionNumber(version2);
        return Objects.equals(version1, version2);
    }

    public static boolean isValidPath(String path) {
        if (path == null | path.isEmpty()) {
            return false;
        }
        path = Paths.get(path).normalize().toString();
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }

        String executableName = file.getName();

        if ((OSUtils.isWindows() && !executableName.equals(windowsBallerinaExecutable)) ||
                (!OSUtils.isWindows() && !executableName.equals(unixBalExecutable))) {
            return false;
        }

        return file.canExecute();
    }

    public static boolean isValidVersion(String version) {
        return version != null && !version.isEmpty();
    }

    public static boolean isValidSdk(String path, String version) {
        return isValidPath(path) && isValidVersion(version);
    }

    public static String getVersionFromPath(String path) {
        if (isValidPath(path)) {
            String version = Paths.get(path).getParent().getParent().getFileName().toString();
            version = version.replace('b', 'B').replace('-', ' ');
            String [] parts = version.split("\\.");
            if (parts.length < 2) {
                return "";
            }
            String update = version.split("\\.")[1];
            version = version + " (Swan Lake Update " + update + ")";
            return isValidVersion(version) ? version : "";
        }
        return "";
    }

    public static List<BallerinaSdk> getBallerinaSdks(String ballerinaPath) {
        List<BallerinaSdk> sdkList = new ArrayList<>();
        if (isValidPath(ballerinaPath)) {
            File sdkDir = new File(ballerinaPath);
            File distRoot = sdkDir.getParentFile().getParentFile().getParentFile();
            File[] files = distRoot.listFiles(
                    (current, name) -> new File(current, name).isDirectory()
                            && name.startsWith(balDistFolderNameStart));
            if (files != null) {
                for (File file : files) {
                    String version = file.getName().replace('b', 'B').replace('-', ' ');
                    String update = version.split("\\.")[1];
                    version = version + " (Swan Lake Update " + update + ")";
                    String executableName = OSUtils.isWindows() ? windowsBallerinaExecutable : unixBalExecutable;
                    Path sdkPath = Paths.get(file.getAbsolutePath(), balExecutableFolder, executableName);
                    if (isValidSdk(sdkPath.toString(), version)) {
                        sdkList.add(new BallerinaSdk(sdkPath.toString(), version));
                    }
                }
            }
        }
        return sdkList;
    }

    public static String findBalDistFolder(String initialPath) {
        Path currentPath = Paths.get(initialPath);
        while (currentPath != null) {
            if (currentPath.getFileName().toString().toLowerCase().contains(balDistFolderNameStart)) {
                return currentPath.normalize().toString();
            }
            currentPath = currentPath.getParent();
        }
        return "";
    }

    public static String getBalBatFromDist(String distPath) {
        Path path = Paths.get(distPath).normalize();
        String lastElement = path.getFileName().toString();

        String executableName = OSUtils.isWindows() ? windowsBallerinaExecutable : unixBalExecutable;

        if (balExecutableFolder.equals(lastElement)) {
            return path.resolve(executableName).toString();
        } else {
            return path.resolve(Paths.get(balExecutableFolder, executableName)).toString();
        }
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
