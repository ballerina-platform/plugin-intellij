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

public class BallerinaRequiredParamsImpl extends ASTWrapperPsiElement implements BallerinaRequiredParams {

  public BallerinaRequiredParamsImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitRequiredParams(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<BallerinaDefaultableParam> getDefaultableParamList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, BallerinaDefaultableParam.class);
  }

  @Override
  @NotNull
  public List<BallerinaRequiredParam> getRequiredParamList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, BallerinaRequiredParam.class);
  }

  @Override
  @NotNull
  public List<BallerinaRestParam> getRestParamList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, BallerinaRestParam.class);
  }

}
