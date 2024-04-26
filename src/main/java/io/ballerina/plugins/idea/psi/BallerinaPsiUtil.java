/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
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
 *
 */

package io.ballerina.plugins.idea.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import io.ballerina.plugins.idea.psi.impl.BallerinaAnnotationImpl;
import io.ballerina.plugins.idea.psi.impl.BallerinaAnnotsImpl;
import io.ballerina.plugins.idea.psi.impl.BallerinaFunctionDefnImpl;
import io.ballerina.plugins.idea.psi.impl.BallerinaMetadataImpl;
import io.ballerina.plugins.idea.psi.impl.BallerinaOtherDeclImpl;
import io.ballerina.plugins.idea.psi.impl.BallerinaServiceDeclImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Provides utility functions related to PSI elements.
 *
 * @since 2.0.0
 */
public class BallerinaPsiUtil {

    public static PsiElement getPreviousSibling(PsiElement element) {
        if (element == null) {
            return null;
        }
        PsiElement prevSibling = element.getPrevSibling();
        while (prevSibling != null && isWhitespace(prevSibling)) {
            prevSibling = prevSibling.getPrevSibling();
        }
        return prevSibling;
    }

    public static PsiElement getNextSibling(PsiElement element) {
        if (element == null) {
            return null;
        }
        PsiElement nextSibling = element.getNextSibling();
        while (nextSibling != null && isWhitespace(nextSibling)) {
            nextSibling = nextSibling.getNextSibling();
        }
        return nextSibling;
    }

    public static boolean isPublicFunction(@NotNull PsiElement element) {
        PsiElement parent = element.getParent();
        return parent.getFirstChild().getNextSibling().getNode().getElementType() == BallerinaTypes.PUBLIC_KEYWORD;
    }

    public static boolean isMainFunction(@NotNull PsiElement element) {
        PsiElement prevSibling = element.getPrevSibling();
        while (prevSibling != null && isWhitespace(prevSibling)) {
            prevSibling = prevSibling.getPrevSibling();
        }
        return element.getText().equals("main") && prevSibling != null
                && Objects.requireNonNull(prevSibling).getNode().getElementType() == BallerinaTypes.FUNCTION_KEYWORD
                && element.getParent() != null
                && Objects.requireNonNull(element.getParent()) instanceof BallerinaFunctionDefnImpl
                && isPublicFunction(element)
                && element.getParent().getParent() != null
                && Objects.requireNonNull(element.getParent().getParent()) instanceof BallerinaOtherDeclImpl;
    }

    public static boolean isService(@NotNull PsiElement element) {
        return Objects.requireNonNull(element).getNode().getElementType() == BallerinaTypes.SERVICE_KEYWORD
                && element.getParent() != null
                && Objects.requireNonNull(element.getParent()) instanceof BallerinaServiceDeclImpl
                && element.getParent().getParent() != null
                && Objects.requireNonNull(element.getParent().getParent()) instanceof BallerinaOtherDeclImpl;
    }

    public static String getFunctionName(PsiElement element) {
        PsiElement root = element.getParent().getParent().getParent();
        PsiElement temp = BallerinaPsiUtil.getNextSibling(root);
        while (temp != null && temp.getNode().getElementType() != BallerinaTypes.FUNCTION_KEYWORD) {
            temp = BallerinaPsiUtil.getNextSibling(temp);
        }
        return temp != null ? BallerinaPsiUtil.getNextSibling(temp).getText() : "";
    }

    public static boolean isTestFunction(@NotNull PsiElement element) {
        if (Objects.equals(element.getText(), "Config")
            && element.getParent() instanceof BallerinaAnnotationImpl
            && element.getParent().getParent() instanceof BallerinaAnnotsImpl
            && element.getParent().getParent().getParent() instanceof BallerinaMetadataImpl
            && element.getParent().getParent().getParent().getParent() instanceof BallerinaFunctionDefnImpl
            && element.getParent().getParent().getParent().getParent().getParent() instanceof BallerinaOtherDeclImpl) {
                PsiElement prev1 = BallerinaPsiUtil.getPreviousSibling(element);
                PsiElement prev2 = BallerinaPsiUtil.getPreviousSibling(prev1);
                PsiElement prev3 = BallerinaPsiUtil.getPreviousSibling(prev2);
                if (prev1 != null && prev2 != null && prev3 != null) {
                    return prev1.getNode().getElementType() == BallerinaTypes.COLON_TOKEN &&
                            prev2.getText().equals("test") &&
                            prev3.getNode().getElementType() == BallerinaTypes.AT_TOKEN;
                }
        }
        return false;
    }

    private static boolean isWhitespace(PsiElement element) {
        return element instanceof PsiWhiteSpace;
    }
}
