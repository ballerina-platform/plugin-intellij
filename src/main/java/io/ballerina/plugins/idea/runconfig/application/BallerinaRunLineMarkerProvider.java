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

import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.ExecutionEnvironmentBuilder;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import io.ballerina.plugins.idea.BallerinaIcons;
import io.ballerina.plugins.idea.project.BallerinaProjectUtil;
import io.ballerina.plugins.idea.psi.BallerinaPsiUtil;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Cursor;
import java.nio.file.Paths;

import javax.swing.Icon;

import static io.ballerina.plugins.idea.BallerinaConstants.BAL_EXTENSION;

/**
 * Provides gutter icons for main functions and services.
 *
 * @since 2.0.0
 */
public class BallerinaRunLineMarkerProvider implements LineMarkerProvider {


    @Nullable
    @Override
    public LineMarkerInfo getLineMarkerInfo(@NotNull PsiElement element) {
        String version = BallerinaSdkService.getInstance().getBallerinaVersion(element.getProject());
        if (version != null && BallerinaPsiUtil.isMainFunction(element) || BallerinaPsiUtil.isService(element)) {
            return createRunLineMarkerInfo(element);
        }
        return null;
    }

    private LineMarkerInfo createRunLineMarkerInfo(@NotNull PsiElement element) {
        PsiFile containingFile = element.getContainingFile();
        String fileName = containingFile != null ? containingFile.getName() : "Unknown";
        VirtualFile virtualFile = containingFile != null ? containingFile.getVirtualFile() : null;
        String packageName;
        if (virtualFile != null) {
            String path = virtualFile.getPath();
            String packagePath = BallerinaProjectUtil.findBallerinaPackage(path);
            if (!packagePath.isEmpty()) {
                packageName = Paths.get(packagePath).normalize().getFileName().toString();
                fileName = "package " + packageName;
            } else {
                packageName = "";
            }
        } else {
            packageName = "";
        }
        String finalFileName = fileName;
        Icon icon = BallerinaIcons.RUN;
        return new LineMarkerInfo<>(element, element.getTextRange(), icon,
                psiElement -> "Run " + finalFileName,
                (e, elt) -> {

                    e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                    Project project = elt.getProject();
                    VirtualFile file = elt.getContainingFile().getVirtualFile();

                    if (file != null && file.getName().endsWith(BAL_EXTENSION)) {

                        RunManager runManager = RunManager.getInstance(project);
                        String configName = !packageName.isEmpty() ? packageName : finalFileName;
                        String temp = configName.endsWith(BAL_EXTENSION)
                                ? configName.substring(0, configName.length() - 4)
                                : configName;
                        RunnerAndConfigurationSettings settings =
                                runManager.createConfiguration("Run " + temp,
                                        BallerinaApplicationRunConfigType.class);
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

                        runManager.setSelectedConfiguration(settings);

                        try {
                            ExecutionEnvironmentBuilder.create(project, DefaultRunExecutor.getRunExecutorInstance(),
                                    runConfiguration).buildAndExecute();
                        } catch (ExecutionException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }, GutterIconRenderer.Alignment.CENTER, () -> "Run " + finalFileName
        );
    }
}
