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

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * Represents Ballerina run configuration execution state.
 *
 * @since 2.0.0
 */
public class BallerinaExecutionState extends CommandLineState {

    protected final List<String> commands;
    protected final List<String> programArguments;
    protected final Map<String, String> envVariables;
    protected final String balPath;
    protected String script;

    protected BallerinaExecutionState(ExecutionEnvironment environment, String balPath, String script,
                                      List<String> commands, List<String> programArguments,
                                      Map<String, String> envVariables) {
        super(environment);
        this.commands = commands;
        this.balPath = balPath;
        this.script = script;
        this.programArguments = programArguments;
        this.envVariables = envVariables;
    }

    public String getScript() {
        return script;
    }

    @Override
    protected @NotNull ProcessHandler startProcess() throws ExecutionException {
        return null;
    }
}
