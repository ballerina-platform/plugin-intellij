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

public class BallerinaParamListImpl extends ASTWrapperPsiElement implements BallerinaParamList {

  public BallerinaParamListImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitParamList(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaDefaultableParams getDefaultableParams() {
    return findChildByClass(BallerinaDefaultableParams.class);
  }

  @Override
  @Nullable
  public BallerinaIncludedRecordParams getIncludedRecordParams() {
    return findChildByClass(BallerinaIncludedRecordParams.class);
  }

  @Override
  @Nullable
  public BallerinaRequiredParams getRequiredParams() {
    return findChildByClass(BallerinaRequiredParams.class);
  }

  @Override
  @Nullable
  public BallerinaRestParam getRestParam() {
    return findChildByClass(BallerinaRestParam.class);
  }

}
