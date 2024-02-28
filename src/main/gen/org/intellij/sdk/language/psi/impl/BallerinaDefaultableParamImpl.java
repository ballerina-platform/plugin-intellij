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

public class BallerinaDefaultableParamImpl extends ASTWrapperPsiElement implements BallerinaDefaultableParam {

  public BallerinaDefaultableParamImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitDefaultableParam(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaAnnots getAnnots() {
    return findChildByClass(BallerinaAnnots.class);
  }

  @Override
  @Nullable
  public BallerinaDefaultExpression getDefaultExpression() {
    return findChildByClass(BallerinaDefaultExpression.class);
  }

  @Override
  @Nullable
  public BallerinaInferredTypedescDefault getInferredTypedescDefault() {
    return findChildByClass(BallerinaInferredTypedescDefault.class);
  }

  @Override
  @Nullable
  public BallerinaParamName getParamName() {
    return findChildByClass(BallerinaParamName.class);
  }

  @Override
  @NotNull
  public BallerinaTypeDescriptor getTypeDescriptor() {
    return findNotNullChildByClass(BallerinaTypeDescriptor.class);
  }

}
