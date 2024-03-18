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

public class BallerinaServiceDeclImpl extends ASTWrapperPsiElement implements BallerinaServiceDecl {

  public BallerinaServiceDeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitServiceDecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaAttachPoint getAttachPoint() {
    return findChildByClass(BallerinaAttachPoint.class);
  }

  @Override
  @Nullable
  public BallerinaExpressionList getExpressionList() {
    return findChildByClass(BallerinaExpressionList.class);
  }

  @Override
  @Nullable
  public BallerinaIsolatedQual getIsolatedQual() {
    return findChildByClass(BallerinaIsolatedQual.class);
  }

  @Override
  @NotNull
  public BallerinaMetadata getMetadata() {
    return findNotNullChildByClass(BallerinaMetadata.class);
  }

  @Override
  @Nullable
  public BallerinaServiceMembers getServiceMembers() {
    return findChildByClass(BallerinaServiceMembers.class);
  }

  @Override
  @Nullable
  public BallerinaTypeDescriptor getTypeDescriptor() {
    return findChildByClass(BallerinaTypeDescriptor.class);
  }

}
