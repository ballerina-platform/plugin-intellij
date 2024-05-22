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

public class BallerinaResourceMethodQualsImpl extends ASTWrapperPsiElement implements BallerinaResourceMethodQuals {

  public BallerinaResourceMethodQualsImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitResourceMethodQuals(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaFunctionQuals getFunctionQuals() {
    return findChildByClass(BallerinaFunctionQuals.class);
  }

  @Override
  @Nullable
  public BallerinaIsolatedQual getIsolatedQual() {
    return findChildByClass(BallerinaIsolatedQual.class);
  }

  @Override
  @NotNull
  public BallerinaResourceQual getResourceQual() {
    return findNotNullChildByClass(BallerinaResourceQual.class);
  }

  @Override
  @Nullable
  public BallerinaTransactionalQual getTransactionalQual() {
    return findChildByClass(BallerinaTransactionalQual.class);
  }

}
