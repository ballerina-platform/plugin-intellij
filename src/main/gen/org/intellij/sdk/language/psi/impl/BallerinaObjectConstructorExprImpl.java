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

public class BallerinaObjectConstructorExprImpl extends BallerinaExpressionImpl implements BallerinaObjectConstructorExpr {

  public BallerinaObjectConstructorExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitObjectConstructorExpr(this);
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
  @NotNull
  public BallerinaObjectConstructorBlock getObjectConstructorBlock() {
    return findNotNullChildByClass(BallerinaObjectConstructorBlock.class);
  }

  @Override
  @NotNull
  public BallerinaObjectTypeQuals getObjectTypeQuals() {
    return findNotNullChildByClass(BallerinaObjectTypeQuals.class);
  }

  @Override
  @Nullable
  public BallerinaTypeReference getTypeReference() {
    return findChildByClass(BallerinaTypeReference.class);
  }

}
