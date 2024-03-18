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

public class BallerinaQueryPipelineImpl extends ASTWrapperPsiElement implements BallerinaQueryPipeline {

  public BallerinaQueryPipelineImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitQueryPipeline(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public BallerinaFromClause getFromClause() {
    return findNotNullChildByClass(BallerinaFromClause.class);
  }

  @Override
  @NotNull
  public List<BallerinaIntermediateClause> getIntermediateClauseList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, BallerinaIntermediateClause.class);
  }

}
