/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com)
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

package org.intellij.sdk.language.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.BallerinaLexerAdapter;
import org.intellij.sdk.language.psi.BallerinaTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static org.intellij.sdk.language.psi.BallerinaTokenSets.CONTEXTUALS;
import static org.intellij.sdk.language.psi.BallerinaTokenSets.DOCUMENTATIONS;
import static org.intellij.sdk.language.psi.BallerinaTokenSets.KEYWORDS;
import static org.intellij.sdk.language.psi.BallerinaTokenSets.LITERALS;
import static org.intellij.sdk.language.psi.BallerinaTokenSets.OPERATORS;
import static org.intellij.sdk.language.psi.BallerinaTokenSets.SEPARATORS;
import static org.intellij.sdk.language.psi.BallerinaTokenSets.TYPES;

/**
 * Implements syntax highlighting for the Ballerina language in IntelliJ IDEs.
 * Defines coloring schemes for various token types.
 *
 * @since 2.0.0
 */
public class BallerinaSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("BAL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey TYPE =
            createTextAttributesKey("BAL_TYPE", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey CONTEXTUAL =
            createTextAttributesKey("BAL_CONTEXT", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey SEPARATOR =
            createTextAttributesKey("BAL_SEPARATOR", DefaultLanguageHighlighterColors.BRACKETS);

    public static final TextAttributesKey OPERATOR =
            createTextAttributesKey("BAL_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);

    public static final TextAttributesKey DOCUMENTATION =
            createTextAttributesKey("BAL_DOCREF", DefaultLanguageHighlighterColors.DOC_COMMENT);

    public static final TextAttributesKey LITERAL =
            createTextAttributesKey("BAL_LITERAL", DefaultLanguageHighlighterColors.NUMBER);

    public static final TextAttributesKey IDENTIFYER =
            createTextAttributesKey("BAL_IDENTIFYER", DefaultLanguageHighlighterColors.FUNCTION_CALL);

    public static final TextAttributesKey STRING =
            createTextAttributesKey("BAL_STRING", DefaultLanguageHighlighterColors.STRING);

    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("BAL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);

    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("BAL_BAD", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] TYPE_KEYS = new TextAttributesKey[]{TYPE};
    private static final TextAttributesKey[] CONTEXTUAL_KEYS = new TextAttributesKey[]{CONTEXTUAL};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};
    private static final TextAttributesKey[] DOCUMENTATION_KEYS = new TextAttributesKey[]{DOCUMENTATION};
    private static final TextAttributesKey[] LITERAL_KEYS = new TextAttributesKey[]{LITERAL};
    private static final TextAttributesKey[] IDENTIFYER_KEYS = new TextAttributesKey[]{IDENTIFYER};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] BAD_CHARACTER_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {

        return new BallerinaLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {

        if (TYPES.contains(tokenType)) {
            return TYPE_KEYS;
        } else if (KEYWORDS.contains(tokenType)) {
            return KEYWORD_KEYS;
        } else if (tokenType.equals(BallerinaTypes.PARAMETER_NAME)) {
            return TYPE_KEYS;
        } else if (DOCUMENTATIONS.contains(tokenType)) {
            return DOCUMENTATION_KEYS;
        } else if (CONTEXTUALS.contains(tokenType)) {
            return CONTEXTUAL_KEYS;
        } else if (SEPARATORS.contains(tokenType)) {
            return SEPARATOR_KEYS;
        } else if (OPERATORS.contains(tokenType)) {
            return OPERATOR_KEYS;
        } else if (tokenType.equals(BallerinaTypes.STRING_LITERAL_TOKEN)) {
            return STRING_KEYS;
        } else if (tokenType.equals(BallerinaTypes.TEMPLATE_STRING)) {
            return STRING_KEYS;
        } else if (tokenType.equals(BallerinaTypes.IDENTIFIER_TOKEN)) {
            return IDENTIFYER_KEYS;
        } else if (LITERALS.contains(tokenType)) {
            return LITERAL_KEYS;
        } else if (tokenType.equals(BallerinaTypes.COMMENT_MINUTIAE)) {
            return COMMENT_KEYS;
        }
        return EMPTY_KEYS;
    }
}
