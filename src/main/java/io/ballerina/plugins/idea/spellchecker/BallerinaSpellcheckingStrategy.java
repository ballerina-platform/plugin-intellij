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

package io.ballerina.plugins.idea.spellchecker;

import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import io.ballerina.plugins.idea.psi.BallerinaTypes;
import org.jetbrains.annotations.NotNull;

/**
 * Provides spell checking strategy for Ballerina files.
 *
 * @since 2.0.0
 */
public class BallerinaSpellcheckingStrategy extends SpellcheckingStrategy {

    @NotNull
    @Override
    public Tokenizer<?> getTokenizer(PsiElement element) {
        if (element.getNode().getElementType() == BallerinaTypes.IDENTIFIER_TOKEN) {
            return new BallerinaIdentifierTokenizer();
        } else if (element instanceof PsiComment) {
            return new BallerinaCommentTokenizer();
        } else if (element.getNode().getElementType() == BallerinaTypes.MARKDOWN_DOCUMENTATION_TEXT) {
            return new BallerinaMarkdownTextTokenizer();
        }
        return EMPTY_TOKENIZER;
    }
}
