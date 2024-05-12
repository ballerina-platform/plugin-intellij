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

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseMotionListener;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.HighlighterLayer;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;
import io.ballerina.plugins.idea.psi.BallerinaTypes;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.util.Objects;

/**
 * Provides underline effect for identifiers when ctrl key is pressed. This is used when navigating to the definition.
 *
 * @since 2.0.0
 */
public class BallerinaIdentifierUnderline implements EditorMouseMotionListener {

    private RangeHighlighter currentHighlighter = null;
    private Editor lastEditor = null;

    @Override
    public void mouseMoved(EditorMouseEvent e) {
        Editor editor = e.getEditor();
        if ((e.getMouseEvent().getModifiersEx() & InputEvent.CTRL_DOWN_MASK) !=
                InputEvent.CTRL_DOWN_MASK) {
            removeHighlighter();
            return;
        }
        Point point = e.getMouseEvent().getPoint();
        int offset = editor.logicalPositionToOffset(editor.xyToLogicalPosition(point));
        PsiFile psiFile = PsiUtilBase.getPsiFileInEditor(editor, Objects.requireNonNull(editor.getProject()));
        if (psiFile == null) {
            return;
        }
        PsiElement element = psiFile.findElementAt(offset);
        if (element != null && isIdentifier(element)) {
            underlineElement(editor, element);
        } else {
            removeHighlighter();
        }
    }

    private boolean isIdentifier(PsiElement element) {
        return element.getNode().getElementType() == BallerinaTypes.IDENTIFIER_TOKEN;
    }

    private void underlineElement(Editor editor, PsiElement element) {
        removeHighlighter();

        TextAttributes baseAttributes = EditorColorsManager.getInstance().getGlobalScheme()
                .getAttributes(TextAttributesKey.find("DEFAULT_NUMBER"));

        TextAttributes attributes = new TextAttributes();
        attributes.setEffectType(EffectType.LINE_UNDERSCORE);
        attributes.setEffectColor(baseAttributes.getForegroundColor());
        attributes.setForegroundColor(baseAttributes.getForegroundColor());

        currentHighlighter = editor.getMarkupModel()
                .addRangeHighlighter(element.getTextRange().getStartOffset(), element.getTextRange().getEndOffset(),
                        HighlighterLayer.HYPERLINK, attributes, HighlighterTargetArea.EXACT_RANGE);
        lastEditor = editor;
    }

    private void removeHighlighter() {
        if (lastEditor != null && currentHighlighter != null) {
            lastEditor.getMarkupModel().removeHighlighter(currentHighlighter);
            currentHighlighter = null;
        }
    }
}
