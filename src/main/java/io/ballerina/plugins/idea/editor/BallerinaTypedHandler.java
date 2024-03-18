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

package io.ballerina.plugins.idea.editor;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

/**
 * Handle auto-insertion of closing backticks or double quotes when one is typed.
 *
 * @since 2.0.0
 */
public class BallerinaTypedHandler extends TypedHandlerDelegate {

    @Override
    public @NotNull Result charTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        if (c == '`' || c == '"') { // Check if the typed character is a backtick or double quote
            insertCharAndMoveCaret(editor, c);
        }

        return super.charTyped(c, project, editor, file);
    }

    private void insertCharAndMoveCaret(Editor editor, char c) {
        Document document = editor.getDocument();
        int offset = editor.getCaretModel().getOffset();

        // Insert the character automatically for closing
        document.insertString(offset, String.valueOf(c));

        // Move the caret to the position between the two characters
        editor.getCaretModel().moveToOffset(offset);
    }

}

