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

package io.ballerina.plugins.idea.runconfig;

import com.intellij.execution.configuration.EnvironmentVariablesComponent;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.JBUI;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import org.jetbrains.annotations.NotNull;

import java.awt.BorderLayout;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Provides settings editor for run configurations.
 *
 * @since 2.0.0
 */
public class BallerinaExecutionSettingsEditor extends SettingsEditor<BallerinaExecutionConfiguration> {

    protected JPanel myPanel;
    protected TextFieldWithBrowseButton scriptPathField;
    protected EnvironmentVariablesComponent envVarsComponent;
    protected JBList<String> commandOptionsList;
    protected DefaultListModel<String> commandOptionsModel;
    protected JLabel commandOptionsLabel;
    protected JPanel commandOptionsPanel;
    protected JBList<String> programArgumentsList;
    protected DefaultListModel<String> programArgumentsModel;
    protected JLabel programArgumentsLabel;
    protected JPanel programArgumentsPanel;
    protected JTextField ballerinaSdkField;

    public BallerinaExecutionSettingsEditor(Project project, String executionType) {
        setupScriptPathField(executionType);
        setupBalSdkField(project);
        setupEnvVarComponent();
        setupCommandOptionsComponent();
        setupProgramArgComponent();
        createFinalPanel(executionType);
    }

    private void setupScriptPathField(String executionType) {
        scriptPathField = new TextFieldWithBrowseButton();
        scriptPathField.addBrowseFolderListener("Select " + executionType + " Source", null, null,
                FileChooserDescriptorFactory.createSingleFileDescriptor());
    }

    private void setupBalSdkField(Project project) {
        String balVersion = BallerinaSdkService.getInstance().getBallerinaVersion(project);
        ballerinaSdkField = new JTextField();
        if (balVersion != null) {
            ballerinaSdkField.setText(balVersion);
            ballerinaSdkField.setEditable(false);
        }
    }

    private void setupEnvVarComponent() {
        envVarsComponent = new EnvironmentVariablesComponent();
        envVarsComponent.setLabelLocation(BorderLayout.WEST);
    }

    private void setupCommandOptionsComponent() {
        commandOptionsLabel = new JLabel("Command options:");
        commandOptionsModel = new DefaultListModel<>();
        commandOptionsList = new JBList<>(commandOptionsModel);
        commandOptionsList.setVisibleRowCount(3);

        commandOptionsPanel = ToolbarDecorator.createDecorator(commandOptionsList)
                .setAddAction(button -> {
                    String value = JOptionPane.showInputDialog("Enter Command:");
                    if (value != null && !value.isEmpty()) {
                        commandOptionsModel.addElement(value);
                    }
                })
                .setRemoveAction(button -> {
                    int index = commandOptionsList.getSelectedIndex();
                    if (index != -1) {
                        commandOptionsModel.remove(index);
                    }
                })
                .createPanel();

        commandOptionsPanel.setBorder(JBUI.Borders.empty(5));
    }

    private void setupProgramArgComponent() {
        programArgumentsLabel = new JLabel("Program arguments:");
        programArgumentsModel = new DefaultListModel<>();
        programArgumentsList = new JBList<>(programArgumentsModel);
        programArgumentsList.setVisibleRowCount(3);

        programArgumentsPanel = ToolbarDecorator.createDecorator(programArgumentsList)
                .setAddAction(button -> {
                    String value = JOptionPane.showInputDialog("Enter Argument:");
                    if (value != null && !value.isEmpty()) {
                        programArgumentsModel.addElement(value);
                    }
                })
                .setRemoveAction(button -> {
                    int index = programArgumentsList.getSelectedIndex();
                    if (index != -1) {
                        programArgumentsModel.remove(index);
                    }
                })
                .createPanel();

        programArgumentsPanel.setBorder(JBUI.Borders.empty(5));
    }

    private void createFinalPanel(String executionType) {
        FormBuilder formBuilder = FormBuilder.createFormBuilder()
                .addLabeledComponent(executionType + " source:", scriptPathField)
                .addLabeledComponent("Ballerina SDK version:", ballerinaSdkField)
                .addComponent(envVarsComponent)
                .addComponentFillVertically(new JPanel(), 0)
                .addComponent(commandOptionsLabel)
                .addComponent(commandOptionsPanel)
                .addComponentFillVertically(new JPanel(), 0)
                .addComponent(programArgumentsLabel)
                .addComponent(programArgumentsPanel);

        myPanel = formBuilder.getPanel();
        myPanel.setBorder(JBUI.Borders.empty(10));
    }

    @Override
    protected void resetEditorFrom(@NotNull BallerinaExecutionConfiguration configuration) {
        scriptPathField.setText(configuration.getScriptName());
        envVarsComponent.setEnvs(configuration.getEnvs());
        commandOptionsModel.clear();
        commandOptionsModel.addAll(configuration.getCommands());
        programArgumentsModel.clear();
        programArgumentsModel.addAll(configuration.getProgramArgs());
    }

    @Override
    protected void applyEditorTo(@NotNull BallerinaExecutionConfiguration configuration) throws ConfigurationException {
        configuration.setScriptName(scriptPathField.getText());
        configuration.setEnvs(envVarsComponent.getEnvs());
        List<String> commands = Collections.list(commandOptionsModel.elements());
        configuration.setCommands(commands);
        List<String> programArguments = Collections.list(programArgumentsModel.elements());
        configuration.setProgramArgs(programArguments);
    }

    @Override
    protected @NotNull JComponent createEditor() {
        return myPanel;
    }
}
