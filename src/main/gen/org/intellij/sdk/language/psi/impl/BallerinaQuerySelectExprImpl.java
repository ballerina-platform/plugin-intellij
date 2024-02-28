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

public class BallerinaQuerySelectExprImpl extends ASTWrapperPsiElement implements BallerinaQuerySelectExpr {

  public BallerinaQuerySelectExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitQuerySelectExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaOnConflictClause getOnConflictClause() {
    return findChildByClass(BallerinaOnConflictClause.class);
  }

  @Override
  @Nullable
  public BallerinaQueryConstructType getQueryConstructType() {
    return findChildByClass(BallerinaQueryConstructType.class);
  }

  @Override
  @NotNull
  public BallerinaQueryPipeline getQueryPipeline() {
    return findNotNullChildByClass(BallerinaQueryPipeline.class);
  }

  @Override
  @NotNull
  public BallerinaSelectClause getSelectClause() {
    return findNotNullChildByClass(BallerinaSelectClause.class);
  }

}
