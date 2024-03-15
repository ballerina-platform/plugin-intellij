// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.ballerina.plugins.idea.psi.BallerinaTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.ballerina.plugins.idea.psi.*;

public class BallerinaIntermediateClauseImpl extends ASTWrapperPsiElement implements BallerinaIntermediateClause {

  public BallerinaIntermediateClauseImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitIntermediateClause(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaFromClause getFromClause() {
    return findChildByClass(BallerinaFromClause.class);
  }

  @Override
  @Nullable
  public BallerinaGroupByClause getGroupByClause() {
    return findChildByClass(BallerinaGroupByClause.class);
  }

  @Override
  @Nullable
  public BallerinaJoinClause getJoinClause() {
    return findChildByClass(BallerinaJoinClause.class);
  }

  @Override
  @Nullable
  public BallerinaLetClause getLetClause() {
    return findChildByClass(BallerinaLetClause.class);
  }

  @Override
  @Nullable
  public BallerinaLimitClause getLimitClause() {
    return findChildByClass(BallerinaLimitClause.class);
  }

  @Override
  @Nullable
  public BallerinaOrderByClause getOrderByClause() {
    return findChildByClass(BallerinaOrderByClause.class);
  }

  @Override
  @Nullable
  public BallerinaWhereClause getWhereClause() {
    return findChildByClass(BallerinaWhereClause.class);
  }

}
