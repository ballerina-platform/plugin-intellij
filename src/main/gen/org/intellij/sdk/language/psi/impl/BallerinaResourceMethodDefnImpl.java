// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.sdk.language.psi.BallerinaTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.intellij.sdk.language.psi.*;

public class BallerinaResourceMethodDefnImpl extends ASTWrapperPsiElement implements BallerinaResourceMethodDefn {

  public BallerinaResourceMethodDefnImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitResourceMethodDefn(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaFunctionSignature getFunctionSignature() {
    return findChildByClass(BallerinaFunctionSignature.class);
  }

  @Override
  @NotNull
  public BallerinaMetadata getMetadata() {
    return findNotNullChildByClass(BallerinaMetadata.class);
  }

  @Override
  @Nullable
  public BallerinaMethodDefnBody getMethodDefnBody() {
    return findChildByClass(BallerinaMethodDefnBody.class);
  }

  @Override
  @Nullable
  public BallerinaResourceMethodName getResourceMethodName() {
    return findChildByClass(BallerinaResourceMethodName.class);
  }

  @Override
  @NotNull
  public BallerinaResourceMethodQuals getResourceMethodQuals() {
    return findNotNullChildByClass(BallerinaResourceMethodQuals.class);
  }

  @Override
  @Nullable
  public BallerinaResourcePath getResourcePath() {
    return findChildByClass(BallerinaResourcePath.class);
  }

}
