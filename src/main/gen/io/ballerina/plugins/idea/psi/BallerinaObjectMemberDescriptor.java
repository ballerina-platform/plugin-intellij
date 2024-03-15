// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaObjectMemberDescriptor extends PsiElement {

  @Nullable
  BallerinaMethodDecl getMethodDecl();

  @Nullable
  BallerinaObjectFieldDescriptor getObjectFieldDescriptor();

  @Nullable
  BallerinaObjectTypeInclusion getObjectTypeInclusion();

  @Nullable
  BallerinaRemoteMethodDecl getRemoteMethodDecl();

  @Nullable
  BallerinaResourceMethodDecl getResourceMethodDecl();

}
