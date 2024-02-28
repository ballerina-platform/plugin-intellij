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

public class BallerinaMethodDefnImpl extends ASTWrapperPsiElement implements BallerinaMethodDefn {

  public BallerinaMethodDefnImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitMethodDefn(this);
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
  public BallerinaMethodDefnBody getMethodDefnBody() {
    return findNotNullChildByClass(BallerinaMethodDefnBody.class);
  }

  @Override
  @NotNull
  public BallerinaMethodQuals getMethodQuals() {
    return findNotNullChildByClass(BallerinaMethodQuals.class);
  }

  @Override
  @Nullable
  public BallerinaObjectVisibilityQual getObjectVisibilityQual() {
    return findChildByClass(BallerinaObjectVisibilityQual.class);
  }

  @Override
  @Nullable
  public BallerinaSpecialMethodName getSpecialMethodName() {
    return findChildByClass(BallerinaSpecialMethodName.class);
  }

}
