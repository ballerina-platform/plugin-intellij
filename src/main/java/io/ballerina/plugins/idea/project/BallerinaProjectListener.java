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

package io.ballerina.plugins.idea.project;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import org.jetbrains.annotations.NotNull;

import static io.ballerina.plugins.idea.preloading.LSPUtils.stopProcesses;

/**
 * Listen for project open and close events.
 *
 * @since 2.0.0
 */
public class BallerinaProjectListener implements ProjectManagerListener {

    @Override
    public void projectClosing(@NotNull Project project) {
        Project[] projects = ProjectManager.getInstance().getOpenProjects();
        if (projects.length <= 1) {
            stopProcesses();
        }
    }
}
