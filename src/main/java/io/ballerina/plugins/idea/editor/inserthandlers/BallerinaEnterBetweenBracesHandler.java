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

package io.ballerina.plugins.idea.editor.inserthandlers;

import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegateAdapter;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import io.ballerina.plugins.idea.BallerinaLanguage;
import io.ballerina.plugins.idea.psi.BallerinaTypeDefinition;
import org.jetbrains.annotations.NotNull;

/**
 * Handles the enter key press in braces.
 */
public class BallerinaEnterBetweenBracesHandler extends EnterHandlerDelegateAdapter {

    @Override
    public Result postProcessEnter(@NotNull PsiFile file, @NotNull Editor editor, @NotNull DataContext dataContext) {
        if (!file.getLanguage().is(BallerinaLanguage.INSTANCE)) {
            return Result.Continue;
        }
        // We need to save the file before checking. Otherwise issues can occur when we press enter in a string.
        Project project = file.getProject();
        PsiDocumentManager.getInstance(project).commitDocument(editor.getDocument());

        // Get the offset of the caret.
        int caretOffset = editor.getCaretModel().getOffset();
        // Get the element at the offset.
        PsiElement element = file.findElementAt(caretOffset);
        // If the element is null, return.
        if (element == null) {
            return Result.Continue;
        }
        // Check whether the semicolon is needed.
        if (needToInsertSemicolon(element)) {
            PsiElement definition = PsiTreeUtil.getParentOfType(element, BallerinaTypeDefinition.class);

            if (definition == null) {
                return Result.Continue;
            }
            // Get the end offset of the type definition.
            int endOffset = definition.getTextRange().getEndOffset();
            // Calculate the caret shift.
            int caretShift = endOffset - caretOffset;
            // Move the caret to the right.
            EditorModificationUtil.moveCaretRelatively(editor, caretShift);
            // Insert the semicolon and move the caret back to the original position.
            EditorModificationUtil.insertStringAtCaret(editor, ";", false, -(caretShift + 1));
            // Commit the document.
            PsiDocumentManager.getInstance(project).commitDocument(editor.getDocument());
        }

        Document doc = editor.getDocument();
        LogicalPosition position = editor.getCaretModel().getLogicalPosition();
        int lineStartOff = doc.getLineStartOffset(position.line - 1);
        int lineEndOff = doc.getLineEndOffset(position.line - 1);
        // If the previous line ends with left brace.
        if (doc.getText(new TextRange(lineStartOff, lineEndOff)).trim().endsWith("{")) {
            // Adds indentation to the caret.
            EditorModificationUtil.insertStringAtCaret(editor, "    ", false, 4);
            // Commit the document.
            PsiDocumentManager.getInstance(project).commitDocument(editor.getDocument());
        }

        return Result.Continue;
    }

    private boolean needToInsertSemicolon(PsiElement element) {
        // Check for type definition.
        BallerinaTypeDefinition typeDefinition = PsiTreeUtil.getParentOfType(element, BallerinaTypeDefinition.class);
        // Check whether the type definition has a semicolon.
        return typeDefinition != null && typeDefinition.getSemicolon() == null;
    }
}
