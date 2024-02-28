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

public class BallerinaFunctionDefnBodyImpl extends ASTWrapperPsiElement implements BallerinaFunctionDefnBody {

  public BallerinaFunctionDefnBodyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitFunctionDefnBody(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaTokens getTokens() {
    return findChildByClass(BallerinaTokens.class);
  }

  @Override
  @Nullable
  public BallerinaTokensIgnore getTokensIgnore() {
    return findChildByClass(BallerinaTokensIgnore.class);
  }

  @Override
  @Nullable
  public BallerinaExprFunctionBody getExprFunctionBody() {
    return findChildByClass(BallerinaExprFunctionBody.class);
  }

  @Override
  @Nullable
  public BallerinaExternalFunctionBody getExternalFunctionBody() {
    return findChildByClass(BallerinaExternalFunctionBody.class);
  }

}
