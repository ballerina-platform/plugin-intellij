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

public class BallerinaBindingPatternImpl extends ASTWrapperPsiElement implements BallerinaBindingPattern {

  public BallerinaBindingPatternImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitBindingPattern(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaCaptureBindingPattern getCaptureBindingPattern() {
    return findChildByClass(BallerinaCaptureBindingPattern.class);
  }

  @Override
  @Nullable
  public BallerinaErrorBindingPattern getErrorBindingPattern() {
    return findChildByClass(BallerinaErrorBindingPattern.class);
  }

  @Override
  @Nullable
  public BallerinaListBindingPattern getListBindingPattern() {
    return findChildByClass(BallerinaListBindingPattern.class);
  }

  @Override
  @Nullable
  public BallerinaMappingBindingPattern getMappingBindingPattern() {
    return findChildByClass(BallerinaMappingBindingPattern.class);
  }

  @Override
  @Nullable
  public BallerinaWildcardBindingPattern getWildcardBindingPattern() {
    return findChildByClass(BallerinaWildcardBindingPattern.class);
  }

}
