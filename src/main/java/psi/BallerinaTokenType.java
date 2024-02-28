package psi;

import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.BallerinaLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class BallerinaTokenType extends IElementType {
    public BallerinaTokenType(@NotNull @NonNls String debugName) {
        super(debugName, BallerinaLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "BallerinaTokenType." + super.toString();
    }

}
