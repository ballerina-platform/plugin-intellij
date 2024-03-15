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

public class BallerinaNewExprImpl extends BallerinaExpressionImpl implements BallerinaNewExpr {

  public BallerinaNewExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitNewExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaExplicitNewExpr getExplicitNewExpr() {
    return findChildByClass(BallerinaExplicitNewExpr.class);
  }

  @Override
  @Nullable
  public BallerinaImplicitNewExpr getImplicitNewExpr() {
    return findChildByClass(BallerinaImplicitNewExpr.class);
  }

}