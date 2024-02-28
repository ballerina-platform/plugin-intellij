// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.sdk.language.psi.BallerinaTypes.*;
import org.intellij.sdk.language.psi.*;

public class BallerinaSimpleTypeDescriptorImpl extends BallerinaTypeDescriptorImpl implements BallerinaSimpleTypeDescriptor {

  public BallerinaSimpleTypeDescriptorImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitSimpleTypeDescriptor(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaBooleanTypeDescriptor getBooleanTypeDescriptor() {
    return findChildByClass(BallerinaBooleanTypeDescriptor.class);
  }

  @Override
  @Nullable
  public BallerinaFloatingPointTypeDescriptor getFloatingPointTypeDescriptor() {
    return findChildByClass(BallerinaFloatingPointTypeDescriptor.class);
  }

  @Override
  @Nullable
  public BallerinaIntTypeDescriptor getIntTypeDescriptor() {
    return findChildByClass(BallerinaIntTypeDescriptor.class);
  }

  @Override
  @Nullable
  public BallerinaNilTypeDescriptor getNilTypeDescriptor() {
    return findChildByClass(BallerinaNilTypeDescriptor.class);
  }

  @Override
  @Nullable
  public BallerinaTaggedDataTypeDescriptor getTaggedDataTypeDescriptor() {
    return findChildByClass(BallerinaTaggedDataTypeDescriptor.class);
  }

}
