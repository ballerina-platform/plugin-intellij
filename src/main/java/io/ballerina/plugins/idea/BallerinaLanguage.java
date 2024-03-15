/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com)
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

import com.intellij.lang.Language;

/**
 * Provides Ballerina language support in IntelliJ-based IDEs.
 * This singleton class integrates Ballerina into the IntelliJ Platform, enabling language-specific features like
 * syntax highlighting and code completion.
 *
 * @since 2.0.0
 */
public class BallerinaLanguage extends Language {

    public static final BallerinaLanguage INSTANCE = new BallerinaLanguage();

    private BallerinaLanguage() {

        super("Ballerina");
    }

}
