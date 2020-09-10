/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
 */

package io.ballerina.plugins.idea.inspections;

import com.google.common.base.Strings;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.EditorNotificationPanel;
import com.intellij.ui.EditorNotifications;
import icons.BallerinaIcons;
import io.ballerina.plugins.idea.BallerinaFileType;
import io.ballerina.plugins.idea.preloading.BallerinaCmdException;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import io.ballerina.plugins.idea.sdk.BallerinaSdkUtils;
import org.jetbrains.annotations.NotNull;

import static io.ballerina.plugins.idea.sdk.BallerinaSdkUtils.getMajorVersion;
import static io.ballerina.plugins.idea.sdk.BallerinaSdkUtils.getMinorVersion;

/**
 * Provides wrong module type message if the ballerina file is not in a Ballerina module.
 */
public class VersionMismatchNotificationProvider extends EditorNotifications.Provider<EditorNotificationPanel>
        implements DumbAware {

    private static final Key<EditorNotificationPanel> KEY = Key.create("Plugin Version Mismatch");

    private final Project myProject;

    public VersionMismatchNotificationProvider(@NotNull Project project) {
        myProject = project;
    }

    @NotNull
    @Override
    public Key<EditorNotificationPanel> getKey() {
        return KEY;
    }

    @Override
    public EditorNotificationPanel createNotificationPanel(@NotNull VirtualFile file, @NotNull FileEditor fileEditor) {
        if (file.getFileType() != BallerinaFileType.INSTANCE) {
            return null;
        }
        Module module = ModuleUtilCore.findModuleForFile(file, myProject);
        if (module == null) {
            return null;
        }
        String sdkVersion = BallerinaSdkService.getInstance(myProject).getSdkVersion(module);
        String pluginVersion = BallerinaSdkUtils.getBallerinaPluginVersion();

        if (!Strings.isNullOrEmpty(sdkVersion) && !Strings.isNullOrEmpty(pluginVersion)) {
            // Compares the major and minor version numbers between the ballerina sdk and the plugin.
            if (!getMajorVersion(sdkVersion).equals(getMajorVersion(pluginVersion))
                    || !getMinorVersion(sdkVersion).equals(getMinorVersion(pluginVersion))) {
                return createPanel(module, sdkVersion, pluginVersion, false);
            }
        } else if (Strings.isNullOrEmpty(sdkVersion)) {
            // Compares auto-detected ballerina version with the plugin version.
            String autoDetectedBalHome = "";
            try {
                autoDetectedBalHome = BallerinaSdkUtils.autoDetectSdk(myProject);
            } catch (BallerinaCmdException e) {
                // no operation.
            }
            sdkVersion = BallerinaSdkUtils.retrieveBallerinaVersion(autoDetectedBalHome);
            if (!Strings.isNullOrEmpty(sdkVersion) && !Strings.isNullOrEmpty(pluginVersion)) {
                // Compares the major and minor version numbers between the auto detected ballerina version and
                // the plugin.
                if (!getMajorVersion(sdkVersion).equals(getMajorVersion(pluginVersion))
                        || !getMinorVersion(sdkVersion).equals(getMinorVersion(pluginVersion))) {
                    return createPanel(module, sdkVersion, pluginVersion, true);
                }
            }
        }
        return null;
    }

    @NotNull
    private static EditorNotificationPanel createPanel(@NotNull Module module, @NotNull String sdkVersion,
                                                       @NotNull String pluginVersion, boolean autoDetected) {
        EditorNotificationPanel panel = new EditorNotificationPanel();
        if (autoDetected) {
            panel.setText(String.format("Auto-detected Ballerina version (%s) for the Module '%s' does not match " +
                            "your Ballerina plugin version (%s). Some features may not work properly.",
                    sdkVersion, module.getName(), pluginVersion));
        } else {
            panel.setText(String.format("Your Ballerina SDK version (%s) for the Module '%s' does not match " +
                            "your Ballerina plugin version (%s). Some features may not work properly.",
                    sdkVersion, module.getName(), pluginVersion));
        }
        panel.icon(BallerinaIcons.ICON);
        return panel;
    }
}
