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

package io.ballerina.plugins.idea.project;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import io.ballerina.plugins.idea.sdk.BallerinaSdkUtil;

import java.io.File;
import java.util.Optional;

import static io.ballerina.plugins.idea.BallerinaConstants.BAL_MODULE_DIR_NAME;
import static io.ballerina.plugins.idea.BallerinaConstants.BAL_TEST_DIR_NAME;
import static io.ballerina.plugins.idea.BallerinaConstants.BAL_TOML_FILE;

/**
 * Utility class for ballerina project wise configurations.
 *
 * @since 2.0.0
 */
public class BallerinaProjectUtil {

    public static Optional<String> findBallerinaPackage(String startingPath) {
        try {
            startingPath = BallerinaSdkUtil.getNormalizedPath(startingPath);
            File current = new File(startingPath);
            while (current != null) {
                File ballerinaFile = new File(current, BAL_TOML_FILE);
                if (ballerinaFile.exists()) {
                    return Optional.of(current.getAbsolutePath());
                }
                current = current.getParentFile();
            }

            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<String> findBallerinaModule(String startingPath) {
        try {
            startingPath = BallerinaSdkUtil.getNormalizedPath(startingPath);
            File current = new File(startingPath);

            while (current != null) {
                if (current.getParentFile() != null
                        && current.getParentFile().getName()
                        .equalsIgnoreCase(BAL_MODULE_DIR_NAME)) {
                    if (new File(current.getParentFile().getParentFile(), BAL_TOML_FILE)
                            .exists()) {
                        return Optional.of(current.getAbsolutePath());
                    }
                }
                current = current.getParentFile();
            }

            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static boolean isPackageTest(PsiElement element) {
        try {
            PsiFile containingFile = element.getContainingFile();
            VirtualFile virtualFile = containingFile != null ? containingFile.getVirtualFile() : null;
            if (virtualFile != null) {
                String path = virtualFile.getPath();
                File current = new File(path);
                File parent = current.getParentFile();
                File balToml = new File(parent.getParent(), BAL_TOML_FILE);
                return parent.getName().equals(BAL_TEST_DIR_NAME) && balToml.exists();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isModuleTest(PsiElement element) {
        try {
            PsiFile containingFile = element.getContainingFile();
            VirtualFile virtualFile = containingFile != null ? containingFile.getVirtualFile() : null;
            if (virtualFile != null) {
                String path = virtualFile.getPath();
                File current = new File(path);
                File parent = current.getParentFile();
                File grandParent = parent.getParentFile().getParentFile();
                File balToml = new File(grandParent.getParentFile(), BAL_TOML_FILE);
                return parent.getName().equals(BAL_TEST_DIR_NAME)
                        && grandParent.getName().equalsIgnoreCase(BAL_MODULE_DIR_NAME) &&
                        balToml.exists();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static Optional<String> getModuleName(PsiElement element) {
        try {
            PsiFile containingFile = element.getContainingFile();
            VirtualFile virtualFile = containingFile != null ? containingFile.getVirtualFile() : null;
            if (virtualFile != null) {
                String path = virtualFile.getPath();
                File current = new File(path);
                File parent = current.getParentFile();
                return Optional.of(parent.getParentFile().getName());
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
