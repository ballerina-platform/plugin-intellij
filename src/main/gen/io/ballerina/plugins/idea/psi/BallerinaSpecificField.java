// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaSpecificField extends PsiElement {

  @Nullable
  BallerinaFieldName getFieldName();

  @Nullable
  BallerinaStringLiteral getStringLiteral();

  @Nullable
  BallerinaValueExpr getValueExpr();

  @Nullable
  BallerinaVariableName getVariableName();

}
