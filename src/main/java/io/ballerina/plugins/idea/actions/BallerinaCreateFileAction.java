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

package io.ballerina.plugins.idea.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.psi.PsiDirectory;
import io.ballerina.plugins.idea.BallerinaIcons;
import org.jetbrains.annotations.NotNull;

/**
 * Add option to create a new ballerina files in a right click action.
 *
 * @since 2.0.0
 */
public class BallerinaCreateFileAction extends CreateFileFromTemplateAction {

    private static final String BALLERINA_FILE_TEMPLATE = "Ballerina_File";
    private static final String BALLERINA_MAIN_TEMPLATE = "Ballerina_Main";
    private static final String BALLERINA_SERVICE_TEMPLATE = "Ballerina_Service";

    public BallerinaCreateFileAction() {
        super("Ballerina File", "Create a new Ballerina file", BallerinaIcons.BAL_ICON);
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory directory,
                               @NotNull CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("New Ballerina File")
                .addKind("Ballerina File", BallerinaIcons.BAL_ICON, BALLERINA_FILE_TEMPLATE)
                .addKind("Ballerina Main", BallerinaIcons.MAIN, BALLERINA_MAIN_TEMPLATE)
                .addKind("Ballerina Service", BallerinaIcons.SERVICE, BALLERINA_SERVICE_TEMPLATE)
                .setValidator(new InputValidator() {
                    @Override
                    public boolean checkInput(String inputString) {
                        return !inputString.isEmpty() && !inputString.contains(" ");
                    }

                    @Override
                    public boolean canClose(String inputString) {
                        return checkInput(inputString);
                    }
                });
    }

    @Override
    protected String getActionName(@NotNull PsiDirectory directory, @NotNull String newName, String templateName) {
        return "Create Ballerina File " + newName;
    }

    @Override
    protected String getDefaultTemplateProperty() {
        return BALLERINA_FILE_TEMPLATE;
    }

    @Override
    protected @NlsContexts.DialogTitle @NotNull String getErrorTitle() {
        return "Error Creating Ballerina File";
    }
}
