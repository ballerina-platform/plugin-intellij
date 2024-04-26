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
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.ExecutionEnvironmentBuilder;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import io.ballerina.plugins.idea.BallerinaConstants;
import io.ballerina.plugins.idea.BallerinaIcons;
import io.ballerina.plugins.idea.project.BallerinaProjectUtil;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Paths;
import java.util.Objects;

/**
 * Action for running ballerina application.
 *
 * @since 2.0.0
 */
public class BallerinaRunFileAction extends AnAction {

    public BallerinaRunFileAction() {
        super(BallerinaIcons.APPLICATION_RUN);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);

        if (project != null && file != null && (file.getName().endsWith(BallerinaConstants.BAL_EXTENSION)
                | file.isDirectory())) {
            String fileName = file.getName();
            String path = file.getPath();
            String packagePath = BallerinaProjectUtil.findBallerinaPackage(path);
            if (!packagePath.isEmpty()) {
                fileName = Paths.get(packagePath).normalize().getFileName().toString();
            }
            RunManager runManager = RunManager.getInstance(project);
            String temp = fileName.endsWith(BallerinaConstants.BAL_EXTENSION)
                    ? fileName.substring(0, fileName.length() - 4) : fileName;
            RunnerAndConfigurationSettings settings =
                    runManager.createConfiguration("Run " + temp, BallerinaApplicationRunConfigType.class);
            BallerinaApplicationRunConfiguration runConfiguration =
                    (BallerinaApplicationRunConfiguration) settings.getConfiguration();
            String script = file.getPath();
            String ballerinaPackage = BallerinaProjectUtil.findBallerinaPackage(script);
            if (!ballerinaPackage.isEmpty()) {
                script = ballerinaPackage;
            }
            runConfiguration.setScriptName(script);

            boolean exists = false;
            for (RunConfiguration existingConfig : runManager.getAllConfigurationsList()) {
                if (existingConfig instanceof BallerinaApplicationRunConfiguration &&
                        existingConfig.getName().equals(runConfiguration.getName()) &&
                        ((BallerinaApplicationRunConfiguration) existingConfig).getScriptName()
                                .equals(runConfiguration.getScriptName())) {
                    exists = true;
                    settings = runManager.findSettings(existingConfig);
                    runConfiguration = (BallerinaApplicationRunConfiguration) existingConfig;
                    break;
                }
            }

            if (!exists) {
                runManager.addConfiguration(settings);
            }

            // Select the new configuration in the UI
            runManager.setSelectedConfiguration(settings);

            try {
                ExecutionEnvironmentBuilder.create(project, DefaultRunExecutor.getRunExecutorInstance(),
                        runConfiguration).buildAndExecute();

            } catch (ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);
        String version = BallerinaSdkService.getInstance().getBallerinaVersion(e.getProject());
        boolean visible =
                file != null && (file.getName().endsWith(BallerinaConstants.BAL_EXTENSION) | file.isDirectory())
                        && !Objects.equals(version, "");
        if (visible) {
            String fileName = file.getName();
            String path = file.getPath();
            String packagePath = BallerinaProjectUtil.findBallerinaPackage(path);
            String packageName = null;
            if (!packagePath.isEmpty()) {
                packageName = Paths.get(packagePath).normalize().getFileName().toString();
                fileName = "package " + packageName;
            }
            if (!file.isDirectory() || (file.isDirectory() && file.getName().equals(packageName))) {
                e.getPresentation().setVisible(true);
                e.getPresentation().setText("Run " + fileName);
            } else {
                e.getPresentation().setVisible(false);
            }
        } else {
            e.getPresentation().setVisible(false);
        }
    }
}

