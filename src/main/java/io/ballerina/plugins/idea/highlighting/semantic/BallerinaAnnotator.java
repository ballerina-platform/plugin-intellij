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

package io.ballerina.plugins.idea.highlighting.semantic;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import io.ballerina.plugins.idea.highlighting.syntax.BallerinaSyntaxHighlightingColors;
import io.ballerina.plugins.idea.psi.BallerinaAlias;
import io.ballerina.plugins.idea.psi.BallerinaAnnotationAttachment;
import io.ballerina.plugins.idea.psi.BallerinaAnyIdentifierName;
import io.ballerina.plugins.idea.psi.BallerinaCompletePackageName;
import io.ballerina.plugins.idea.psi.BallerinaFloatingPointLiteral;
import io.ballerina.plugins.idea.psi.BallerinaFunctionNameReference;
import io.ballerina.plugins.idea.psi.BallerinaGlobalVariableDefinition;
import io.ballerina.plugins.idea.psi.BallerinaIntegerLiteral;
import io.ballerina.plugins.idea.psi.BallerinaInvocation;
import io.ballerina.plugins.idea.psi.BallerinaNameReference;
import io.ballerina.plugins.idea.psi.BallerinaObjectMethod;
import io.ballerina.plugins.idea.psi.BallerinaPackageReference;
import io.ballerina.plugins.idea.psi.BallerinaRecordKey;
import io.ballerina.plugins.idea.psi.BallerinaServiceDefinition;
import io.ballerina.plugins.idea.psi.BallerinaTableColumn;
import io.ballerina.plugins.idea.psi.BallerinaTypeDefinition;
import io.ballerina.plugins.idea.psi.BallerinaTypes;
import io.ballerina.plugins.idea.psi.BallerinaWorkerDefinition;
import org.jetbrains.annotations.NotNull;

/**
 * Handles annotating text in the runtime.
 */
public class BallerinaAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        PsiElement parent = element.getParent();
        if (element instanceof BallerinaNameReference) {
            if (parent instanceof BallerinaAnnotationAttachment) {
                Annotation annotation = holder.createInfoAnnotation(element, null);
                annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.ANNOTATION);
                return;
            }
            PsiReference reference = ((BallerinaNameReference) element).getIdentifier().getReference();
            if (reference != null) {
                PsiElement resolvedElement = reference.resolve();
                if ((resolvedElement != null) && (resolvedElement
                        .getParent() instanceof BallerinaGlobalVariableDefinition)) {
                    Annotation annotation = holder.createInfoAnnotation(element, null);
                    annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.GLOBAL_VARIABLE);
                }
            }
        } else if (element instanceof BallerinaPackageReference) {
            // If the package reference resides inside a annotation attachment, package reference should also be
            // highlighted as a part of the annotation.
            if (parent != null && parent.getParent() instanceof BallerinaAnnotationAttachment) {
                Annotation annotation = holder.createInfoAnnotation(element, null);
                annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.ANNOTATION);
            } else {
                annotateKeyword(element, holder, BallerinaSyntaxHighlightingColors.ENTITY_NAME, false);
            }
        } else if (element instanceof LeafPsiElement) {
            IElementType elementType = ((LeafPsiElement) element).getElementType();
            if (elementType == BallerinaTypes.AT || elementType == BallerinaTypes.ANNOTATION_ACCESS) {
                if (parent instanceof BallerinaAnnotationAttachment) {
                    Annotation annotation = holder.createInfoAnnotation(element, null);
                    annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.ANNOTATION);
                }
            } else if (elementType == BallerinaTypes.SEMICOLON || elementType == BallerinaTypes.COMMA) {
                annotateKeyword(element, holder, BallerinaSyntaxHighlightingColors.KEYWORD, false);
            } else if (elementType == BallerinaTypes.MAP || elementType == BallerinaTypes.FOREACH) {
                if (parent.getNode().getElementType() == BallerinaTypes.RESERVED_WORD) {
                    //if the context is an iterable operation, skips highlighting as a keyword
                    if (parent.getParent() != null
                            && parent.getParent().getNode().getElementType() == BallerinaTypes.ANY_IDENTIFIER_NAME) {
                        annotateKeyword(element, holder, BallerinaSyntaxHighlightingColors.DEFAULT, false);
                    }
                } else {
                    annotateKeyword(element, holder, false);
                }
            } else if (elementType == BallerinaTypes.STRING_TEMPLATE_LITERAL_START
                    || elementType == BallerinaTypes.XML_LITERAL_START) {
                annotateKeyword(element, holder, true);
                annotateTemplateStart(element, holder);
            } else if (elementType == BallerinaTypes.STRING_TEMPLATE_LITERAL_END
                    || elementType == BallerinaTypes.XML_LITERAL_END
                    || elementType == BallerinaTypes.XML_ALL_CHAR) {
                annotateText(element, holder);
            } else if (elementType == BallerinaTypes.STRING_TEMPLATE_TEXT ||
                    elementType == BallerinaTypes.QUOTED_STRING_LITERAL) {
                annotateText(element, holder);
                // Todo - restore after adding xml rules
//            } else if (elementType == BallerinaTypes.XML_TEMPLATE_TEXT
//                    || elementType == BallerinaTypes.XML_SINGLE_QUOTED_TEMPLATE_STRING
//                    || elementType == BallerinaTypes.XML_DOUBLE_QUOTED_TEMPLATE_STRING
//                    || elementType == BallerinaTypes.XML_PI_TEMPLATE_TEXT
//                    || elementType == BallerinaTypes.XML_COMMENT_TEMPLATE_TEXT) {
//                annotateExpressionTemplateStart(element, holder);
            } else if (elementType == BallerinaTypes.STRING_TEMPLATE_EXPRESSION_START) {
                TextRange textRange = element.getTextRange();
                int startOffset = textRange.getStartOffset() + element.getText().indexOf('$');
                // Highlights xml plain text.
                TextRange newTextRange2 = new TextRange(textRange.getStartOffset(), startOffset);
                Annotation annotation2 = holder.createInfoAnnotation(newTextRange2, null);
                annotation2.setTextAttributes(BallerinaSyntaxHighlightingColors.STRING);
                // Highlights interpolation start($).
                TextRange newTextRange = new TextRange(startOffset, startOffset + 1);
                Annotation annotation = holder.createInfoAnnotation(newTextRange, null);
                annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.TEMPLATE_LANGUAGE_COLOR);
            } else if (elementType == BallerinaTypes.SINGLE_BACKTICK_CONTENT
                    || elementType == BallerinaTypes.DOUBLE_BACKTICK_CONTENT
                    || elementType == BallerinaTypes.TRIPLE_BACKTICK_CONTENT) {
                annotateInlineCode(element, holder);
            } else if (elementType == BallerinaTypes.MARKDOWN_DOCUMENTATION_TEXT) {
                Annotation annotation = holder.createInfoAnnotation(element, null);
                annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.DOCUMENTATION);
            } else if (elementType == BallerinaTypes.MARKDOWN_DOCUMENTATION_LINE_START) {
                TextRange textRange = element.getTextRange();
                // Highlights "#"
                int startOffset = textRange.getStartOffset() + ((LeafPsiElement) element).getText().indexOf('#');
                TextRange newTextRange = new TextRange(startOffset, startOffset + 1);
                Annotation annotation = holder.createInfoAnnotation(newTextRange, null);
                annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.DOCUMENTATION);
            } else if (elementType == BallerinaTypes.PARAMETER_DOCUMENTATION_START) {
                TextRange textRange = element.getTextRange();
                // Highlights "#"
                int startOffset = textRange.getStartOffset() + ((LeafPsiElement) element).getText().indexOf("#");
                TextRange newTextRange = new TextRange(startOffset, startOffset + 1);
                Annotation annotation = holder.createInfoAnnotation(newTextRange, null);
                annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.DOCUMENTATION);
                // Highlights "+"
                startOffset = textRange.getStartOffset() + ((LeafPsiElement) element).getText().indexOf("+");
                newTextRange = new TextRange(startOffset, startOffset + 1);
                annotation = holder.createInfoAnnotation(newTextRange, null);
                annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.DOCUMENTATION_INLINE_CODE);
            } else if (elementType == BallerinaTypes.PARAMETER_NAME
                    || elementType == BallerinaTypes.DESCRIPTION_SEPARATOR) {
                // Highlights input parameter name and "-" token
                TextRange textRange = element.getTextRange();
                Annotation annotation = holder.createInfoAnnotation(textRange, null);
                annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.DOCUMENTATION_INLINE_CODE);
            } else if (elementType == BallerinaTypes.RETURN_PARAMETER_DOCUMENTATION_START) {
                TextRange textRange = element.getTextRange();
                // Highlights "#"
                int startOffset = textRange.getStartOffset() + ((LeafPsiElement) element).getText().indexOf("#");
                TextRange newTextRange = new TextRange(startOffset, startOffset + 1);
                Annotation annotation = holder.createInfoAnnotation(newTextRange, null);
                annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.DOCUMENTATION);
                // Highlights "+"
                startOffset = textRange.getStartOffset() + ((LeafPsiElement) element).getText().indexOf("+");
                newTextRange = new TextRange(startOffset, startOffset + 1);
                annotation = holder.createInfoAnnotation(newTextRange, null);
                annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.DOCUMENTATION_INLINE_CODE);
                // Highlights "return" and "-"
                newTextRange = new TextRange(
                        textRange.getStartOffset() + ((LeafPsiElement) element).getText().indexOf("return"),
                        textRange.getEndOffset());
                annotation = holder.createInfoAnnotation(newTextRange, null);
                annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.DOCUMENTATION_INLINE_CODE);
            } else if (elementType == BallerinaTypes.SINGLE_BACKTICK_MARKDOWN_START
                    || elementType == BallerinaTypes.DOUBLE_BACKTICK_MARKDOWN_START
                    || elementType == BallerinaTypes.TRIPLE_BACKTICK_MARKDOWN_START
                    || elementType == BallerinaTypes.SINGLE_BACKTICK_MARKDOWN_END
                    || elementType == BallerinaTypes.DOUBLE_BACKTICK_MARKDOWN_END
                    || elementType == BallerinaTypes.TRIPLE_BACKTICK_MARKDOWN_END) {
                annotateInlineCode(element, holder);
            } else if (elementType == BallerinaTypes.IDENTIFIER) {
                if (parent instanceof BallerinaTableColumn) {
                    if (element.getText().equals("key") && element.getNextSibling() instanceof PsiWhiteSpace) {
                        PsiElement nextElement = element.getNextSibling().getNextSibling();
                        if (nextElement instanceof LeafPsiElement
                                && ((LeafPsiElement) element).getElementType() == BallerinaTypes.IDENTIFIER) {
                            Annotation annotation = holder.createInfoAnnotation(element, null);
                            annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.KEYWORD);
                        }
                    }
                    // Highlights "self" keyword only inside object type contexts.
                } else if ("self".equals(element.getText())) {
                    BallerinaObjectMethod objectContext = PsiTreeUtil
                            .getParentOfType(element, BallerinaObjectMethod.class);
                    if (objectContext != null) {
                        Annotation annotation = holder.createInfoAnnotation(element, null);
                        annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.KEYWORD);
                    }
                    // Highlights function names.
                } else if (parent instanceof BallerinaAnyIdentifierName && !(parent
                        .getParent() instanceof BallerinaInvocation) && !(parent
                        .getParent() instanceof BallerinaFunctionNameReference)) {
                    annotateKeyword(element, holder, BallerinaSyntaxHighlightingColors.ENTITY_NAME, false);
                    // Highlights type names.
                } else if (parent instanceof BallerinaTypeDefinition) {
                    annotateKeyword(element, holder, BallerinaSyntaxHighlightingColors.ENTITY_NAME, false);
                    // Highlights Service names.
                } else if (parent instanceof BallerinaServiceDefinition) {
                    annotateKeyword(element, holder, BallerinaSyntaxHighlightingColors.ENTITY_NAME, false);
                    // Highlights Worker names.
                } else if (parent instanceof BallerinaWorkerDefinition) {
                    annotateKeyword(element, holder, BallerinaSyntaxHighlightingColors.ENTITY_NAME, false);
                    // Highlights package alias.
                } else if (parent instanceof BallerinaAlias) {
                    annotateKeyword(element, holder, BallerinaSyntaxHighlightingColors.ENTITY_NAME, false);
                }
            }
        } else if (element instanceof BallerinaFloatingPointLiteral || element instanceof BallerinaIntegerLiteral) {
            annotateNumber(element, holder);
        } else if (element instanceof BallerinaCompletePackageName) {
            Annotation annotation = holder.createInfoAnnotation(element, null);
            annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.PACKAGE);
            // Todo - restore after adding xml grammar
//        } else if (element instanceof BallerinaXmlItem) {
//            Annotation annotation = holder.createInfoAnnotation(element, null);
//            annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.STRING);
        } else if (element instanceof BallerinaRecordKey) {
            // Todo - Do we need to highlight key?
        }
    }

    private void annotateTemplateStart(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        TextRange textRange = element.getTextRange();
        TextRange newTextRange = new TextRange(textRange.getEndOffset() - 1, textRange.getEndOffset());
        Annotation annotation = holder.createInfoAnnotation(newTextRange, null);
        annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.STRING);
    }

    private void annotateInlineCode(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        Annotation annotation = holder.createInfoAnnotation(element, null);
        annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.DOCUMENTATION_INLINE_CODE);
    }

    private void annotateKeyword(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                 boolean excludeEndChar) {
        TextRange textRange = element.getTextRange();
        TextRange newTextRange = new TextRange(textRange.getStartOffset(),
                textRange.getEndOffset() - (excludeEndChar ? 1 : 0));
        Annotation annotation = holder.createInfoAnnotation(newTextRange, null);
        annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.KEYWORD);
    }

    private void annotateKeyword(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                 @NotNull TextAttributesKey textAttributesKey, boolean excludeEndChar) {
        TextRange textRange = element.getTextRange();
        TextRange newTextRange = new TextRange(textRange.getStartOffset(),
                textRange.getEndOffset() - (excludeEndChar ? 1 : 0));
        Annotation annotation = holder.createInfoAnnotation(newTextRange, null);
        annotation.setTextAttributes(textAttributesKey);
    }

    private void annotateExpressionTemplateStart(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        TextRange textRange = element.getTextRange();
        TextRange newTextRange = new TextRange(textRange.getEndOffset() - 2, textRange.getEndOffset());
        Annotation annotation = holder.createInfoAnnotation(newTextRange, null);
        annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.TEMPLATE_LANGUAGE_COLOR);
        if (textRange.getEndOffset() - 2 > textRange.getStartOffset()) {
            newTextRange = new TextRange(textRange.getStartOffset(), textRange.getEndOffset() - 2);
            annotation = holder.createInfoAnnotation(newTextRange, null);
            annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.STRING);
        }
    }

    private void annotateStringLiteralTemplateEnd(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        Annotation annotation = holder.createInfoAnnotation(element, null);
        annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.TEMPLATE_LANGUAGE_COLOR);
    }

    private void annotateText(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        Annotation annotation = holder.createInfoAnnotation(element, null);
        annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.STRING);
    }

    private void annotateNumber(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        Annotation annotation = holder.createInfoAnnotation(element, null);
        annotation.setTextAttributes(BallerinaSyntaxHighlightingColors.NUMBER);
    }
}
