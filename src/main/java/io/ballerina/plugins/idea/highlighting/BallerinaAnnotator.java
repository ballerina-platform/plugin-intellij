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

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import io.ballerina.plugins.idea.psi.BallerinaAbsoluteResourcePath;
import io.ballerina.plugins.idea.psi.BallerinaFunctionDefn;
import io.ballerina.plugins.idea.psi.BallerinaModuleTypeDefn;
import io.ballerina.plugins.idea.psi.BallerinaResourcePath;
import io.ballerina.plugins.idea.psi.BallerinaResourcePathSegmentName;
import io.ballerina.plugins.idea.psi.BallerinaTypes;
import org.jetbrains.annotations.NotNull;

/**
 * Provides custom annotations for Ballerina language elements in IntelliJ.
 *
 * @since 2.0.0
 */
public class BallerinaAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        PsiElement parent = element.getParent();
        if (element instanceof LeafPsiElement) {
            IElementType elementType = ((LeafPsiElement) element).getElementType();
            if (elementType == BallerinaTypes.SEMICOLON_TOKEN || elementType == BallerinaTypes.COMMA_TOKEN) {
                annotateKeyword(element, holder, DefaultLanguageHighlighterColors.KEYWORD, false);
            } else if (elementType == BallerinaTypes.MAP_KEYWORD || elementType == BallerinaTypes.FOREACH_KEYWORD) {
                annotateKeyword(element, holder, DefaultLanguageHighlighterColors.KEYWORD, false);
            } else if (elementType == BallerinaTypes.STRING_LITERAL_TOKEN ||
                    elementType == BallerinaTypes.TEMPLATE_STRING) {
                annotateText(element, holder, DefaultLanguageHighlighterColors.STRING);
            } else if (elementType == BallerinaTypes.INTERPOLATION_START_TOKEN ||
                    elementType == BallerinaTypes.INTERPOLATION_END_TOKEN) {
                holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                        .range(element.getTextRange())
                        .textAttributes(DefaultLanguageHighlighterColors.METADATA)
                        .create();
            } else if (elementType == BallerinaTypes.SLASH_TOKEN &&
                    (parent instanceof BallerinaResourcePath || parent instanceof BallerinaAbsoluteResourcePath)) {
                holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                        .range(element.getTextRange())
                        .textAttributes(DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
                        .create();
            } else if (elementType == BallerinaTypes.MARKDOWN_DOCUMENTATION_TEXT ||
                    elementType == BallerinaTypes.MARKDOWN_DOCUMENTATION_LINE_START ||
                    elementType == BallerinaTypes.PARAMETER_DOCUMENTATION_START) {
                annotateDocumentation(element, holder);
            } else if (elementType == BallerinaTypes.IDENTIFIER_TOKEN) {
                annotateIdentifier(element, holder, parent);
            } else if (elementType == BallerinaTypes.AT_TOKEN) {
                annotateKeyword(element, holder, DefaultLanguageHighlighterColors.METADATA, false);

            } else if (elementType == BallerinaTypes.STRING_TEMPLATE_START_TOKEN) {
                TextRange textRange = element.getTextRange();
                TextRange newTextRange = new TextRange(textRange.getStartOffset(), textRange.getEndOffset() - 1);

                holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                        .range(newTextRange)
                        .textAttributes(DefaultLanguageHighlighterColors.KEYWORD)
                        .create();
            } else if (elementType == BallerinaTypes.RETURN_PARAMETER_DOCUMENTATION_START) {
                String tokenText = element.getText();
                int returnStart = tokenText.indexOf("return");

                if (returnStart >= 0) {
                    TextRange entireRange = element.getTextRange();
                    TextRange returnRange = new TextRange(entireRange.getStartOffset() + returnStart,
                            entireRange.getStartOffset() + returnStart +
                                    "return".length());

                    holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                            .range(returnRange)
                            .textAttributes(DefaultLanguageHighlighterColors.DOC_COMMENT_TAG)
                            .create();
                }
            }

        }
    }

    private void annotateKeyword(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                 @NotNull TextAttributesKey textAttributesKey, boolean excludeEndChar) {
        TextRange textRange = element.getTextRange();
        TextRange newTextRange =
                new TextRange(textRange.getStartOffset(), textRange.getEndOffset() - (excludeEndChar ? 1 : 0));
        holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                .range(newTextRange)
                .textAttributes(textAttributesKey)
                .create();
    }

    private void annotateText(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                              @NotNull TextAttributesKey textAttributesKey) {
        holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                .range(element.getTextRange())
                .textAttributes(textAttributesKey)
                .create();
    }

    private void annotateDocumentation(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                .range(element.getTextRange())
                .textAttributes(DefaultLanguageHighlighterColors.DOC_COMMENT)
                .create();
    }

    private void annotateIdentifier(@NotNull PsiElement element, @NotNull AnnotationHolder holder, PsiElement parent) {
        if (isSpecialParentType(parent)) {
            annotateKeyword(element, holder, DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
        } else {
            PsiElement previousSibling = getPreviousNonWhiteSpaceSibling(element);
            if (previousSibling != null) {
                handlePreviousSiblingAnnotations(element, holder, previousSibling);
            }
        }
    }

    private boolean isSpecialParentType(PsiElement parent) {
        return parent instanceof BallerinaModuleTypeDefn || parent instanceof BallerinaResourcePathSegmentName ||
                parent instanceof BallerinaFunctionDefn;
    }

    private void handlePreviousSiblingAnnotations(PsiElement element, AnnotationHolder holder,
                                                  PsiElement previousSibling) {
        String previousSiblingText = previousSibling.toString();
        if (isSpecialPreviousSibling(previousSiblingText)) {
            annotateKeyword(element, holder, DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
        } else if (previousSiblingText.equals("PsiElement(BallerinaTokenType.AT_TOKEN)")) {
            annotateKeyword(element, holder, DefaultLanguageHighlighterColors.METADATA);
        } else if (isAtTokenWithColon(previousSibling)) {
            annotateKeyword(element, holder, DefaultLanguageHighlighterColors.METADATA);
        }
    }

    private boolean isSpecialPreviousSibling(String previousSiblingText) {
        return previousSiblingText.equals("PsiElement(BallerinaTokenType.FUNCTION_KEYWORD)") ||
                previousSiblingText.equals("PsiElement(BallerinaTokenType.ENUM_KEYWORD)") ||
                previousSiblingText.equals("PsiElement(BallerinaTokenType.TYPE_KEYWORD)") ||
                previousSiblingText.equals("PsiElement(BallerinaTokenType.CLASS_KEYWORD)");
    }

    private boolean isAtTokenWithColon(PsiElement previousSibling) {
        PsiElement colonSibling = getPreviousNonWhiteSpaceSibling(previousSibling);
        PsiElement atSibling = colonSibling != null ? getPreviousNonWhiteSpaceSibling(colonSibling) : null;
        return previousSibling.toString().equals("PsiElement(BallerinaTokenType.COLON_TOKEN)") &&
                colonSibling != null &&
                colonSibling.toString().equals("PsiElement(BallerinaTokenType.IDENTIFIER_TOKEN)") &&
                atSibling != null && atSibling.toString().equals("PsiElement(BallerinaTokenType.AT_TOKEN)");
    }

    private PsiElement getPreviousNonWhiteSpaceSibling(PsiElement element) {
        PsiElement sibling = element.getPrevSibling();
        while (sibling instanceof PsiWhiteSpace) {
            sibling = sibling.getPrevSibling();
        }
        return sibling;
    }

    private void annotateKeyword(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                 TextAttributesKey color) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.getTextRange())
                .textAttributes(color)
                .create();
    }

}
