// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.ballerina.plugins.idea.psi.BallerinaTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.ballerina.plugins.idea.psi.*;

public class BallerinaResourceMethodDeclImpl extends ASTWrapperPsiElement implements BallerinaResourceMethodDecl {

  public BallerinaResourceMethodDeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitResourceMethodDecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public BallerinaFunctionSignature getFunctionSignature() {
    return findNotNullChildByClass(BallerinaFunctionSignature.class);
  }

  @Override
  @NotNull
  public BallerinaMetadata getMetadata() {
    return findNotNullChildByClass(BallerinaMetadata.class);
  }

  @Override
  @NotNull
  public BallerinaResourceMethodName getResourceMethodName() {
    return findNotNullChildByClass(BallerinaResourceMethodName.class);
  }

  @Override
  @NotNull
  public BallerinaResourceMethodQuals getResourceMethodQuals() {
    return findNotNullChildByClass(BallerinaResourceMethodQuals.class);
  }

  @Override
  @NotNull
  public BallerinaResourcePath getResourcePath() {
    return findNotNullChildByClass(BallerinaResourcePath.class);
  }

}
