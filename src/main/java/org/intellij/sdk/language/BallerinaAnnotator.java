package org.intellij.sdk.language;

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
import org.intellij.sdk.language.psi.*;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.highlighting.HighlightErrorFilter;

import java.util.Arrays;
import java.util.Objects;

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
            } else if (elementType == BallerinaTypes.STRING_LITERAL_TOKEN || elementType == BallerinaTypes.TEMPLATE_STRING) {
                annotateText(element, holder, DefaultLanguageHighlighterColors.STRING);
            } else if (elementType == BallerinaTypes.INTERPOLATION_START_TOKEN || elementType == BallerinaTypes.INTERPOLATION_END_TOKEN) {
                holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                        .range(element.getTextRange())
                        .textAttributes(DefaultLanguageHighlighterColors.METADATA)
                        .create();
            } else if (elementType == BallerinaTypes.SINGLE_BACKTICK_CONTENT || elementType == BallerinaTypes.DOUBLE_BACKTICK_CONTENT || elementType == BallerinaTypes.TRIPLE_BACKTICK_CONTENT) {
                annotateInlineCode(element, holder);
            } else if (elementType == BallerinaTypes.SLASH_TOKEN && (parent instanceof BallerinaResourcePath || parent instanceof BallerinaAbsoluteResourcePath)) {
                holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                        .range(element.getTextRange())
                        .textAttributes(DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
                        .create();
            } else if (elementType == BallerinaTypes.MARKDOWN_DOCUMENTATION_TEXT || elementType == BallerinaTypes.MARKDOWN_DOCUMENTATION_LINE_START || elementType == BallerinaTypes.PARAMETER_DOCUMENTATION_START ) {
                annotateDocumentation(element, holder);
            } else if (elementType == BallerinaTypes.PARAMETER_NAME) {
                holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                        .range(element.getTextRange())
                        .textAttributes(DefaultLanguageHighlighterColors.DOC_COMMENT_TAG)
                        .create();
            } else if (elementType == BallerinaTypes.IDENTIFIER_TOKEN) {
                annotateIdentifier(element, holder, parent);
            } else if (elementType == BallerinaTypes.AT_TOKEN){
                annotateKeyword(element, holder, DefaultLanguageHighlighterColors.METADATA, false);

            }else if (elementType == BallerinaTypes.STRING_TEMPLATE_START_TOKEN) {
                TextRange textRange = element.getTextRange();
                // Assuming the backtick is only at the start or end of the token,
                // you adjust the range to exclude the backtick character.
                TextRange newTextRange = new TextRange(textRange.getStartOffset(), textRange.getEndOffset() - 1);

                holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                        .range(newTextRange) // Apply highlighting to the adjusted range, excluding the backtick
                        .textAttributes(DefaultLanguageHighlighterColors.KEYWORD)
                        .create();
            }

            else if (elementType == BallerinaTypes.RETURN_PARAMETER_DOCUMENTATION_START) {
                String tokenText = element.getText();
                int returnStart = tokenText.indexOf("return"); // Find the start index of "return" within the token text

                if (returnStart >= 0) { // Ensure "return" is found within the token text
                    TextRange entireRange = element.getTextRange(); // Get the entire text range of the token
                    TextRange returnRange = new TextRange(entireRange.getStartOffset() + returnStart, entireRange.getStartOffset() + returnStart + "return".length()); // Calculate the text range for "return"

                    holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                            .range(returnRange) // Apply the annotation to the range of "return" only
                            .textAttributes(DefaultLanguageHighlighterColors.DOC_COMMENT_TAG)
                            .create();
                }
            }

        }
    }

    private void annotateKeyword(@NotNull PsiElement element, @NotNull AnnotationHolder holder, @NotNull TextAttributesKey textAttributesKey, boolean excludeEndChar) {
        TextRange textRange = element.getTextRange();
        TextRange newTextRange = new TextRange(textRange.getStartOffset(), textRange.getEndOffset() - (excludeEndChar ? 1 : 0));
        holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                .range(newTextRange)
                .textAttributes(textAttributesKey)
                .create();
    }

    private void annotateText(@NotNull PsiElement element, @NotNull AnnotationHolder holder, @NotNull TextAttributesKey textAttributesKey) {
        holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                .range(element.getTextRange())
                .textAttributes(textAttributesKey)
                .create();
    }

    private void annotateInlineCode(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                .range(element.getTextRange())
                .textAttributes(DefaultLanguageHighlighterColors.DOC_COMMENT_TAG_VALUE)
                .create();
    }

    private void annotateDocumentation(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        holder.newAnnotation(HighlightSeverity.INFORMATION, "")
                .range(element.getTextRange())
                .textAttributes(DefaultLanguageHighlighterColors.DOC_COMMENT)
                .create();
    }

    private PsiElement getPreviousSibling(PsiElement element) {
        PsiElement prevSibling = element.getPrevSibling();
        while (prevSibling != null && prevSibling.toString().equals("PsiWhiteSpace")) {
            prevSibling = prevSibling.getPrevSibling();
        }
        return prevSibling;
    }

    private void annotateIdentifier(@NotNull PsiElement element, @NotNull AnnotationHolder holder, PsiElement parent) {

        if (parent instanceof BallerinaModuleTypeDefn || parent instanceof BallerinaResourcePathSegmentName || parent instanceof BallerinaFunctionDefn) {
            annotateKeyword(element, holder, DefaultLanguageHighlighterColors.FUNCTION_DECLARATION, false);
        } else if (getPreviousSibling(element) != null) {
            if (Objects.requireNonNull(getPreviousSibling(element)).toString().equals("PsiElement(BallerinaTokenType.FUNCTION_KEYWORD)") ||
                    Objects.requireNonNull(getPreviousSibling(element)).toString().equals("PsiElement(BallerinaTokenType.ENUM_KEYWORD)") ||
                    Objects.requireNonNull(getPreviousSibling(element)).toString().equals("PsiElement(BallerinaTokenType.TYPE_KEYWORD)") ||
                    Objects.requireNonNull(getPreviousSibling(element)).toString().equals("PsiElement(BallerinaTokenType.CLASS_KEYWORD)")) {
                annotateKeyword(element, holder, DefaultLanguageHighlighterColors.FUNCTION_DECLARATION, false);
            } else if (Objects.requireNonNull(getPreviousSibling(element)).toString().equals("PsiElement(BallerinaTokenType.AT_TOKEN)")){
                annotateKeyword(element, holder, DefaultLanguageHighlighterColors.METADATA, false);
            } else if (getPreviousSibling(getPreviousSibling(element)) != null && getPreviousSibling(getPreviousSibling(getPreviousSibling(element))) != null &&
                    Objects.requireNonNull(getPreviousSibling(element)).toString().equals("PsiElement(BallerinaTokenType.COLON_TOKEN)") &&
                    Objects.requireNonNull(getPreviousSibling(getPreviousSibling(element))).toString().equals("PsiElement(BallerinaTokenType.IDENTIFIER_TOKEN)") &&
                    Objects.requireNonNull(getPreviousSibling(getPreviousSibling(getPreviousSibling(element)))).toString().equals("PsiElement(BallerinaTokenType.AT_TOKEN)")){
                annotateKeyword(element, holder, DefaultLanguageHighlighterColors.METADATA, false);
            }
        }
    }
}
