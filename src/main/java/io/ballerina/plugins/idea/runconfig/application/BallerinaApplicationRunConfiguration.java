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

package io.ballerina.plugins.idea.runconfig.application;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import io.ballerina.plugins.idea.notification.BallerinaPluginNotifier;
import io.ballerina.plugins.idea.runconfig.BallerinaExecutionConfiguration;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents Ballerina application run configuration.
 *
 * @since 2.0.0
 */
public class BallerinaApplicationRunConfiguration extends BallerinaExecutionConfiguration {

    protected BallerinaApplicationRunConfiguration(Project project, ConfigurationFactory factory, String name) {
        super(project, factory, name, BallerinaApplicationRunConfigType.EXEC_TYPE);
    }

    @Override
    public @Nullable RunProfileState getState(@NotNull Executor executor,
                                              @NotNull ExecutionEnvironment executionEnvironment)
            throws ExecutionException {
        if (BallerinaSdkService.getInstance().getBallerinaVersion(executionEnvironment.getProject()).isEmpty()) {
            BallerinaPluginNotifier.notifyBallerinaNotDetected(executionEnvironment.getProject());
            return null;
        }

        String sourcePath = getOptions().getSourcePath();

        try {
            Path path = Paths.get(sourcePath).normalize();
            if (!Files.exists(path)) {
                throw new IllegalArgumentException("File does not exist");
            }
            sourcePath = path.toString();
        } catch (Exception e) {
            BallerinaPluginNotifier.customNotification(executionEnvironment.getProject(), NotificationType.ERROR,
                    "Invalid path", "Provided path is not valid or does not exist.");
            return null;
        }

        return new BallerinaApplicationRunningState(executionEnvironment,
                BallerinaSdkService.getInstance().getBallerinaPath(executionEnvironment.getProject()),
                sourcePath, getOptions().getAdditionalCommands(), getOptions().getProgramArguments(),
                getOptions().getEnvVars());
    }
}
