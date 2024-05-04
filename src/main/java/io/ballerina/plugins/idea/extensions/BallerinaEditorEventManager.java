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
import com.intellij.openapi.editor.event.EditorMouseListener;
import com.intellij.openapi.editor.event.EditorMouseMotionListener;
import org.jetbrains.annotations.NotNull;
import org.wso2.lsp4intellij.client.languageserver.ServerOptions;
import org.wso2.lsp4intellij.client.languageserver.requestmanager.RequestManager;
import org.wso2.lsp4intellij.client.languageserver.wrapper.LanguageServerWrapper;
import org.wso2.lsp4intellij.editor.EditorEventManager;
import org.wso2.lsp4intellij.listeners.LSPCaretListenerImpl;

/**
 * Contains extended EditorEventManager which serves  ballerina language server related specific capabilities.
 *
 * @since 2.0.0
 */
public class BallerinaEditorEventManager extends EditorEventManager {

    public BallerinaEditorEventManager(Editor editor,
                                       DocumentListener documentListener,
                                       EditorMouseListener mouseListener,
                                       EditorMouseMotionListener mouseMotionListener,
                                       LSPCaretListenerImpl caretListener,
                                       RequestManager requestmanager,
                                       ServerOptions serverOptions,
                                       LanguageServerWrapper wrapper) {
        super(editor, documentListener, mouseListener, mouseMotionListener, caretListener, requestmanager,
                serverOptions,
                wrapper);
    }

    @Override
    @NotNull
    public String getCompletionPrefix(Editor editor, int offset) {
        String delimiterString = String.join("", this.completionTriggers)
                + "!@#$%^&*()-+=|][}{:;/?.>,<\\ \t\n\r";
        String documentText = editor.getDocument().getText();
        int lastIndex = -1;
        for (char delimiter : delimiterString.toCharArray()) {
            int index = documentText.substring(0, offset).lastIndexOf(delimiter);
            if (index > lastIndex) {
                lastIndex = index;
            }
        }
        return lastIndex >= 0 ? documentText.substring(lastIndex + 1, offset) : documentText.substring(0, offset);
    }

    @Override
    public void refreshAnnotations() {

    }
}
