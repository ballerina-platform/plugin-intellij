// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaErrorArgList extends PsiElement {

  @NotNull
  List<BallerinaNamedArg> getNamedArgList();

  @NotNull
  List<BallerinaPositionalArg> getPositionalArgList();

}
