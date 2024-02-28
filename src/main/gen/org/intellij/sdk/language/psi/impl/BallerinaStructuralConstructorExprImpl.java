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

public class BallerinaStructuralConstructorExprImpl extends BallerinaExpressionImpl implements BallerinaStructuralConstructorExpr {

  public BallerinaStructuralConstructorExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitStructuralConstructorExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaListConstructorExpr getListConstructorExpr() {
    return findChildByClass(BallerinaListConstructorExpr.class);
  }

  @Override
  @Nullable
  public BallerinaMappingConstructorExpr getMappingConstructorExpr() {
    return findChildByClass(BallerinaMappingConstructorExpr.class);
  }

  @Override
  @Nullable
  public BallerinaTableConstructorExpr getTableConstructorExpr() {
    return findChildByClass(BallerinaTableConstructorExpr.class);
  }

}
