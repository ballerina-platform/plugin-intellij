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

package org.intellij.sdk.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.intellij.sdk.language.parser.BallerinaParser;
import org.intellij.sdk.language.psi.BallerinaFile;
import org.intellij.sdk.language.psi.BallerinaTokenSets;
import org.intellij.sdk.language.psi.BallerinaTypes;
import org.jetbrains.annotations.NotNull;

/**
 * Defines the parser and lexer setup for Ballerina language support in IntelliJ IDEs.
 * Handles the creation of lexer, parser, and PSI elements for Ballerina files.
 *
 * @since 2.0.0
 */
final class BallerinaParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(BallerinaLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {

        return new BallerinaLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {

        return BallerinaTokenSets.COMMENTS;
    }

    public @NotNull TokenSet getWhitespaceTokens() {

        return BallerinaTokenSets.WHITESPACES;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {

        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {

        return new BallerinaParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {

        return FILE;
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {

        return new BallerinaFile(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {

        return BallerinaTypes.Factory.createElement(node);
    }

}
