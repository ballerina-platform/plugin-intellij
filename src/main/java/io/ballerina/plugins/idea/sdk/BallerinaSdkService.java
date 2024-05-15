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

import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import io.ballerina.plugins.idea.notification.BallerinaPluginNotifier;

import java.util.List;

import static io.ballerina.plugins.idea.BallerinaConstants.EMPTY_STRING;

/**
 * Acts as an API for sdk path and version retrieval in all places.
 *
 * @since 2.0.0
 */
public class BallerinaSdkService {

    private static BallerinaSdkService instance;
    private String ballerinaVersion;
    private String ballerinaPath;
    private boolean notified = false;
    private final List<BallerinaSdk> sdkList;

    private BallerinaSdkService() {
        // This code will only run once when the IDE starts and the first access to BallerinaSdkService occurs
        ballerinaPath = BallerinaSdkUtils.getBallerinaPath().orElse(EMPTY_STRING);
        ballerinaVersion = BallerinaSdkUtils.getBallerinaVersion().orElse(EMPTY_STRING);
        if (!BallerinaSdkUtils.isValidSdk(ballerinaPath, ballerinaVersion)) {
            ballerinaVersion = EMPTY_STRING;
            ballerinaPath = EMPTY_STRING;
        }
        sdkList = BallerinaSdkUtils.getBallerinaSdks(ballerinaPath);
    }

    public static synchronized BallerinaSdkService getInstance() {
        if (instance == null) {
            instance = new BallerinaSdkService();
        }
        return instance;
    }

    public String getBallerinaVersion(Project project) {
        BallerinaSdkSettings settings = BallerinaSdkSettings.getInstance();
        if (settings != null && settings.isUseCustomSdk()) {
            String version = settings.getBallerinaSdkVersion();
            String sdkPath = settings.getBallerinaSdkPath();
            if ((version.isEmpty() && sdkPath.isEmpty())
                    || (BallerinaSdkUtils.isValidSdk(sdkPath, version)
                    && BallerinaSdkUtils.getVersionFromPath(getBallerinaPath(project)).equals(version))) {
                return version;
            }
        }
        return ballerinaVersion;
    }

    public String getBallerinaPath(Project project) {
        BallerinaSdkSettings settings = BallerinaSdkSettings.getInstance();
        boolean wrongSettingsPath = false;
        if (settings != null  && settings.isUseCustomSdk()) {
            String sdkPath = settings.getBallerinaSdkPath();
            String version = settings.getBallerinaSdkVersion();
            if ((sdkPath.isEmpty() && version.isEmpty()) || BallerinaSdkUtils.isValidSdk(sdkPath, version)) {
                return sdkPath;
            } else {
                wrongSettingsPath = true;
            }
        }
        if (wrongSettingsPath && !notified && BallerinaSdkUtils.isValidSdk(ballerinaPath, ballerinaVersion)) {
            notified = true;
            BallerinaPluginNotifier.customNotification(project,
                    NotificationType.INFORMATION,
                    "Invalid Ballerina SDK path",
                    "The Ballerina SDK path set in the settings is invalid. " +
                            "Using the system Ballerina SDK path.");
            BallerinaSdkSettings.getInstance().setUseCustomSdk(false);
        }
        return ballerinaPath;
    }

    public String getSystemBalPath() {
        return ballerinaPath;
    }

    public String getSystemBalVersion() {
        return ballerinaVersion;
    }

    public List<BallerinaSdk> getSdkList() {
        return sdkList;
    }
}
