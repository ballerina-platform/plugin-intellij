// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaResourcePath extends PsiElement {

  @Nullable
  BallerinaDotResourcePath getDotResourcePath();

  @Nullable
  BallerinaResourcePathRestParam getResourcePathRestParam();

  @NotNull
  List<BallerinaResourcePathSegment> getResourcePathSegmentList();

}
