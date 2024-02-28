// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaDocumentationText extends PsiElement {

  @NotNull
  List<BallerinaBacktickedBlock> getBacktickedBlockList();

  @NotNull
  List<BallerinaDocumentationReference> getDocumentationReferenceList();

  @NotNull
  List<BallerinaDocumentationTextContent> getDocumentationTextContentList();

  @NotNull
  List<BallerinaReferenceType> getReferenceTypeList();

}
