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

public class BallerinaImportDeclImpl extends ASTWrapperPsiElement implements BallerinaImportDecl {

  public BallerinaImportDeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitImportDecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaImportPrefix getImportPrefix() {
    return findChildByClass(BallerinaImportPrefix.class);
  }

  @Override
  @Nullable
  public BallerinaModuleName getModuleName() {
    return findChildByClass(BallerinaModuleName.class);
  }

  @Override
  @Nullable
  public BallerinaOrgName getOrgName() {
    return findChildByClass(BallerinaOrgName.class);
  }

}
