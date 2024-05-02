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

package io.ballerina.plugins.idea.widget;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.MouseEvent;

import javax.swing.Icon;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Display ballerina icon in the status bar after detecting bal version.
 * This displays bal version when hovering over it.
 *
 * @since 2.0.0
 */
public class BallerinaIconWidget implements StatusBarWidget, StatusBarWidget.IconPresentation {

    private static final String ID = "BallerinaIconWidget";
    private final Project project;
    private Icon icon = null;
    private String tooltipText = EMPTY;

    public BallerinaIconWidget(Project project) {
        this.project = project;
    }

    @NotNull
    @Override
    public String ID() {
        return ID;
    }

    @Nullable
    @Override
    public WidgetPresentation getPresentation() {
        return this;
    }

    @Override
    public void install(@NotNull StatusBar statusBar) {

    }

    @Override
    public void dispose() {
        setIcon(null);
        setTooltipText(EMPTY);
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
        if (statusBar != null) {
            statusBar.updateWidget(ID());
        }
    }

    @Nullable
    @Override
    public String getTooltipText() {
        return tooltipText;
    }

    public void setTooltipText(String tooltipText) {
        this.tooltipText = tooltipText;
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
        if (statusBar != null) {
            statusBar.updateWidget(ID());
        }
    }

    @Override
    public @Nullable Consumer<MouseEvent> getClickConsumer() {
        return null;
    }

    public Project getProject() {
        return project;
    }
}
