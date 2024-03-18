// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaDocumentationString extends PsiElement {

  @Nullable
  BallerinaDeprecatedAnnotationDocumentationLine getDeprecatedAnnotationDocumentationLine();

  @Nullable
  BallerinaDeprecatedParametersDocumentationLine getDeprecatedParametersDocumentationLine();

  @NotNull
  List<BallerinaDocumentationLine> getDocumentationLineList();

  @NotNull
  List<BallerinaParameterDocumentationLine> getParameterDocumentationLineList();

  @Nullable
  BallerinaReturnParameterDocumentationLine getReturnParameterDocumentationLine();

}
