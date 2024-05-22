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

package io.ballerina.plugins.idea.configuration.ui;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import io.ballerina.plugins.idea.sdk.BallerinaSdk;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import io.ballerina.plugins.idea.sdk.BallerinaSdkSettings;
import io.ballerina.plugins.idea.sdk.BallerinaSdkUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static io.ballerina.plugins.idea.BallerinaConstants.EMPTY_STRING;

/**
 * Provides UI for selecting a global sdk version for Intellij IDEA.
 * Can use system sdk and custom sdk and switch between them.
 *
 * @since 2.0.0
 */
public class BallerinaSdkPanel implements BallerinaSettingsPanel {

    private ComboBox<String> sdkVersionComboBox = new ComboBox<>();
    private JLabel sdkSettingsTextField = new JLabel();
    private JCheckBox useCustomSdkCheckbox = new JCheckBox();
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel(new GridBagLayout());
    private String selectedSdkPath = EMPTY_STRING;
    private String selectedSdkVersion = EMPTY_STRING;
    private Color defaultColor;
    private String systemBalPath;
    private String systemBalVersion;
    private List<BallerinaSdk> sdkList;
    private final Project project;
    private static final String ADD_BAL_SDK = "Add Ballerina SDK";
    private static final String SYSTEM_BAL_VERSION = "[System Ballerina version] ";
    private static final String BALLERINA_SWAN_LAKE = "Ballerina Swan Lake";
    private static final String INVALID_BAL_SDK_PATH = "Invalid Ballerina SDK path selected";
    private static final String NO_SDK = "No SDK";
    private static final String NO_VALID_BAL_SDK = "No valid Ballerina SDK found";
    private static final String LABEL_TEXT = "Ballerina SDK Path:";
    private static final String CHECKBOX_TEXT = "Use custom Ballerina SDK";
    private static final Map<String, String> sdkVersiontoPathMap = new HashMap<>();

    public BallerinaSdkPanel(Project project) {
        this.project = project;
        init();
    }

    @Override
    public JComponent getPanel() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.insets = JBUI.insets(2, 0);

        panel.add(BallerinaSettingsPanelTitle.getTitleComponent("SDK settings"), gbc);

        gbc.gridy++;

        panel.add(label, gbc);
        gbc.gridy++;
        panel.add(sdkVersionComboBox, gbc);
        gbc.gridy++;
        panel.add(sdkSettingsTextField, gbc);
        gbc.gridy++;
        panel.add(useCustomSdkCheckbox, gbc);

        return panel;
    }



    public ComboBox<String> getSdkVersionComboBox() {
        return sdkVersionComboBox;
    }

    public JLabel getSdkSettingsTextField() {
        return sdkSettingsTextField;
    }

    public JCheckBox getUseCustomSdkCheckbox() {
        return useCustomSdkCheckbox;
    }

    public JLabel getLabel() {
        return label;
    }

    public String getSelectedSdkPath() {
        return selectedSdkPath;
    }

    public String getSelectedSdkVersion() {
        return selectedSdkVersion;
    }

    @Override
    public void disposeUi() {
        sdkVersionComboBox = null;
        sdkSettingsTextField = null;
        useCustomSdkCheckbox = null;
        label = null;
        selectedSdkPath = null;
        panel.removeAll();
        panel = null;
    }

    private void init() {
        initializeComponents();
        handleInitialCheckbox();
        fillComboBox();
        setupCheckBoxListener();
        setupComboBoxListener();
    }

    private void initializeComponents() {
        label.setText(LABEL_TEXT);
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), labelFont.getStyle(), labelFont.getSize() + 1));
        useCustomSdkCheckbox.setText(CHECKBOX_TEXT);
        useCustomSdkCheckbox.setSelected(BallerinaSdkSettings.getInstance().isUseCustomSdk());
        defaultColor = sdkSettingsTextField.getForeground();
        sdkList = BallerinaSdkService.getInstance().getSdkList();
        systemBalPath = BallerinaSdkService.getInstance().getSystemBalPath();
        systemBalVersion = BallerinaSdkService.getInstance().getSystemBalVersion();
        sdkVersiontoPathMap.put(SYSTEM_BAL_VERSION + systemBalVersion, systemBalPath);
    }

    private void handleInitialCheckbox() {
        if (!useCustomSdkCheckbox.isSelected()) {
            if (BallerinaSdkUtils.isValidSdk(systemBalPath, systemBalVersion)) {
                sdkVersionComboBox.addItem(SYSTEM_BAL_VERSION + systemBalVersion);
                sdkSettingsTextField.setText(EMPTY_STRING);
                selectedSdkPath = systemBalPath;
                selectedSdkVersion = systemBalVersion;
            } else {
                sdkSettingsTextField.setText(NO_VALID_BAL_SDK);
                sdkSettingsTextField.setForeground(JBColor.RED);
                selectedSdkVersion = EMPTY_STRING;
                selectedSdkPath = EMPTY_STRING;
            }
        } else {
            String savedPath = BallerinaSdkSettings.getInstance().getBallerinaSdkPath();
            String savedVersion = BallerinaSdkSettings.getInstance().getBallerinaSdkVersion();
            if (BallerinaSdkUtils.isValidSdk(systemBalPath, systemBalVersion)) {
                sdkVersionComboBox.addItem(SYSTEM_BAL_VERSION + systemBalVersion);
            }
            if (BallerinaSdkUtils.isValidSdk(savedPath, savedVersion)) {
                sdkVersiontoPathMap.put(savedVersion, savedPath);
                sdkVersionComboBox.addItem(savedVersion);
                sdkVersionComboBox.setSelectedIndex(sdkVersionComboBox.getItemCount() - 1);
                sdkSettingsTextField.setText(EMPTY_STRING);
                selectedSdkPath = savedPath;
                selectedSdkVersion = savedVersion;
            } else {
                if (savedPath.isEmpty() || savedVersion.isEmpty()) {
                    sdkSettingsTextField.setText(EMPTY_STRING);
                    sdkSettingsTextField.setForeground(defaultColor);
                    selectedSdkVersion = EMPTY_STRING;
                    selectedSdkPath = EMPTY_STRING;
                } else if (BallerinaSdkUtils.isValidSdk(systemBalPath, systemBalVersion)) {
                    sdkVersionComboBox.setSelectedIndex(0);
                    sdkSettingsTextField.setText(EMPTY_STRING);
                    selectedSdkPath = systemBalPath;
                    selectedSdkVersion = systemBalVersion;
                } else {
                    sdkSettingsTextField.setText(NO_VALID_BAL_SDK);
                    sdkSettingsTextField.setForeground(JBColor.RED);
                    selectedSdkVersion = EMPTY_STRING;
                    selectedSdkPath = EMPTY_STRING;
                }
            }
        }
    }

    private void fillComboBox() {
        for (BallerinaSdk sdk : sdkList) {
            if (!Objects.equals(selectedSdkPath, sdk.getPath()) && !Objects.equals(systemBalPath, sdk.getPath())) {
                sdkVersionComboBox.addItem(sdk.getVersion());
                sdkVersiontoPathMap.put(sdk.getVersion(), sdk.getPath());
            }
        }
        sdkVersionComboBox.addItem(NO_SDK);
        sdkVersionComboBox.addItem(ADD_BAL_SDK);
        sdkVersionComboBox.setEnabled(useCustomSdkCheckbox.isSelected());
        if (selectedSdkPath.isEmpty() && selectedSdkVersion.isEmpty()) {
            sdkVersionComboBox.setSelectedItem(NO_SDK);
        }
    }

    private void setupCheckBoxListener() {
        useCustomSdkCheckbox.addActionListener(e -> {
            boolean selected = useCustomSdkCheckbox.isSelected();
            if (!selected) {
                String version = sdkVersionComboBox.getItemAt(0);
                if (Objects.equals(version, SYSTEM_BAL_VERSION + systemBalVersion)) {
                    sdkVersionComboBox.setSelectedIndex(0);
                    sdkSettingsTextField.setText(EMPTY_STRING);
                    selectedSdkVersion = systemBalVersion;
                    selectedSdkPath = systemBalPath;
                } else {
                    sdkVersionComboBox.setSelectedItem(ADD_BAL_SDK);
                    sdkSettingsTextField.setText(NO_VALID_BAL_SDK);
                    selectedSdkVersion = EMPTY_STRING;
                    selectedSdkPath = EMPTY_STRING;
                }
            }
            sdkVersionComboBox.setEnabled(selected);
        });
    }

    private void setupComboBoxListener() {
        sdkVersionComboBox.addActionListener(e -> {
            if (ADD_BAL_SDK.equals(sdkVersionComboBox.getSelectedItem())) {
                handleCustomFolderSelection();
            } else if (NO_SDK.equals(sdkVersionComboBox.getSelectedItem())) {
                handleNoSdkSelection();
            } else {
                handleDetectedPathsSelection();
            }
        });
    }

    private void handleNoSdkSelection() {
        sdkSettingsTextField.setText(EMPTY_STRING);
        sdkSettingsTextField.setForeground(defaultColor);
        selectedSdkVersion = EMPTY_STRING;
        selectedSdkPath = EMPTY_STRING;
    }

    private void handleCustomFolderSelection() {
        VirtualFile file =
                FileChooser.chooseFile(new FileChooserDescriptor(false, true,
                                false, false,
                                false, false),
                        project, null);
        if (file == null) {
            return;
        }
        String balDistFolder = BallerinaSdkUtils.findBalDistFolder(file.getPath());
        String sdkPath = BallerinaSdkUtils.getBalBatFromDist(balDistFolder);
        if (BallerinaSdkUtils.isValidPath(sdkPath)) {
            sdkPath = BallerinaSdkUtils.getNormalizedPath(sdkPath);
            if (sdkPath.equals(systemBalPath)) {
                sdkVersionComboBox.setSelectedIndex(0);
                sdkSettingsTextField.setText(EMPTY_STRING);
            } else {
                selectedSdkPath = sdkPath;
                boolean found = false;
                if (sdkVersionComboBox.getItemCount() > 1) {
                    for (int i = 0; i < sdkVersionComboBox.getItemCount(); i++) {
                        String item = sdkVersionComboBox.getItemAt(i);
                        if (sdkVersiontoPathMap.containsKey(item) && sdkVersiontoPathMap.get(item).equals(sdkPath)) {
                            found = true;
                            sdkVersionComboBox.setSelectedIndex(i);
                            break;
                        }
                    }
                }
                String version = BallerinaSdkUtils.getVersionFromPath(sdkPath);
                if (!found) {
                    sdkVersiontoPathMap.put(version, sdkPath);
                    sdkVersionComboBox.insertItemAt(version, 1);
                    sdkVersionComboBox.setSelectedIndex(1);
                }
                if (version.isEmpty()) {
                    version = BALLERINA_SWAN_LAKE;
                }
                selectedSdkVersion = version;
                sdkSettingsTextField.setText(EMPTY_STRING);
            }
            sdkSettingsTextField.setForeground(defaultColor);
        } else {
            sdkSettingsTextField.setText(INVALID_BAL_SDK_PATH);
            sdkSettingsTextField.setForeground(JBColor.RED);
            selectedSdkVersion = EMPTY_STRING;
            selectedSdkPath = EMPTY_STRING;
        }
    }

    private void handleDetectedPathsSelection() {
        String version = (String) sdkVersionComboBox.getSelectedItem();
        String path = sdkVersiontoPathMap.get(version);
        if (Objects.equals(version, SYSTEM_BAL_VERSION + systemBalVersion)) {
            selectedSdkPath = systemBalPath;
            selectedSdkVersion = systemBalVersion;
            sdkSettingsTextField.setText(EMPTY_STRING);
            sdkSettingsTextField.setForeground(defaultColor);
        } else {
            if (version != null && version.isEmpty()) {
                version = BALLERINA_SWAN_LAKE;
            }
            selectedSdkPath = path;
            selectedSdkVersion = version;
            sdkSettingsTextField.setText(EMPTY_STRING);
            sdkSettingsTextField.setForeground(defaultColor);
        }
    }
}
