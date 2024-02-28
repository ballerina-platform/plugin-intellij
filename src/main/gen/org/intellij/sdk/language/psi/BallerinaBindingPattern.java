// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaBindingPattern extends PsiElement {

  @Nullable
  BallerinaCaptureBindingPattern getCaptureBindingPattern();

  @Nullable
  BallerinaErrorBindingPattern getErrorBindingPattern();

  @Nullable
  BallerinaListBindingPattern getListBindingPattern();

  @Nullable
  BallerinaMappingBindingPattern getMappingBindingPattern();

  @Nullable
  BallerinaWildcardBindingPattern getWildcardBindingPattern();

}
