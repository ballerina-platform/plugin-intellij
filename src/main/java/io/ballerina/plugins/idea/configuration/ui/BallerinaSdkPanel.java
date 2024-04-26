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
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import io.ballerina.plugins.idea.sdk.BallerinaSdkSettings;
import io.ballerina.plugins.idea.sdk.BallerinaSdkUtil;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Provides UI for selecting a global sdk version for Intellij IDEA.
 * Can use system sdk and custom sdk and switch between them.
 *
 * @since 2.0.0
 */
public class BallerinaSdkPanel {

    private ComboBox<String> sdkVersionComboBox = new ComboBox<>();
    private JLabel selectedVersionTextField = new JLabel();
    private JCheckBox useCustomSdkCheckbox = new JCheckBox();
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel(new GridBagLayout());
    private String selectedSdkPath = "";
    private String selectedSdkVersion = "";
    private BallerinaSdkService sdkService;
    private BallerinaSdkSettings sdkSettings;
    private Color defaultColor;
    private String systemBalPath;
    private String systemBalVersion;
    private List<BallerinaSdkUtil.BallerinaSdk> sdkList;
    private final Project project;
    private static final String CB_DEFAULT_PREFIX = "[System default] ";
    private static final String ADD_BALLERINA_SDK = "Add Ballerina SDK";
    private static final String SYSTEM_BALLERINA_VERSION = "[System Ballerina version] ";
    private static final String BALLERINA_SWAN_LAKE = "Ballerina Swan Lake";
    private static final String INVALID_BAL_SDK_PATH = "Invalid Ballerina SDK path selected";
    private static final String NO_VALID_BAL_SDK = "No valid Ballerina SDK found";
    private static final String LABEL_TEXT = "Ballerina SDK Path:";
    private static final String CHECKBOX_TEXT = "Use custom Ballerina SDK";

    public BallerinaSdkPanel(Project project) {
        this.project = project;
        init();
    }

    public JComponent getPanel() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.insets = JBUI.insets(2, 0);

        panel.add(label, gbc);

        gbc.gridy++;
        panel.add(sdkVersionComboBox, gbc);

        gbc.gridy++;
        panel.add(selectedVersionTextField, gbc);

        gbc.gridy++;
        panel.add(useCustomSdkCheckbox, gbc);

        return panel;
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
        Color defaultColor = selectedVersionTextField.getForeground();
        sdkList = BallerinaSdkService.getInstance().getSdkList();
        systemBalPath = BallerinaSdkService.getInstance().getSystemBalPath();
        systemBalVersion = BallerinaSdkService.getInstance().getSystemBalVersion();
    }

    private void handleInitialCheckbox() {
        if (!useCustomSdkCheckbox.isSelected()) {
            if (BallerinaSdkUtil.isValidSdk(systemBalPath, systemBalVersion)) {
                sdkVersionComboBox.addItem(CB_DEFAULT_PREFIX + BallerinaSdkUtil.findBalDistFolder(systemBalPath));
                selectedVersionTextField.setText(SYSTEM_BALLERINA_VERSION + systemBalVersion);
                selectedSdkPath = systemBalPath;
                selectedSdkVersion = systemBalVersion;
            } else {
                selectedVersionTextField.setText(NO_VALID_BAL_SDK);
                selectedVersionTextField.setForeground(JBColor.RED);
                selectedSdkVersion = "";
                selectedSdkPath = "";
            }
        } else {
            String savedPath = BallerinaSdkSettings.getInstance().getBallerinaSdkPath();
            String savedVersion = BallerinaSdkSettings.getInstance().getBallerinaSdkVersion();
            if (BallerinaSdkUtil.isValidSdk(systemBalPath, systemBalVersion)) {
                sdkVersionComboBox.addItem(CB_DEFAULT_PREFIX + BallerinaSdkUtil.findBalDistFolder(systemBalPath));
            }
            if (BallerinaSdkUtil.isValidSdk(savedPath, savedVersion)) {
                sdkVersionComboBox.addItem(BallerinaSdkUtil.findBalDistFolder(savedPath));
                sdkVersionComboBox.setSelectedIndex(sdkVersionComboBox.getItemCount() - 1);
                selectedVersionTextField.setText(savedVersion);
                selectedSdkPath = savedPath;
                selectedSdkVersion = savedVersion;
            } else {
                if (BallerinaSdkUtil.isValidSdk(systemBalPath, systemBalVersion)) {
                    sdkVersionComboBox.setSelectedIndex(0);
                    selectedVersionTextField.setText(SYSTEM_BALLERINA_VERSION + systemBalVersion);
                    selectedSdkPath = systemBalPath;
                    selectedSdkVersion = systemBalVersion;
                } else {
                    selectedVersionTextField.setText(NO_VALID_BAL_SDK);
                    selectedVersionTextField.setForeground(JBColor.RED);
                    selectedSdkVersion = "";
                    selectedSdkPath = "";
                }
            }
        }
    }

    private void fillComboBox() {
        for (BallerinaSdkUtil.BallerinaSdk sdk : sdkList) {
            if (!Objects.equals(selectedSdkPath, sdk.getPath()) && !Objects.equals(systemBalPath, sdk.getPath())) {
                sdkVersionComboBox.addItem(BallerinaSdkUtil.findBalDistFolder(sdk.getPath()));
            }
        }
        sdkVersionComboBox.addItem(ADD_BALLERINA_SDK);
        sdkVersionComboBox.setEnabled(useCustomSdkCheckbox.isSelected());
    }

    private void setupCheckBoxListener() {
        useCustomSdkCheckbox.addActionListener(e -> {
            boolean selected = useCustomSdkCheckbox.isSelected();
            if (!selected) {
                String path = sdkVersionComboBox.getItemAt(0);
                if (Objects.equals(path, CB_DEFAULT_PREFIX + BallerinaSdkUtil.findBalDistFolder(systemBalPath))) {
                    sdkVersionComboBox.setSelectedIndex(0);
                    selectedVersionTextField.setText(SYSTEM_BALLERINA_VERSION + systemBalVersion);
                    selectedSdkVersion = systemBalVersion;
                    selectedSdkPath = systemBalPath;
                } else {
                    sdkVersionComboBox.setSelectedItem(ADD_BALLERINA_SDK);
                    selectedVersionTextField.setText(NO_VALID_BAL_SDK);
                    selectedSdkVersion = "";
                    selectedSdkPath = "";
                }
            }
            sdkVersionComboBox.setEnabled(selected);
        });
    }

    private void setupComboBoxListener() {
        sdkVersionComboBox.addActionListener(e -> {
            if (ADD_BALLERINA_SDK.equals(sdkVersionComboBox.getSelectedItem())) {
                handleCustomFolderSelection();
            } else {
                handleDetectedPathsSelection();
            }
        });
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
        String balDistFolder = BallerinaSdkUtil.findBalDistFolder(file.getPath());
        String sdkPath = BallerinaSdkUtil.getBalBatFromDist(balDistFolder);
        if (BallerinaSdkUtil.isValidPath(sdkPath)) {
            sdkPath = Paths.get(sdkPath).normalize().toString();
            if (sdkPath.equals(systemBalPath)) {
                sdkVersionComboBox.setSelectedIndex(0);
                selectedVersionTextField.setText(SYSTEM_BALLERINA_VERSION + systemBalVersion);
            } else {
                selectedSdkPath = sdkPath;
                boolean found = false;
                if (sdkVersionComboBox.getItemCount() > 1) {
                    for (int i = 0; i < sdkVersionComboBox.getItemCount(); i++) {
                        if (sdkVersionComboBox.getItemAt(i)
                                .equals(BallerinaSdkUtil.findBalDistFolder(sdkPath))) {
                            found = true;
                            sdkVersionComboBox.setSelectedIndex(i);
                            break;
                        }
                    }
                }
                if (!found) {
                    sdkVersionComboBox.insertItemAt(BallerinaSdkUtil.findBalDistFolder(sdkPath), 1);
                    sdkVersionComboBox.setSelectedIndex(1);
                }
                String version = BallerinaSdkUtil.getVersionFromPath(sdkPath);
                if (version.isEmpty()) {
                    version = BALLERINA_SWAN_LAKE;
                }
                selectedSdkVersion = version;
                selectedVersionTextField.setText(version);
            }
            selectedVersionTextField.setForeground(defaultColor);
        } else {
            selectedVersionTextField.setText(INVALID_BAL_SDK_PATH);
            selectedVersionTextField.setForeground(JBColor.RED);
            selectedSdkVersion = "";
            selectedSdkPath = "";
        }
    }

    private void handleDetectedPathsSelection() {
        String path = (String) sdkVersionComboBox.getSelectedItem();
        if (Objects.equals(path, CB_DEFAULT_PREFIX
                + BallerinaSdkUtil.findBalDistFolder(systemBalPath))) {
            selectedSdkPath = systemBalPath;
            selectedSdkVersion = systemBalVersion;
            selectedVersionTextField.setText(SYSTEM_BALLERINA_VERSION + systemBalVersion);
            selectedVersionTextField.setForeground(defaultColor);
        } else {
            if (path != null) {
                path = BallerinaSdkUtil.getBalBatFromDist(path);
            }
            String version = BallerinaSdkUtil.getVersionFromPath(path);
            if (version.isEmpty()) {
                version = BALLERINA_SWAN_LAKE;
            }
            selectedSdkPath = path;
            selectedSdkVersion = version;
            selectedVersionTextField.setText(version);
            selectedVersionTextField.setForeground(defaultColor);
        }
    }

    public ComboBox<String> getSdkVersionComboBox() {
        return sdkVersionComboBox;
    }

    public JLabel getSelectedVersionTextField() {
        return selectedVersionTextField;
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

    public void disposeUi() {
        sdkVersionComboBox = null;
        selectedVersionTextField = null;
        useCustomSdkCheckbox = null;
        label = null;
        selectedSdkPath = null;
        panel.removeAll();
        panel = null;
    }
}
