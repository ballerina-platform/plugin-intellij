package psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.intellij.sdk.language.BallerinaFileType;
import org.intellij.sdk.language.BallerinaLanguage;
import org.jetbrains.annotations.NotNull;

public class BallerinaFile extends PsiFileBase {
    public BallerinaFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, BallerinaLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return BallerinaFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Ballerina File";
    }

}
