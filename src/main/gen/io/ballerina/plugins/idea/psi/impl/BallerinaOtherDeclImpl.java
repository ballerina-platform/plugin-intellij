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

public class BallerinaOtherDeclImpl extends ASTWrapperPsiElement implements BallerinaOtherDecl {

  public BallerinaOtherDeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitOtherDecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaAnnotationDecl getAnnotationDecl() {
    return findChildByClass(BallerinaAnnotationDecl.class);
  }

  @Override
  @Nullable
  public BallerinaFunctionDefn getFunctionDefn() {
    return findChildByClass(BallerinaFunctionDefn.class);
  }

  @Override
  @Nullable
  public BallerinaListenerDecl getListenerDecl() {
    return findChildByClass(BallerinaListenerDecl.class);
  }

  @Override
  @Nullable
  public BallerinaModuleClassDefn getModuleClassDefn() {
    return findChildByClass(BallerinaModuleClassDefn.class);
  }

  @Override
  @Nullable
  public BallerinaModuleConstDecl getModuleConstDecl() {
    return findChildByClass(BallerinaModuleConstDecl.class);
  }

  @Override
  @Nullable
  public BallerinaModuleEnumDecl getModuleEnumDecl() {
    return findChildByClass(BallerinaModuleEnumDecl.class);
  }

  @Override
  @Nullable
  public BallerinaModuleTypeDefn getModuleTypeDefn() {
    return findChildByClass(BallerinaModuleTypeDefn.class);
  }

  @Override
  @Nullable
  public BallerinaModuleVarDecl getModuleVarDecl() {
    return findChildByClass(BallerinaModuleVarDecl.class);
  }

  @Override
  @Nullable
  public BallerinaModuleXmlnsDecl getModuleXmlnsDecl() {
    return findChildByClass(BallerinaModuleXmlnsDecl.class);
  }

  @Override
  @Nullable
  public BallerinaServiceDecl getServiceDecl() {
    return findChildByClass(BallerinaServiceDecl.class);
  }

}
