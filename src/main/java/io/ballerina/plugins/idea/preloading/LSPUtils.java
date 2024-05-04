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
import io.ballerina.plugins.idea.extensions.BallerinaLSPExtensionManager;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import io.ballerina.plugins.idea.sdk.BallerinaSdkUtils;
import org.jetbrains.annotations.NotNull;
import org.wso2.lsp4intellij.IntellijLanguageClient;
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.ProcessBuilderServerDefinition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static io.ballerina.plugins.idea.BallerinaConstants.BAL_EXT_NAME;
import static io.ballerina.plugins.idea.BallerinaConstants.BAL_LOG_PREFIX;
import static io.ballerina.plugins.idea.BallerinaConstants.BAL_LS_CMD;
import static io.ballerina.plugins.idea.OSUtils.getOperatingSystem;

/**
 * Utility function for connecting with the Ballerina Language Server.
 *
 * @since 2.0.0
 */
public class LSPUtils {

    private static final Set<Project> registeredProjects = new java.util.HashSet<>();
    private static final Logger LOG = Logger.getInstance(LSPUtils.class);

    public static void registerProject(@NotNull Project project) {
        if (registeredProjects.contains(project)) {
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
            IntellijLanguageClient.addServerDefinition(new ProcessBuilderServerDefinition(BAL_EXT_NAME, processBuilder),
                    project);
            registeredProjects.add(project);
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + "Error while registering the Ballerina Language Server", e);
        }
    }

    /**
     * Stops running language server instances.
     */
    public static void stopProcesses() {
        try {
            String os = getOperatingSystem();
            if (os == null) {
                LOG.error(BAL_LOG_PREFIX + "unsupported operating system");
                return;
            }
            Terminator terminator = new TerminatorFactory().getTerminator(os);
            if (terminator == null) {
                LOG.error(BAL_LOG_PREFIX + "unsupported operating system");
                return;
            }
            terminator.terminate();
        } catch (Exception e) {
            LOG.error(BAL_LOG_PREFIX + "Error occurred when trying to terminate ballerina processes", e);
        }
    }
}
