package org.intellij.sdk.language;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.psi.BallerinaTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BallerinaBraceMatcher implements PairedBraceMatcher {

    private static final BracePair[] pairs = new BracePair[]{
            new BracePair(BallerinaTypes.OPEN_PAREN_TOKEN, BallerinaTypes.CLOSE_PAREN_TOKEN, true),
            new BracePair(BallerinaTypes.OPEN_BRACE_PIPE_TOKEN, BallerinaTypes.CLOSE_BRACE_PIPE_TOKEN, true),
            new BracePair(BallerinaTypes.OPEN_NESTED_BRACE_PIPE_TOKEN, BallerinaTypes.CLOSE_NESTED_BRACE_PIPE_TOKEN, false),
            new BracePair(BallerinaTypes.IGNORED_OPEN_BRACE_PIPE_TOKEN, BallerinaTypes.IGNORED_CLOSE_BRACE_PIPE_TOKEN, false),
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
            // Check for specific parent types if needed, similar to the original implementation
            // For example, check if parent is a function, class, or other code block

            parent = parent.getParent();
        }

        // Once the desired parent is found, return its start offset
        if (parent != null) {
            return parent.getTextRange().getStartOffset();
        }

        return openingBraceOffset;
    }
}
