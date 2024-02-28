// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaQuerySelectExpr extends PsiElement {

  @Nullable
  BallerinaOnConflictClause getOnConflictClause();

  @Nullable
  BallerinaQueryConstructType getQueryConstructType();

  @NotNull
  BallerinaQueryPipeline getQueryPipeline();

  @NotNull
  BallerinaSelectClause getSelectClause();

}
