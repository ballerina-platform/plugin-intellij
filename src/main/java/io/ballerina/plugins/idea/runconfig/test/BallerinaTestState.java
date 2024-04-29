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

package io.ballerina.plugins.idea.runconfig.test;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessHandlerFactory;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import io.ballerina.plugins.idea.project.BallerinaProjectUtil;
import io.ballerina.plugins.idea.runconfig.BallerinaExecutionState;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Represents how Ballerina testing code is executed.
 *
 * @since 2.0.0
 */
public class BallerinaTestState extends BallerinaExecutionState {

    protected BallerinaTestState(ExecutionEnvironment environment, String balPath, String script, List<String> commands,
                                 List<String> programArguments, Map<String, String> envVariables) {
        super(environment, balPath, script, commands, programArguments, envVariables);
    }

    @Override
    protected @NotNull ProcessHandler startProcess() throws ExecutionException {
        Optional<String> ballerinaPackage = BallerinaProjectUtil.findBallerinaPackage(script);
        ballerinaPackage.ifPresent(s -> script = s);

        GeneralCommandLine commandLine = new GeneralCommandLine(balPath, "test");
        commandLine.withEnvironment(envVariables);

        if (commands != null && !commands.isEmpty()) {
            for (String cmd : commands) {
                if (!cmd.isBlank()) {
                    commandLine.addParameter(cmd.strip());
                }
            }
        }

        if (programArguments != null && !programArguments.isEmpty()) {
            for (String arg : programArguments) {
                if (!arg.isBlank()) {
                    commandLine.addParameter(arg.strip());
                }
            }
        }

        commandLine.setWorkDirectory(script);
        OSProcessHandler processHandler = ProcessHandlerFactory.getInstance().createColoredProcessHandler(commandLine);
        ProcessTerminatedListener.attach(processHandler);
        return processHandler;
    }
}
