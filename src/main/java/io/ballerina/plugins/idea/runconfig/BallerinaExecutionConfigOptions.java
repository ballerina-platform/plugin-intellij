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

/**
 * Stores Ballerina execution configuration options.
 *
 * @since 2.0.0
 */
public class BallerinaExecutionConfigOptions extends RunConfigurationOptions {

    private final StoredProperty<String> myScriptName =
            string("").provideDelegate(this, "scriptName");

    private final StoredProperty<List<Object>> myAdditionalCommands =
            list().provideDelegate(this, "additionalCommands");

    private final StoredProperty<Map<Object, Object>> myEnvVars =
            map().provideDelegate(this, "envVars");

    private final StoredProperty<List<Object>> myProgramArguments =
            list().provideDelegate(this, "programArguments");

    public String getScriptName() {
        return myScriptName.getValue(this);
    }

    public void setScriptName(String scriptName) {
        myScriptName.setValue(this, scriptName);
    }

    public List<String> getAdditionalCommands() {
        return (List<String>) (List) myAdditionalCommands.getValue(this);
    }

    public void setAdditionalCommands(List<String> additionalCommands) {
        myAdditionalCommands.setValue(this, (List) additionalCommands);
    }

    public Map<String, String> getEnvVars() {
        return (Map<String, String>) (Map) myEnvVars.getValue(this);
    }

    public void setEnvVars(Map<String, String> envVars) {
        myEnvVars.setValue(this, (Map) envVars);
    }

    public List<String> getProgramArguments() {
        return (List<String>) (List) myProgramArguments.getValue(this);
    }

    public void setProgramArguments(List<String> programArguments) {
        myProgramArguments.setValue(this, (List) programArguments);
    }
}
