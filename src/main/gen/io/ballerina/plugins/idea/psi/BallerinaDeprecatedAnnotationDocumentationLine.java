// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaDeprecatedAnnotationDocumentationLine extends PsiElement {

  @NotNull
  List<BallerinaDeprecateAnnotationDescriptionLine> getDeprecateAnnotationDescriptionLineList();

  @NotNull
  BallerinaDeprecatedAnnotationDocumentation getDeprecatedAnnotationDocumentation();

}
