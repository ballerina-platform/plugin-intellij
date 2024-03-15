// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaOtherDecl extends PsiElement {

  @Nullable
  BallerinaAnnotationDecl getAnnotationDecl();

  @Nullable
  BallerinaFunctionDefn getFunctionDefn();

  @Nullable
  BallerinaListenerDecl getListenerDecl();

  @Nullable
  BallerinaModuleClassDefn getModuleClassDefn();

  @Nullable
  BallerinaModuleConstDecl getModuleConstDecl();

  @Nullable
  BallerinaModuleEnumDecl getModuleEnumDecl();

  @Nullable
  BallerinaModuleTypeDefn getModuleTypeDefn();

  @Nullable
  BallerinaModuleVarDecl getModuleVarDecl();

  @Nullable
  BallerinaModuleXmlnsDecl getModuleXmlnsDecl();

  @Nullable
  BallerinaServiceDecl getServiceDecl();

}
