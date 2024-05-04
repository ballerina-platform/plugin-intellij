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

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.DocumentListener;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageServer;
import org.wso2.lsp4intellij.client.ClientContext;
import org.wso2.lsp4intellij.client.languageserver.ServerOptions;
import org.wso2.lsp4intellij.client.languageserver.requestmanager.DefaultRequestManager;
import org.wso2.lsp4intellij.client.languageserver.requestmanager.RequestManager;
import org.wso2.lsp4intellij.client.languageserver.wrapper.LanguageServerWrapper;
import org.wso2.lsp4intellij.editor.EditorEventManager;
import org.wso2.lsp4intellij.extensions.LSPExtensionManager;
import org.wso2.lsp4intellij.listeners.EditorMouseListenerImpl;
import org.wso2.lsp4intellij.listeners.EditorMouseMotionListenerImpl;
import org.wso2.lsp4intellij.listeners.LSPCaretListenerImpl;

/**
 * Contains extended LSP components which serves  ballerina language server related specific capabilities.
 *
 * @since 2.0.0
 */
public class BallerinaLSPExtensionManager implements LSPExtensionManager {

    @Override
    public <T extends DefaultRequestManager> T getExtendedRequestManagerFor(LanguageServerWrapper languageServerWrapper,
                                                                            LanguageServer languageServer,
                                                                            LanguageClient languageClient,
                                                                            ServerCapabilities serverCapabilities) {
        return null;
    }

    @Override
    public <T extends EditorEventManager> T getExtendedEditorEventManagerFor(Editor editor,
                                                                             DocumentListener documentListener,
                                                                             EditorMouseListenerImpl
                                                                                         editorMouseListener,
                                                                             EditorMouseMotionListenerImpl
                                                                                         editorMouseMotionListener,
                                                                             LSPCaretListenerImpl lspCaretListener,
                                                                             RequestManager requestManager,
                                                                             ServerOptions serverOptions,
                                                                             LanguageServerWrapper
                                                                                         languageServerWrapper) {
        return (T) new BallerinaEditorEventManager(editor, documentListener, editorMouseListener,
                editorMouseMotionListener, lspCaretListener, requestManager, serverOptions, languageServerWrapper);
    }

    @Override
    public Class<? extends LanguageServer> getExtendedServerInterface() {
        return null;
    }

    @Override
    public LanguageClient getExtendedClientFor(ClientContext clientContext) {
        return null;
    }
}
