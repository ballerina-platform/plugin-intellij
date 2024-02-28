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

public class BallerinaBehavioralTypeDescriptorImpl extends BallerinaTypeDescriptorImpl implements BallerinaBehavioralTypeDescriptor {

  public BallerinaBehavioralTypeDescriptorImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitBehavioralTypeDescriptor(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaErrorTypeDescriptor getErrorTypeDescriptor() {
    return findChildByClass(BallerinaErrorTypeDescriptor.class);
  }

  @Override
  @Nullable
  public BallerinaFunctionTypeDescriptor getFunctionTypeDescriptor() {
    return findChildByClass(BallerinaFunctionTypeDescriptor.class);
  }

  @Override
  @Nullable
  public BallerinaFutureTypeDescriptor getFutureTypeDescriptor() {
    return findChildByClass(BallerinaFutureTypeDescriptor.class);
  }

  @Override
  @Nullable
  public BallerinaHandleTypeDescriptor getHandleTypeDescriptor() {
    return findChildByClass(BallerinaHandleTypeDescriptor.class);
  }

  @Override
  @Nullable
  public BallerinaObjectTypeDescriptor getObjectTypeDescriptor() {
    return findChildByClass(BallerinaObjectTypeDescriptor.class);
  }

  @Override
  @Nullable
  public BallerinaStreamTypeDescriptor getStreamTypeDescriptor() {
    return findChildByClass(BallerinaStreamTypeDescriptor.class);
  }

  @Override
  @Nullable
  public BallerinaTypedescTypeDescriptor getTypedescTypeDescriptor() {
    return findChildByClass(BallerinaTypedescTypeDescriptor.class);
  }

}
