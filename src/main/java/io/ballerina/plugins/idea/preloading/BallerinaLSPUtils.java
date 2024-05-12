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

package io.ballerina.plugins.idea.preloading;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import io.ballerina.plugins.idea.extensions.BallerinaLSPExtensionManager;
import io.ballerina.plugins.idea.extensions.BallerinaLanguageServerDefinition;
import io.ballerina.plugins.idea.project.BallerinaProjectListener;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import io.ballerina.plugins.idea.sdk.BallerinaSdkUtils;
import org.jetbrains.annotations.NotNull;
import org.wso2.lsp4intellij.IntellijLanguageClient;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static io.ballerina.plugins.idea.BallerinaConstants.BAL_EXT_NAME;
import static io.ballerina.plugins.idea.BallerinaConstants.BAL_LOG_PREFIX;
import static io.ballerina.plugins.idea.BallerinaConstants.BAL_LS_CMD;

/**
 * Utility function for connecting with the Ballerina Language Server.
 *
 * @since 2.0.0
 */
public class BallerinaLSPUtils {

    private static final Set<String> registeredProjects = new HashSet<>();
    private static final Logger LOG = Logger.getInstance(BallerinaLSPUtils.class);

    public static void registerProject(@NotNull Project project) {
        if (registeredProjects.contains(project.getBasePath())) {
            return;
        }
        String sdkPath = BallerinaSdkService.getInstance().getBallerinaPath(project);
        if (!BallerinaSdkUtils.isValidPath(sdkPath)) {
            return;
        }
        try {
            List<String> args = new ArrayList<>();
            args.add(sdkPath);
            args.add(BAL_LS_CMD);
            ProcessBuilder processBuilder = new ProcessBuilder(args);
            processBuilder.directory(new File(Objects.requireNonNull(project.getBasePath())));

            IntellijLanguageClient.addExtensionManager(BAL_EXT_NAME, new BallerinaLSPExtensionManager());
            IntellijLanguageClient
                    .addServerDefinition(new BallerinaLanguageServerDefinition(BAL_EXT_NAME, processBuilder), project);
            registeredProjects.add(project.getBasePath());
            ProjectManager.getInstance().addProjectManagerListener(project, new BallerinaProjectListener());
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + "Error while registering the Ballerina Language Server", e);
        }
    }

    public static void unregisterProject(@NotNull Project project) {
        if (!registeredProjects.contains(project.getBasePath())) {
            return;
        }
        registeredProjects.remove(project.getBasePath());
    }
}
