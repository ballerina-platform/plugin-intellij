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

public class BallerinaObjectMemberDescriptorImpl extends ASTWrapperPsiElement implements BallerinaObjectMemberDescriptor {

  public BallerinaObjectMemberDescriptorImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitObjectMemberDescriptor(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaMethodDecl getMethodDecl() {
    return findChildByClass(BallerinaMethodDecl.class);
  }

  @Override
  @Nullable
  public BallerinaObjectFieldDescriptor getObjectFieldDescriptor() {
    return findChildByClass(BallerinaObjectFieldDescriptor.class);
  }

  @Override
  @Nullable
  public BallerinaObjectTypeInclusion getObjectTypeInclusion() {
    return findChildByClass(BallerinaObjectTypeInclusion.class);
  }

  @Override
  @Nullable
  public BallerinaRemoteMethodDecl getRemoteMethodDecl() {
    return findChildByClass(BallerinaRemoteMethodDecl.class);
  }

  @Override
  @Nullable
  public BallerinaResourceMethodDecl getResourceMethodDecl() {
    return findChildByClass(BallerinaResourceMethodDecl.class);
  }

}
