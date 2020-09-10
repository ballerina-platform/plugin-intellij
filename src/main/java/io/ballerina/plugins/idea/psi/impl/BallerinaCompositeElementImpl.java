/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.ballerina.plugins.idea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import io.ballerina.plugins.idea.psi.BallerinaCompositeElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Implementation of composite element class.
 */
public class BallerinaCompositeElementImpl extends ASTWrapperPsiElement implements BallerinaCompositeElement {

    public BallerinaCompositeElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String toString() {
        return getNode().getElementType().toString();
    }

    @Override
    public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state,
                                       @Nullable PsiElement lastParent, @NotNull PsiElement place) {
        return processDeclarationsDefault(this, processor, state, lastParent, place);
    }

    @Contract(pure = true)
    public static boolean processDeclarationsDefault(@NotNull BallerinaCompositeElement o,
                                                     @NotNull PsiScopeProcessor processor,
                                                     @NotNull ResolveState state,
                                                     @Nullable PsiElement lastParent,
                                                     @NotNull PsiElement place) {
        return false;
    }

    @Override
    public boolean shouldGoDeeper() {
        return true;
    }
}
