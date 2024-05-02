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

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.StoredProperty;

import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Stores Ballerina execution configuration options.
 *
 * @since 2.0.0
 */
public class BallerinaExecutionConfigOptions extends RunConfigurationOptions {

    private final StoredProperty<String> scriptName =
            string(EMPTY).provideDelegate(this, "scriptName");

    private final StoredProperty<List<Object>> additionalCommands =
            list().provideDelegate(this, "additionalCommands");

    private final StoredProperty<Map<Object, Object>> envVars =
            map().provideDelegate(this, "envVars");

    private final StoredProperty<List<Object>> programArguments =
            list().provideDelegate(this, "programArguments");

    public String getScriptName() {
        return scriptName.getValue(this);
    }

    public void setScriptName(String scriptName) {
        this.scriptName.setValue(this, scriptName);
    }

    public List<String> getAdditionalCommands() {
        return (List<String>) (List) additionalCommands.getValue(this);
    }

    public void setAdditionalCommands(List<String> additionalCommands) {
        this.additionalCommands.setValue(this, (List) additionalCommands);
    }

    public Map<String, String> getEnvVars() {
        return (Map<String, String>) (Map) envVars.getValue(this);
    }

    public void setEnvVars(Map<String, String> envVars) {
        this.envVars.setValue(this, (Map) envVars);
    }

    public List<String> getProgramArguments() {
        return (List<String>) (List) programArguments.getValue(this);
    }

    public void setProgramArguments(List<String> programArguments) {
        this.programArguments.setValue(this, (List) programArguments);
    }
}
