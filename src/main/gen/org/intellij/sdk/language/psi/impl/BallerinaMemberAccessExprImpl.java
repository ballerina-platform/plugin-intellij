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

public class BallerinaMemberAccessExprImpl extends BallerinaExpressionImpl implements BallerinaMemberAccessExpr {

  public BallerinaMemberAccessExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitMemberAccessExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public BallerinaExpression getExpression() {
    return findNotNullChildByClass(BallerinaExpression.class);
  }

  @Override
  @Nullable
  public BallerinaKeyExpression getKeyExpression() {
    return findChildByClass(BallerinaKeyExpression.class);
  }

  @Override
  @Nullable
  public BallerinaMultiKeyExpression getMultiKeyExpression() {
    return findChildByClass(BallerinaMultiKeyExpression.class);
  }

}
