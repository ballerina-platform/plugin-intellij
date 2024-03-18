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

public class BallerinaFunctionDefnImpl extends ASTWrapperPsiElement implements BallerinaFunctionDefn {

  public BallerinaFunctionDefnImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BallerinaVisitor visitor) {
    visitor.visitFunctionDefn(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BallerinaVisitor) accept((BallerinaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BallerinaFunctionDefnBody getFunctionDefnBody() {
    return findChildByClass(BallerinaFunctionDefnBody.class);
  }

  @Override
  @Nullable
  public BallerinaFunctionQuals getFunctionQuals() {
    return findChildByClass(BallerinaFunctionQuals.class);
  }

  @Override
  @Nullable
  public BallerinaFunctionSignature getFunctionSignature() {
    return findChildByClass(BallerinaFunctionSignature.class);
  }

  @Override
  @Nullable
  public BallerinaFunctionTypeDescriptor getFunctionTypeDescriptor() {
    return findChildByClass(BallerinaFunctionTypeDescriptor.class);
  }

  @Override
  @NotNull
  public BallerinaMetadata getMetadata() {
    return findNotNullChildByClass(BallerinaMetadata.class);
  }

}
