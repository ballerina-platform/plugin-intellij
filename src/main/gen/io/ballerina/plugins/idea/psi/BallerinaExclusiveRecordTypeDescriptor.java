// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaExclusiveRecordTypeDescriptor extends PsiElement {

  @Nullable
  BallerinaTokensPipe getTokensPipe();

  @NotNull
  List<BallerinaFieldDescriptor> getFieldDescriptorList();

  @Nullable
  BallerinaRecordRestDescriptor getRecordRestDescriptor();

}
