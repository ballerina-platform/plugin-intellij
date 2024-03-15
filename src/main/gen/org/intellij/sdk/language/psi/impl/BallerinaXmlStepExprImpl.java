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

public class BallerinaXmlStepExprImpl extends BallerinaExpressionImpl implements BallerinaXmlStepExpr {

  public BallerinaXmlStepExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitXmlStepExpr(this);
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
  @NotNull
  public List<BallerinaXmlStepExtend> getXmlStepExtendList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, BallerinaXmlStepExtend.class);
  }

  @Override
  @NotNull
  public BallerinaXmlStepStart getXmlStepStart() {
    return findNotNullChildByClass(BallerinaXmlStepStart.class);
  }

}