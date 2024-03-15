// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaIntermediateClause extends PsiElement {

  @Nullable
  BallerinaFromClause getFromClause();

  @Nullable
  BallerinaGroupByClause getGroupByClause();

  @Nullable
  BallerinaJoinClause getJoinClause();

  @Nullable
  BallerinaLetClause getLetClause();

  @Nullable
  BallerinaLimitClause getLimitClause();

  @Nullable
  BallerinaOrderByClause getOrderByClause();

  @Nullable
  BallerinaWhereClause getWhereClause();

}
