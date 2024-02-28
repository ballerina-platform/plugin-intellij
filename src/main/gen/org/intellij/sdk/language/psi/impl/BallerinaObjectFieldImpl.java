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

public class BallerinaObjectFieldImpl extends ASTWrapperPsiElement implements BallerinaObjectField {

  public BallerinaObjectFieldImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitObjectField(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaFieldInitializer getFieldInitializer() {
    return findChildByClass(BallerinaFieldInitializer.class);
  }

  @Override
  @NotNull
  public BallerinaFieldName getFieldName() {
    return findNotNullChildByClass(BallerinaFieldName.class);
  }

  @Override
  @NotNull
  public BallerinaMetadata getMetadata() {
    return findNotNullChildByClass(BallerinaMetadata.class);
  }

  @Override
  @Nullable
  public BallerinaObjectVisibilityQual getObjectVisibilityQual() {
    return findChildByClass(BallerinaObjectVisibilityQual.class);
  }

  @Override
  @NotNull
  public BallerinaTypeDescriptor getTypeDescriptor() {
    return findNotNullChildByClass(BallerinaTypeDescriptor.class);
  }

}
