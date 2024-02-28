// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaResourceMethodDefn extends PsiElement {

  @Nullable
  BallerinaFunctionSignature getFunctionSignature();

  @NotNull
  BallerinaMetadata getMetadata();

  @Nullable
  BallerinaMethodDefnBody getMethodDefnBody();

  @Nullable
  BallerinaResourceMethodName getResourceMethodName();

  @NotNull
  BallerinaResourceMethodQuals getResourceMethodQuals();

  @Nullable
  BallerinaResourcePath getResourcePath();

}
