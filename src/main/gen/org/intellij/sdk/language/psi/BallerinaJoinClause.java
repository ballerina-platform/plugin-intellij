// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaJoinClause extends PsiElement {

  @NotNull
  BallerinaExpression getExpression();

  @NotNull
  BallerinaJoinOnCondition getJoinOnCondition();

  @NotNull
  BallerinaTypedBindingPattern getTypedBindingPattern();

}
