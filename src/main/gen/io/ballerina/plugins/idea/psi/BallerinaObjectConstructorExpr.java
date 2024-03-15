// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaObjectConstructorExpr extends BallerinaExpression {

  @Nullable
  BallerinaAnnots getAnnots();

  @NotNull
  BallerinaObjectConstructorBlock getObjectConstructorBlock();

  @NotNull
  BallerinaObjectTypeQuals getObjectTypeQuals();

  @Nullable
  BallerinaTypeReference getTypeReference();

}
