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
import io.ballerina.plugins.idea.BallerinaConstants;

import java.io.File;
import java.nio.file.Paths;

/**
 * Utility class for ballerina project wise configurations.
 *
 * @since 2.0.0
 */
public class BallerinaProjectUtil {

    public static String findBallerinaPackage(String startingPath) {
        startingPath = Paths.get(startingPath).normalize().toString();
        File current = new File(startingPath);
        while (current != null) {
            File ballerinaFile = new File(current, BallerinaConstants.BALLERINA_TOML_FILE);
            if (ballerinaFile.exists()) {
                return current.getAbsolutePath();
            }
            current = current.getParentFile();
        }

        return "";
    }

    public static String findBallerinaModule(String startingPath) {
        startingPath = Paths.get(startingPath).normalize().toString();
        File current = new File(startingPath);

        while (current != null) {
            if (current.getParentFile() != null
                && current.getParentFile().getName()
                    .equalsIgnoreCase(BallerinaConstants.BALLERINA_MODULE_FOLDER_NAME)) {
                    if (new File(current.getParentFile().getParentFile(), BallerinaConstants.BALLERINA_TOML_FILE)
                            .exists()) {
                        return current.getAbsolutePath();
                    }
            }
            current = current.getParentFile();
        }

        return "";
    }

    public static boolean isPackageTest(PsiElement element) {
        PsiFile containingFile = element.getContainingFile();
        VirtualFile virtualFile = containingFile != null ? containingFile.getVirtualFile() : null;
        if (virtualFile != null) {
            String path = virtualFile.getPath();
            File current = new File(path);
            File parent = current.getParentFile();
            File balToml = new File(parent.getParent(), BallerinaConstants.BALLERINA_TOML_FILE);
            return parent.getName().equals(BallerinaConstants.BALLERINA_TEST_FOLDER_NAME) && balToml.exists();
        }
        return false;
    }

    public static boolean isModuleTest(PsiElement element) {
        PsiFile containingFile = element.getContainingFile();
        VirtualFile virtualFile = containingFile != null ? containingFile.getVirtualFile() : null;
        if (virtualFile != null) {
            String path = virtualFile.getPath();
            File current = new File(path);
            File parent = current.getParentFile();
            File grandParent = parent.getParentFile().getParentFile();
            File balToml = new File(grandParent.getParentFile(), BallerinaConstants.BALLERINA_TOML_FILE);
            return parent.getName().equals(BallerinaConstants.BALLERINA_TEST_FOLDER_NAME)
                    && grandParent.getName().equalsIgnoreCase(BallerinaConstants.BALLERINA_MODULE_FOLDER_NAME) &&
                    balToml.exists();
        }
        return false;
    }

    public static String getModuleName(PsiElement element) {
        PsiFile containingFile = element.getContainingFile();
        VirtualFile virtualFile = containingFile != null ? containingFile.getVirtualFile() : null;
        if (virtualFile != null) {
            String path = virtualFile.getPath();
            File current = new File(path);
            File parent = current.getParentFile();
            return parent.getParentFile().getName();
        }
        return "";
    }
}
