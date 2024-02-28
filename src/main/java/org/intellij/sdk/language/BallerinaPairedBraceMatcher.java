package org.intellij.sdk.language;

import com.intellij.codeInsight.highlighting.PairedBraceMatcherAdapter;
import com.intellij.lang.BracePair;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.annotations.NotNull;

public class BallerinaPairedBraceMatcher extends PairedBraceMatcherAdapter {

    public BallerinaPairedBraceMatcher() {
        super(new BallerinaBraceMatcher(), BallerinaLanguage.INSTANCE);
    }

    @Override
    public boolean isLBraceToken(@NotNull HighlighterIterator iterator, @NotNull CharSequence fileText, @NotNull FileType fileType) {
        return isBrace(iterator, fileText, fileType, true);
    }

    @Override
    public boolean isRBraceToken(@NotNull HighlighterIterator iterator, @NotNull CharSequence fileText, @NotNull FileType fileType) {
        return isBrace(iterator, fileText, fileType, false);
    }

    private boolean isBrace(HighlighterIterator iterator, CharSequence fileText, FileType fileType, boolean left) {
        final BracePair pair = findPair(left, iterator, fileText, fileType);
        return pair != null;
    }
}