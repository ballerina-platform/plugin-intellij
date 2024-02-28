// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaErrorFieldBindingPatterns extends PsiElement {

  @NotNull
  List<BallerinaNamedArgBindingPattern> getNamedArgBindingPatternList();

  @Nullable
  BallerinaRestBindingPattern getRestBindingPattern();

}
