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

package org.intellij.sdk.language;

import com.intellij.openapi.util.IconLoader;

import javax.swing.Icon;

/**
 * Holds icons used in the Ballerina IntelliJ plugin. This class primarily
 * provides an easy access point for icons related to Ballerina files and
 * other UI components within the IDE.
 *
 * @since 2.0.0
 */
public class BallerinaIcons {

    public static final Icon FILE = IconLoader.getIcon("/icons/BallerinaIcon.png", BallerinaIcons.class);
}
