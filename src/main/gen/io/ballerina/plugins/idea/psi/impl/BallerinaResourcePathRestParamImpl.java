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

public class BallerinaResourcePathRestParamImpl extends ASTWrapperPsiElement implements BallerinaResourcePathRestParam {

  public BallerinaResourcePathRestParamImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitResourcePathRestParam(this);
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
  @Nullable
  public BallerinaParamName getParamName() {
    return findChildByClass(BallerinaParamName.class);
  }

  @Override
  @NotNull
  public BallerinaTypeDescriptor getTypeDescriptor() {
    return findNotNullChildByClass(BallerinaTypeDescriptor.class);
  }

}
