// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaListenerDecl extends PsiElement {

  @NotNull
  BallerinaExpression getExpression();

  @NotNull
  BallerinaListenerVariableName getListenerVariableName();

  @NotNull
  BallerinaMetadata getMetadata();

  @Nullable
  BallerinaTypeDescriptor getTypeDescriptor();

}