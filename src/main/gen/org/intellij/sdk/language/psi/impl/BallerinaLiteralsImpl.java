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

public class BallerinaLiteralsImpl extends BallerinaExpressionImpl implements BallerinaLiterals {

  public BallerinaLiteralsImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitLiterals(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaBooleanLiteral getBooleanLiteral() {
    return findChildByClass(BallerinaBooleanLiteral.class);
  }

  @Override
  @Nullable
  public BallerinaByteArrayLiteral getByteArrayLiteral() {
    return findChildByClass(BallerinaByteArrayLiteral.class);
  }

  @Override
  @Nullable
  public BallerinaNilLiteral getNilLiteral() {
    return findChildByClass(BallerinaNilLiteral.class);
  }

  @Override
  @Nullable
  public BallerinaNumericLiteral getNumericLiteral() {
    return findChildByClass(BallerinaNumericLiteral.class);
  }

  @Override
  @Nullable
  public BallerinaStringLiteral getStringLiteral() {
    return findChildByClass(BallerinaStringLiteral.class);
  }

}
