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

public class BallerinaQueryExprImpl extends BallerinaExpressionImpl implements BallerinaQueryExpr {

  public BallerinaQueryExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitQueryExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaQueryCollectExpr getQueryCollectExpr() {
    return findChildByClass(BallerinaQueryCollectExpr.class);
  }

  @Override
  @Nullable
  public BallerinaQuerySelectExpr getQuerySelectExpr() {
    return findChildByClass(BallerinaQuerySelectExpr.class);
  }

}
