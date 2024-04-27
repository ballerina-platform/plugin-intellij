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

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationBase;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.configurations.WithoutOwnBeforeRunSteps;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents Ballerina execution configuration.
 *
 * @since 2.0.0
 */
public abstract class BallerinaExecutionConfiguration extends RunConfigurationBase<BallerinaExecutionConfigOptions>
        implements WithoutOwnBeforeRunSteps {

    private final String executionType;
    private final Project project;

    protected BallerinaExecutionConfiguration(Project project, ConfigurationFactory factory, String name,
                                              String executionType) {
        super(project, factory, name);
        this.project = project;
        this.executionType = executionType;
    }

    @NotNull
    @Override
    protected BallerinaExecutionConfigOptions getOptions() {
        return (BallerinaExecutionConfigOptions) super.getOptions();
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        super.checkConfiguration();

        String ballerinaVersion = BallerinaSdkService.getInstance().getBallerinaVersion(project);

        if (Objects.equals(ballerinaVersion, "")) {
            throw new RuntimeConfigurationException("Ballerina SDK is not detected.");
        }
    }

    public void addCommand(String cmd) {
        List<String> commands = getOptions().getAdditionalCommands();
        commands.add(cmd);
        getOptions().setAdditionalCommands(commands);
    }

    public List<String> getCommands() {
        return getOptions().getAdditionalCommands();
    }

    public void setCommands(List<String> commands) {
        getOptions().setAdditionalCommands(commands);
    }

    public String getScriptName() {
        return getOptions().getScriptName();
    }

    public void setScriptName(String scriptName) {
        getOptions().setScriptName(scriptName);
    }

    public Map<String, String> getEnvs() {
        return getOptions().getEnvVars();
    }

    public void setEnvs(Map<String, String> envs) {
        getOptions().setEnvVars(envs);
    }

    public List<String> getProgramArgs() {
        return getOptions().getProgramArguments();
    }

    public void setProgramArgs(List<String> programArgs) {
        getOptions().setProgramArguments(programArgs);
    }

    public void addProgramArg(String arg) {
        List<String> args = getOptions().getProgramArguments();
        args.add(arg);
        getOptions().setProgramArguments(args);
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new BallerinaExecutionSettingsEditor(project, executionType);
    }
}
