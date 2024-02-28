// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaMethodDefn extends PsiElement {

  @NotNull
  BallerinaFunctionSignature getFunctionSignature();

  @NotNull
  BallerinaMetadata getMetadata();

  @NotNull
  BallerinaMethodDefnBody getMethodDefnBody();

  @NotNull
  BallerinaMethodQuals getMethodQuals();

  @Nullable
  BallerinaObjectVisibilityQual getObjectVisibilityQual();

  @Nullable
  BallerinaSpecialMethodName getSpecialMethodName();

}
