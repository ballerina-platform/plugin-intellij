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

package io.ballerina.plugins.idea;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

/**
 * Defines the Ballerina file type within IntelliJ-based IDEs. This class associates
 * Ballerina files with the '.bal' extension to the Ballerina language support.
 *
 * @since 2.0.0
 */

public final class BallerinaFileType extends LanguageFileType {

    public static final BallerinaFileType INSTANCE = new BallerinaFileType();

    private BallerinaFileType() {
        super(BallerinaLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Ballerina File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Ballerina language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "bal";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return BallerinaIcons.BAL_ICON;
    }
}
