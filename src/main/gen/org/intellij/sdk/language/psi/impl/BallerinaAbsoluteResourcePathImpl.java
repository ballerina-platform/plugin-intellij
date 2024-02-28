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

public class BallerinaAbsoluteResourcePathImpl extends ASTWrapperPsiElement implements BallerinaAbsoluteResourcePath {

  public BallerinaAbsoluteResourcePathImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitAbsoluteResourcePath(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<BallerinaResourcePathSegmentName> getResourcePathSegmentNameList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, BallerinaResourcePathSegmentName.class);
  }

  @Override
  @Nullable
  public BallerinaRootResourcePath getRootResourcePath() {
    return findChildByClass(BallerinaRootResourcePath.class);
  }

}
