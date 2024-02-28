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

public class BallerinaErrorArgListBindingPatternImpl extends ASTWrapperPsiElement implements BallerinaErrorArgListBindingPattern {

  public BallerinaErrorArgListBindingPatternImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitErrorArgListBindingPattern(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaErrorCauseBindingPattern getErrorCauseBindingPattern() {
    return findChildByClass(BallerinaErrorCauseBindingPattern.class);
  }

  @Override
  @Nullable
  public BallerinaErrorFieldBindingPatterns getErrorFieldBindingPatterns() {
    return findChildByClass(BallerinaErrorFieldBindingPatterns.class);
  }

  @Override
  @Nullable
  public BallerinaErrorMessageBindingPattern getErrorMessageBindingPattern() {
    return findChildByClass(BallerinaErrorMessageBindingPattern.class);
  }

}
