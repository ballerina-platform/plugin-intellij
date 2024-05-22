// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaAbsoluteResourcePath extends PsiElement {

  @NotNull
  List<BallerinaResourcePathSegmentName> getResourcePathSegmentNameList();

  @Nullable
  BallerinaRootResourcePath getRootResourcePath();

}
