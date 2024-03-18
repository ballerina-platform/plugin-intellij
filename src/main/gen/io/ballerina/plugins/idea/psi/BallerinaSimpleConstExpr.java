// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaSimpleConstExpr extends PsiElement {

  @Nullable
  BallerinaSign getSign();

  @Nullable
  BallerinaBooleanLiteral getBooleanLiteral();

  @Nullable
  BallerinaConstantReferenceExpr getConstantReferenceExpr();

  @Nullable
  BallerinaFloatingPointLiteral getFloatingPointLiteral();

  @Nullable
  BallerinaIntLiteral getIntLiteral();

  @Nullable
  BallerinaNilLiteral getNilLiteral();

  @Nullable
  BallerinaStringLiteral getStringLiteral();

}
