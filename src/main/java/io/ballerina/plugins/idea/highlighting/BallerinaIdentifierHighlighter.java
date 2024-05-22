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

package io.ballerina.plugins.idea.highlighting;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.HighlighterLayer;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import io.ballerina.plugins.idea.psi.BallerinaTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;

/**
 * Highlight the same identifiers in the file and add information to the error stripe.
 *
 * @since 2.0.0
 */
public class BallerinaIdentifierHighlighter implements CaretListener {

    private final Project project;

    public BallerinaIdentifierHighlighter(Project project) {
        this.project = project;
    }

    @Override
    public void caretPositionChanged(@NotNull CaretEvent event) {
        Editor editor = event.getEditor();
        PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
        if (psiFile == null) {
            return;
        }

        Caret caret = event.getCaret();
        if (caret == null) {
            return;
        }

        int offset = caret.getOffset();
        PsiElement element = psiFile.findElementAt(offset);

        if (!(element != null && element.getNode().getElementType() == BallerinaTypes.IDENTIFIER_TOKEN)) {
            clearHighlights(editor);
            return;
        }

        String identifierName = element.getText();
        PsiElement [] elements = PsiTreeUtil.collectElements(psiFile,
                elem -> elem.getNode().getElementType() == BallerinaTypes.IDENTIFIER_TOKEN
                        && elem.getText().equals(identifierName));
        clearHighlights(editor);
        for (PsiElement elem : elements) {
            highlightElement(editor, elem);
        }
    }

    private void highlightElement(Editor editor, PsiElement element) {
        Color color = EditorColorsManager.getInstance().getSchemeForCurrentUITheme().getDefaultForeground();
        int transparency = 50;
        color = new Color(color.getRed(), color.getGreen(), color.getBlue(), transparency);

        TextRange range = element.getTextRange();
        TextAttributes attributes = new TextAttributes();
        attributes.setBackgroundColor(color);
        attributes.setEffectType(EffectType.BOXED);

        MarkupModel markupModel = editor.getMarkupModel();
        RangeHighlighter highlighter = markupModel.addRangeHighlighter(range.getStartOffset(), range.getEndOffset(),
                HighlighterLayer.SELECTION, attributes, HighlighterTargetArea.EXACT_RANGE);

        highlighter.setErrorStripeMarkColor(color);
        highlighter.setErrorStripeTooltip(element.getText());
    }

    private void clearHighlights(Editor editor) {
        MarkupModel markupModel = editor.getMarkupModel();
        markupModel.removeAllHighlighters();
    }
}
