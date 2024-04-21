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
import io.ballerina.plugins.idea.notification.BallerinaNotification;

import java.util.List;

/**
 * Acts as an API for sdk path and version retrieval in all places.
 *
 * @since 2.0.0
 */
public class BallerinaSdkService {

    private static BallerinaSdkService instance;
    private final String ballerinaVersion;
    private final String ballerinaPath;
    private boolean notified = false;

    private final List<BallerinaSdkUtil.BallerinaSdk> sdkList;

    private BallerinaSdkService() {
        // This code will only run once when the IDE starts and the first access to BallerinaSdkService occurs
        ballerinaVersion = BallerinaSdkUtil.getBallerinaVersion();
        ballerinaPath = BallerinaSdkUtil.getBallerinaPath();
        sdkList = BallerinaSdkUtil.getBallerinaSdks(ballerinaPath);
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
            if (BallerinaSdkUtil.isValidVersion(version)
                    && BallerinaSdkUtil.getVersionFromPath(getBallerinaPath(project)).equals(version)) {
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
            if (BallerinaSdkUtil.isValidPath(sdkPath)) {
                return sdkPath;
            } else {
                wrongSettingsPath = true;
            }
        }
        if (wrongSettingsPath && !notified && BallerinaSdkUtil.isValidPath(ballerinaPath)) {
            notified = true;
            BallerinaNotification.customNotification(project,
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

    public void setBallerinaSdk(String path, String version) {
        BallerinaSdkSettings settings = BallerinaSdkSettings.getInstance();
        if (settings == null) {
            return;
        }
        settings.setBallerinaSdkPath(path);
        settings.setBallerinaSdkVersion(version);
    }

    public List<BallerinaSdkUtil.BallerinaSdk> getSdkList() {
        return sdkList;
    }
}
