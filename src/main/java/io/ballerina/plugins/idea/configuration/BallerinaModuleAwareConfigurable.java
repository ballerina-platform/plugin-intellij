/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
 */

package io.ballerina.plugins.idea.configuration;

import com.intellij.application.options.ModuleAwareProjectConfigurable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a module aware configuration.
 */
public abstract class BallerinaModuleAwareConfigurable extends ModuleAwareProjectConfigurable {

    public BallerinaModuleAwareConfigurable(@NotNull Project project, String displayName, String helpTopic) {
        super(project, displayName, helpTopic);
    }

    @Override
    protected boolean isSuitableForModule(@NotNull Module module) {
        if (module.isDisposed()) {
            return false;
        }
        Project project = module.getProject();
        return !project.isDisposed() && BallerinaSdkService.getInstance(project).isBallerinaModule(module);
    }
}
