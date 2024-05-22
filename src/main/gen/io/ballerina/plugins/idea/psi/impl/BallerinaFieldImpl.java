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

public class BallerinaFieldImpl extends ASTWrapperPsiElement implements BallerinaField {

  public BallerinaFieldImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitField(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaComputedNameField getComputedNameField() {
    return findChildByClass(BallerinaComputedNameField.class);
  }

  @Override
  @Nullable
  public BallerinaSpecificField getSpecificField() {
    return findChildByClass(BallerinaSpecificField.class);
  }

  @Override
  @Nullable
  public BallerinaSpreadField getSpreadField() {
    return findChildByClass(BallerinaSpreadField.class);
  }

}
