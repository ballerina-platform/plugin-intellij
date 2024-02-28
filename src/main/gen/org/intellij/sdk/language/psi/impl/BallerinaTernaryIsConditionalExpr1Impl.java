// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.sdk.language.psi.BallerinaTypes.*;
import org.intellij.sdk.language.psi.*;

public class BallerinaTernaryIsConditionalExpr1Impl extends BallerinaExpressionImpl implements BallerinaTernaryIsConditionalExpr1 {

  public BallerinaTernaryIsConditionalExpr1Impl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitTernaryIsConditionalExpr1(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<BallerinaExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, BallerinaExpression.class);
  }

  @Override
  @NotNull
  public BallerinaTernaryTypeDescriptor getTernaryTypeDescriptor() {
    return findNotNullChildByClass(BallerinaTernaryTypeDescriptor.class);
  }

}
