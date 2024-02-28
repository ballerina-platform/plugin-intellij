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

public class BallerinaSimpleConstExprImpl extends ASTWrapperPsiElement implements BallerinaSimpleConstExpr {

  public BallerinaSimpleConstExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitSimpleConstExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaSign getSign() {
    return findChildByClass(BallerinaSign.class);
  }

  @Override
  @Nullable
  public BallerinaBooleanLiteral getBooleanLiteral() {
    return findChildByClass(BallerinaBooleanLiteral.class);
  }

  @Override
  @Nullable
  public BallerinaConstantReferenceExpr getConstantReferenceExpr() {
    return findChildByClass(BallerinaConstantReferenceExpr.class);
  }

  @Override
  @Nullable
  public BallerinaFloatingPointLiteral getFloatingPointLiteral() {
    return findChildByClass(BallerinaFloatingPointLiteral.class);
  }

  @Override
  @Nullable
  public BallerinaIntLiteral getIntLiteral() {
    return findChildByClass(BallerinaIntLiteral.class);
  }

  @Override
  @Nullable
  public BallerinaNilLiteral getNilLiteral() {
    return findChildByClass(BallerinaNilLiteral.class);
  }

  @Override
  @Nullable
  public BallerinaStringLiteral getStringLiteral() {
    return findChildByClass(BallerinaStringLiteral.class);
  }

}
