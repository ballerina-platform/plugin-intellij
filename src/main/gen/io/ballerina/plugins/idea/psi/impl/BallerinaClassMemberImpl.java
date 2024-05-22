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

public class BallerinaClassMemberImpl extends ASTWrapperPsiElement implements BallerinaClassMember {

  public BallerinaClassMemberImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitClassMember(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaMethodDefn getMethodDefn() {
    return findChildByClass(BallerinaMethodDefn.class);
  }

  @Override
  @Nullable
  public BallerinaObjectField getObjectField() {
    return findChildByClass(BallerinaObjectField.class);
  }

  @Override
  @Nullable
  public BallerinaObjectTypeInclusion getObjectTypeInclusion() {
    return findChildByClass(BallerinaObjectTypeInclusion.class);
  }

  @Override
  @Nullable
  public BallerinaRemoteMethodDefn getRemoteMethodDefn() {
    return findChildByClass(BallerinaRemoteMethodDefn.class);
  }

  @Override
  @Nullable
  public BallerinaResourceMethodDefn getResourceMethodDefn() {
    return findChildByClass(BallerinaResourceMethodDefn.class);
  }

}
