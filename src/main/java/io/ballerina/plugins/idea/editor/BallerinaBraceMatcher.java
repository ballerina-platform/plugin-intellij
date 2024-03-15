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

package io.ballerina.plugins.idea.editor;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import io.ballerina.plugins.idea.psi.BallerinaTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Provides matching for brace pairs in Ballerina language files, supporting code folding, structure highlighting,
 * and navigation within the IntelliJ IDE.
 *
 * @since 2.0.0
 */
public class BallerinaBraceMatcher implements PairedBraceMatcher {

    private static final BracePair[] pairs = new BracePair[]{
            new BracePair(BallerinaTypes.OPEN_PAREN_TOKEN, BallerinaTypes.CLOSE_PAREN_TOKEN, true),
            new BracePair(BallerinaTypes.OPEN_BRACE_PIPE_TOKEN, BallerinaTypes.CLOSE_BRACE_PIPE_TOKEN, true),
            new BracePair(BallerinaTypes.OPEN_NESTED_BRACE_PIPE_TOKEN, BallerinaTypes.CLOSE_NESTED_BRACE_PIPE_TOKEN,
                    false),
            new BracePair(BallerinaTypes.IGNORED_OPEN_BRACE_PIPE_TOKEN, BallerinaTypes.IGNORED_CLOSE_BRACE_PIPE_TOKEN,
                    false),
            new BracePair(BallerinaTypes.OPEN_BRACE_TOKEN, BallerinaTypes.CLOSE_BRACE_TOKEN, true),
            new BracePair(BallerinaTypes.OPEN_BRACKET_TOKEN, BallerinaTypes.CLOSE_BRACKET_TOKEN, true),
            new BracePair(BallerinaTypes.OPEN_NESTED_BRACE_TOKEN, BallerinaTypes.CLOSE_NESTED_BRACE_TOKEN, false),
            new BracePair(BallerinaTypes.IGNORED_OPEN_BRACE_TOKEN, BallerinaTypes.IGNORED_CLOSE_BRACE_TOKEN, false),
            new BracePair(BallerinaTypes.INTERPOLATION_START_TOKEN, BallerinaTypes.INTERPOLATION_END_TOKEN, false),

    };

    @Override
    public BracePair @NotNull [] getPairs() {

        return pairs;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull final IElementType lbraceType,
                                                   @Nullable final IElementType contextType) {

        return true;
    }

    @Override
    public int getCodeConstructStart(final PsiFile file, int openingBraceOffset) {

        PsiElement element = file.findElementAt(openingBraceOffset);
        if (element == null || element instanceof PsiFile) {
            return openingBraceOffset;
        }
        PsiElement parent = element.getParent();

        // Navigate the PSI tree to find the start of the code construct
        while (parent != null && !(parent instanceof PsiFile)) {

            parent = parent.getParent();
        }

        // Once the desired parent is found, return its start offset
        if (parent != null) {
            return parent.getTextRange().getStartOffset();
        }

        return openingBraceOffset;
    }
}
