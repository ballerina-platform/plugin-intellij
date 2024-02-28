package org.intellij.sdk.language;


import com.intellij.codeInsight.highlighting.HighlightErrorFilter;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

/**
 * Error highlighting filter implementation to ignore syntax error added by the plugin parser (Since both syntax and
 * semantic errors are already provided by the ballerina language server).
 */
public class BallerinaHighlightErrorFilter extends HighlightErrorFilter {

    private static final Logger LOG = Logger.getInstance(BallerinaHighlightErrorFilter.class);

    @Override
    public boolean shouldHighlightErrorElement(@NotNull PsiErrorElement element) {
        try {
            final PsiFile containingFile = element.getContainingFile();
            final String extension = containingFile.getVirtualFile().getExtension();
            return containingFile.getLanguage() != BallerinaLanguage.INSTANCE
                    && (extension == null || !extension.equals("bal"));
        } catch (Exception e) {
            LOG.warn("Error occurred when trying to filter plugin error highlighting.");
            return true;
        }
    }
}
