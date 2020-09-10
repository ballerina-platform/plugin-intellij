/*
 *  Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.ballerina.plugins.idea.webview.diagram.preview.javafx;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import io.ballerina.plugins.idea.webview.diagram.preview.DiagramHtmlPanel;
import io.ballerina.plugins.idea.webview.diagram.preview.HtmlPanelProvider;
import org.jetbrains.annotations.NotNull;

/**
 * JavaFx based diagram panel provider implementation.
 */
public class JavaFxHtmlPanelProvider extends HtmlPanelProvider {

    @NotNull
    @Override
    public DiagramHtmlPanel createHtmlPanel(Project project, VirtualFile file) {
        return new DiagramJavaFxHtmlPanel(project, file);
    }

    @NotNull
    @Override
    public HtmlPanelProvider.AvailabilityInfo isAvailable() {
        try {
            if (Class.forName("javafx.scene.web.WebView", false, getClass().getClassLoader()) != null) {
                return HtmlPanelProvider.AvailabilityInfo.AVAILABLE;
            }
        } catch (ClassNotFoundException ignored) {
        }

        return AvailabilityInfo.UNAVAILABLE;
    }

    @NotNull
    @Override
    public ProviderInfo getProviderInfo() {
        return new ProviderInfo("JavaFX WebView", JavaFxHtmlPanelProvider.class.getName());
    }

}
