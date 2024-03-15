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

import com.intellij.codeInsight.highlighting.PairedBraceMatcherAdapter;
import com.intellij.lang.BracePair;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.openapi.fileTypes.FileType;
import io.ballerina.plugins.idea.BallerinaLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * Implements brace matching for Ballerina language constructs within the IntelliJ IDE, facilitating navigation
 * and editing of paired constructs.
 *
 * @since 2.0.0
 */

public class BallerinaPairedBraceMatcher extends PairedBraceMatcherAdapter {

    public BallerinaPairedBraceMatcher() {

        super(new BallerinaBraceMatcher(), BallerinaLanguage.INSTANCE);
    }

    @Override
    public boolean isLBraceToken(@NotNull HighlighterIterator iterator, @NotNull CharSequence fileText,
                                 @NotNull FileType fileType) {

        return isBrace(iterator, fileText, fileType, true);
    }

    @Override
    public boolean isRBraceToken(@NotNull HighlighterIterator iterator, @NotNull CharSequence fileText,
                                 @NotNull FileType fileType) {

        return isBrace(iterator, fileText, fileType, false);
    }

    private boolean isBrace(HighlighterIterator iterator, CharSequence fileText, FileType fileType, boolean left) {

        final BracePair pair = findPair(left, iterator, fileText, fileType);
        return pair != null;
    }
}
