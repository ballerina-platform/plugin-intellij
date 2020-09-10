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

package icons;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.LayeredIcon;
import com.intellij.ui.RowIcon;
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;

/**
 * Contains icons used for completion, etc.
 */
public class BallerinaIcons {

    public static final Icon FILE = IconLoader.getIcon("/icons/ballerina.png", BallerinaIcons.class);
    public static final Icon ICON = FILE;
    public static final Icon APPLICATION_RUN = createIconWithShift(ICON, AllIcons.Nodes.RunnableMark);
    public static final Icon RUN = AllIcons.RunConfigurations.TestState.Run;
    public static final Icon PACKAGE = PlatformIcons.FOLDER_ICON;

    public static final Icon TEST = AllIcons.RunConfigurations.TestState.Green2;
    public static final Icon RECURSIVE = AllIcons.Gutter.RecursiveMethod;
    public static final Icon FUNCTION = AllIcons.Nodes.Field;
    public static final Icon VARIABLE = AllIcons.Nodes.Variable;
    public static final Icon GLOBAL_VARIABLE = new LayeredIcon(VARIABLE, AllIcons.Nodes.StaticMark);
    public static final Icon PARAMETER = AllIcons.Nodes.Parameter;
    public static final Icon NAMESPACE = AllIcons.Json.Array;
    public static final Icon SERVICE = AllIcons.Nodes.Static;
    public static final Icon ANNOTATION = AllIcons.Nodes.Annotationtype;
    public static final Icon WORKER = AllIcons.Nodes.Rw_access;
    public static final Icon ENDPOINT = AllIcons.Ide.UpDown;
    public static final Icon GLOBAL_ENDPOINT = new LayeredIcon(ENDPOINT, AllIcons.Nodes.StaticMark);
    public static final Icon TYPE = AllIcons.Nodes.Artifact;

    /**
     * Contains icons used for the ballerina diagram editor(referred by plugin.xml).
     */
    public static class Layout {
        public static final Icon EDITOR_ONLY =
                IconLoader.getIcon("/icons/layout/code.svg", BallerinaIcons.class);
        public static final Icon EDITOR_PREVIEW =
                IconLoader.getIcon("/icons/layout/split-view.svg", BallerinaIcons.class);
        public static final Icon PREVIEW_ONLY =
                IconLoader.getIcon("/icons/layout/design-view.svg", BallerinaIcons.class);
    }

    private BallerinaIcons() {

    }

    @NotNull
    private static LayeredIcon createIconWithShift(@NotNull Icon base, Icon mark) {
        LayeredIcon icon = new LayeredIcon(2) {
            @Override
            public int getIconHeight() {
                return base.getIconHeight();
            }
        };
        icon.setIcon(base, 0);
        icon.setIcon(mark, 1, 0, base.getIconWidth() / 2);
        return icon;
    }

    private static RowIcon createPublicFieldIcon(@NotNull Icon base) {
        RowIcon icon = new RowIcon(2);
        icon.setIcon(base, 0);
        icon.setIcon(PlatformIcons.PUBLIC_ICON, 1);
        return icon;
    }

    private static RowIcon createPrivateFieldIcon(@NotNull Icon base) {
        RowIcon icon = new RowIcon(2);
        icon.setIcon(base, 0);
        icon.setIcon(PlatformIcons.PRIVATE_ICON, 1);
        return icon;
    }
}
