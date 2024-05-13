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

package io.ballerina.plugins.idea.preloading;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.LightVirtualFileBase;
import io.ballerina.plugins.idea.BallerinaConstants;
import io.ballerina.plugins.idea.BallerinaIcons;
import io.ballerina.plugins.idea.highlighting.BallerinaIdentifierHighlighter;
import io.ballerina.plugins.idea.notification.BallerinaPluginNotifier;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import io.ballerina.plugins.idea.sdk.BallerinaSdkUtils;
import io.ballerina.plugins.idea.widget.BallerinaDetectionWidget;
import io.ballerina.plugins.idea.widget.BallerinaDetectionWidgetFactory;
import io.ballerina.plugins.idea.widget.BallerinaIconWidget;
import io.ballerina.plugins.idea.widget.BallerinaIconWidgetFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static io.ballerina.plugins.idea.BallerinaConstants.EMPTY_STRING;
import static io.ballerina.plugins.idea.preloading.BallerinaLSPUtils.registerProject;

/**
 * Editor listener implementation which is used to handle ballerina source files opening.
 *
 * @since 2.0.0
 */
public class BallerinaEditorFactoryListener implements EditorFactoryListener {

    private boolean balSourcesFound = false;
    private boolean balSdkFound = false;
    private final Map<Editor, CaretListener> editorCaretListenerMap = new HashMap<>();


    @Override
    public void editorCreated(@NotNull EditorFactoryEvent event) {
        Project project = event.getEditor().getProject();
        if (project == null) {
            return;
        }
        VirtualFile file = FileDocumentManager.getInstance().getFile(event.getEditor().getDocument());
        if (balSdkFound) {
            registerIconWidget(project);
            registerLanguageServer(project);
        }
        boolean isBallerinaFile = isBalFile(file);
        if (isBallerinaFile) {
            CaretListener caretListener = new BallerinaIdentifierHighlighter(project);
            event.getEditor().getCaretModel().addCaretListener(caretListener);
            editorCaretListenerMap.put(event.getEditor(), caretListener);
        }
        if (balSourcesFound || !isBallerinaFile) {
            return;
        }
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            BallerinaDetectionWidget widget = BallerinaDetectionWidgetFactory.getWidget(project);
            if (widget != null) {
                ApplicationManager.getApplication().invokeLater(() -> widget.setMessage("Detecting Ballerina.."));
            }
            String balVersion = BallerinaSdkService.getInstance().getBallerinaVersion(project);
            if (widget != null) {
                ApplicationManager.getApplication().invokeLater(() -> {
                    widget.setMessage(EMPTY_STRING);
                    if (!BallerinaSdkUtils.isValidVersion(balVersion)) {
                        BallerinaPluginNotifier.notifyBallerinaNotDetected(project);
                    } else {
                        balSdkFound = true;
                        registerIconWidget(project);
                        registerLanguageServer(project);
                    }
                });
            }
            balSourcesFound = true;
        });
        balSourcesFound = true;
    }

    @Override
    public void editorReleased(@NotNull EditorFactoryEvent event) {
        CaretListener caretListener = editorCaretListenerMap.get(event.getEditor());
        if (caretListener != null) {
            event.getEditor().getCaretModel().removeCaretListener(caretListener);
        }
    }

    private static boolean isBalFile(@Nullable VirtualFile file) {
        if (file == null || file.getExtension() == null || file instanceof LightVirtualFileBase) {
            return false;
        }
        String fileUrl = file.getUrl();
        if (fileUrl.isEmpty() || fileUrl.startsWith("jar:")) {
            return false;
        }

        return file.getExtension().equals(BallerinaConstants.BAL_EXTENSION.substring(1));
    }

    private void registerIconWidget(Project project) {
        BallerinaIconWidget iconWidget = BallerinaIconWidgetFactory.getWidget(project);
        if (iconWidget != null) {
            iconWidget.setIcon(BallerinaIcons.FILE);
            iconWidget.setTooltipText(BallerinaSdkService.getInstance().getBallerinaVersion(project));
        }
    }

    private void registerLanguageServer(Project project) {
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            registerProject(project);
        });
    }
}
