// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaResourceMethodDecl extends PsiElement {

  @NotNull
  BallerinaFunctionSignature getFunctionSignature();

  @NotNull
  BallerinaMetadata getMetadata();

  @NotNull
  BallerinaResourceMethodName getResourceMethodName();

  @NotNull
  BallerinaResourceMethodQuals getResourceMethodQuals();

  @NotNull
  BallerinaResourcePath getResourcePath();

}
