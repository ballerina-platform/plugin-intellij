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

package io.ballerina.plugins.idea.configuration;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.util.ui.JBUI;
import io.ballerina.plugins.idea.configuration.ui.BallerinaSdkSelection;
import io.ballerina.plugins.idea.notification.BallerinaNotification;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import io.ballerina.plugins.idea.sdk.BallerinaSdkSettings;
import io.ballerina.plugins.idea.sdk.BallerinaSdkUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Objects;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Settings page for Ballerina Language. Can add custom settings UI components and handle
 * their behavior.
 *
 * @since 2.0.0
 */
public class BallerinaLanguageSettingsConfigurable implements Configurable {

    private final Project project;
    private boolean modified = false;
    private BallerinaSdkSelection sdkSelectionUI;

    public BallerinaLanguageSettingsConfigurable(Project project) {
        this.project = project;
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Ballerina";
    }

    /**
     * Add UI components to the settings page in this function.
     */
    @Nullable
    @Override
    public JComponent createComponent() {
        sdkSelectionUI = new BallerinaSdkSelection(project);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0;  // Do not allow vertical stretching
        gbc.insets = JBUI.insets(2, 0);

        panel.add(sdkSelectionUI.getPanel(), gbc);

        // Add UI components as in the following example. increment the gbc.gridy for each component
        // gbc.gridy = 1;
        // panel.add(uiComponent, gbc);

        // Add a vertical filler panel to push everything to the top
        JPanel filler = new JPanel();
        gbc.gridy = 1;
        gbc.weighty = 1;  // Take up all extra space
        panel.add(filler, gbc);

        return panel;
    }

    /**
     * Add conditions to trigger apply button enable/disable in this function.
     */
    @Override
    public boolean isModified() {
        return isSdkChanged();
    }

    /**
     * Handle apply button action in this function.
     */
    @Override
    public void apply() {
        applySdkChange();
    }

    private void applySdkChange() {
        boolean isCustomSdkSelected = sdkSelectionUI.getUseCustomSdkCheckbox().isSelected();
        String selectedSdkPath = BallerinaSdkUtil.findBalDistFolder(sdkSelectionUI.getSelectedSdkPath());
        String currentSdkPath
                = BallerinaSdkUtil.findBalDistFolder(BallerinaSdkSettings.getInstance().getBallerinaSdkPath());
        String systemSdkPath
                = BallerinaSdkUtil.findBalDistFolder(BallerinaSdkService.getInstance().getSystemBalPath());
        modified = isSdkChanged();
        BallerinaSdkSettings.getInstance().setUseCustomSdk(isCustomSdkSelected);
        if (isCustomSdkSelected) {
            if (!Objects.equals(currentSdkPath, selectedSdkPath)) {
                String path = sdkSelectionUI.getSelectedSdkPath();
                String version = sdkSelectionUI.getSelectedSdkVersion();
                BallerinaSdkService.getInstance().setBallerinaSdk(path, version);
            }
            if (Objects.equals(selectedSdkPath, systemSdkPath)) {
                BallerinaSdkSettings.getInstance().setUseCustomSdk(false);
            }
        }
    }

    private boolean isSdkChanged() {
        String currentBalPath
                = BallerinaSdkUtil.findBalDistFolder(BallerinaSdkService.getInstance().getBallerinaPath(project));
        String selectedBalPath = BallerinaSdkUtil.findBalDistFolder(sdkSelectionUI.getSelectedSdkPath());
        return !Objects.equals(currentBalPath, selectedBalPath) && !selectedBalPath.isEmpty();
    }

    // Todo: disconnect LSP process
    @Override
    public void disposeUIResources() {
        sdkSelectionUI.disposeUi();
        if (modified) {
            BallerinaNotification.notifyRestartIde(project);
        }
    }
}
