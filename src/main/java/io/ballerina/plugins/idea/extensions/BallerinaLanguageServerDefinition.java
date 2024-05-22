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

package io.ballerina.plugins.idea.extensions;

import io.ballerina.plugins.idea.preloading.BallerinaLSInitOptions;
import org.eclipse.lsp4j.InitializeParams;
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.ProcessBuilderServerDefinition;

/**
 * Extended language server definition for Ballerina language server.
 *
 * @since 2.0.0
 */
public class BallerinaLanguageServerDefinition extends ProcessBuilderServerDefinition {

    public BallerinaLanguageServerDefinition(String ext, ProcessBuilder process) {
        super(ext, process);
    }

    @Override
    public void customizeInitializeParams(InitializeParams params) {
        BallerinaLSInitOptions ballerinaLSInitOptions = new BallerinaLSInitOptions();
        ballerinaLSInitOptions.setEnableIndexPackages(true);
        params.setInitializationOptions(ballerinaLSInitOptions);
    }
}
