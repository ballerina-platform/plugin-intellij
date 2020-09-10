
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

package io.ballerina.plugins.idea;

import com.intellij.lexer.Lexer;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.search.IndexPatternBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import io.ballerina.plugins.idea.psi.BallerinaFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Index patter builder.
 */
public class BallerinaIndexPatternBuilder implements IndexPatternBuilder {

    @Nullable
    @Override
    public Lexer getIndexingLexer(@NotNull PsiFile file) {
        if (file instanceof BallerinaFile) {
            return ((BallerinaFile) file).getParserDefinition().createLexer(file.getProject());
        }
        return null;
    }

    @Nullable
    @Override
    public TokenSet getCommentTokenSet(@NotNull PsiFile file) {
        return BallerinaParserDefinition.COMMENTS;
    }

    @Override
    public int getCommentStartDelta(IElementType tokenType) {
        return 0;
    }

    @Override
    public int getCommentEndDelta(IElementType tokenType) {
        return 0;
    }
}
