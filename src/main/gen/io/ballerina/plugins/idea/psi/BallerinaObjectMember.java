// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BallerinaObjectMember extends PsiElement {

  @Nullable
  BallerinaMethodDefn getMethodDefn();

  @Nullable
  BallerinaObjectField getObjectField();

  @Nullable
  BallerinaRemoteMethodDefn getRemoteMethodDefn();

  @Nullable
  BallerinaResourceMethodDefn getResourceMethodDefn();

}
