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

package io.ballerina.plugins.idea.debugger;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.util.ThreeState;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XDebuggerUtil;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.frame.XCompositeNode;
import com.intellij.xdebugger.frame.XInlineDebuggerDataCallback;
import com.intellij.xdebugger.frame.XNamedValue;
import com.intellij.xdebugger.frame.XNavigatable;
import com.intellij.xdebugger.frame.XStackFrame;
import com.intellij.xdebugger.frame.XValueChildrenList;
import com.intellij.xdebugger.frame.XValueModifier;
import com.intellij.xdebugger.frame.XValueNode;
import com.intellij.xdebugger.frame.XValuePlace;
import com.intellij.xdebugger.frame.presentation.XRegularValuePresentation;
import com.intellij.xdebugger.frame.presentation.XValuePresentation;
import io.ballerina.plugins.idea.highlighting.syntax.BallerinaSyntaxHighlightingColors;
import org.eclipse.lsp4j.debug.Variable;
import org.eclipse.lsp4j.debug.VariablesArguments;
import org.eclipse.lsp4j.debug.VariablesResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

/**
 * Represents a value in the debug window.
 */
public class BallerinaXValue extends XNamedValue {

    private static final Logger LOG = Logger.getInstance(BallerinaXValue.class);
    @NotNull
    private final BallerinaDebugProcess process;
    @NotNull
    private final Variable variable;
    @Nullable
    private final Icon icon;

    BallerinaXValue(@NotNull BallerinaDebugProcess process, @NotNull Variable variable, @Nullable Icon icon) {
        super(variable.getName());
        this.process = process;
        this.variable = variable;
        this.icon = icon;
    }

    @Override
    public void computePresentation(@NotNull XValueNode node, @NotNull XValuePlace place) {
        XValuePresentation presentation = getPresentation();
        boolean hasChildren = false;
        if (variable.getVariablesReference() != null && variable.getVariablesReference() > 0) {
            hasChildren = true;
        }
        node.setPresentation(icon, presentation, hasChildren);
    }

    @Override
    public void computeChildren(@NotNull XCompositeNode node) {
        try {
            XValueChildrenList list = new XValueChildrenList();
            // Sends a variable request to get the child variables.
            VariablesArguments variablesArgs = new VariablesArguments();
            variablesArgs.setVariablesReference(variable.getVariablesReference());

            VariablesResponse variableResp = process.getDapClientConnector().getRequestManager()
                    .variables(variablesArgs);
            for (Variable variable : variableResp.getVariables()) {
                list.add(variable.getName(), new BallerinaXValue(process, variable, AllIcons.Nodes.Field));
            }
            node.addChildren(list, true);
        } catch (Exception e) {
            LOG.warn("Fetching DAP variable child values failed due to ", e);
        }
    }

    @Nullable
    @Override
    public XValueModifier getModifier() {
        return null;
    }

    @NotNull
    private XValuePresentation getPresentation() {
        String value = variable.getValue() != null ? variable.getValue() : "";
        String type = variable.getType() != null ? variable.getType() : "";

        if (type.equalsIgnoreCase(BallerinaXValueType.STRING.getValue())) {
            return new XValuePresentation() {
                @Override
                public void renderValue(@NotNull XValueTextRenderer renderer) {
                    renderer.renderValue(value, BallerinaSyntaxHighlightingColors.STRING);
                }
            };
        }

        if (type.equalsIgnoreCase(BallerinaXValueType.INT.getValue())
                || type.equalsIgnoreCase(BallerinaXValueType.FLOAT.getValue())
                || type.equalsIgnoreCase(BallerinaXValueType.DECIMAL.getValue())
                || type.equalsIgnoreCase(BallerinaXValueType.LONG.getValue())) {
            return new XRegularValuePresentation(value, type) {
                @Override
                public void renderValue(@NotNull XValueTextRenderer renderer) {
                    renderer.renderValue(value, BallerinaSyntaxHighlightingColors.NUMBER);
                }
            };
        }

        if (type.equalsIgnoreCase(BallerinaXValueType.BOOLEAN.getValue())) {
            return new XValuePresentation() {
                @Override
                public void renderValue(@NotNull XValueTextRenderer renderer) {
                    renderer.renderValue(value, BallerinaSyntaxHighlightingColors.KEYWORD);
                }
            };
        }

        return new XRegularValuePresentation(value, type);
    }

    @Nullable
    private static PsiElement findTargetElement(@NotNull Project project, @NotNull XSourcePosition position,
                                                @NotNull Editor editor, @NotNull String name) {
        // Todo
        return null;
    }

    @Override
    public void computeSourcePosition(@NotNull XNavigatable navigatable) {
        readActionInPooledThread(new Runnable() {

            @Override
            public void run() {
                navigatable.setSourcePosition(findPosition());
            }

            @Nullable
            private XSourcePosition findPosition() {
                XDebugSession debugSession = process.getSession();
                if (debugSession == null) {
                    return null;
                }
                XStackFrame stackFrame = debugSession.getCurrentStackFrame();
                if (stackFrame == null) {
                    return null;
                }
                Project project = debugSession.getProject();
                XSourcePosition position = debugSession.getCurrentPosition();
                Editor editor = ((FileEditorManagerImpl) FileEditorManager.getInstance(project))
                        .getSelectedTextEditor(true);
                if (editor == null || position == null) {
                    return null;
                }
                String name = myName.startsWith("&") ? myName.replaceFirst("\\&", "") : myName;
                PsiElement resolved = findTargetElement(project, position, editor, name);
                if (resolved == null) {
                    return null;
                }
                VirtualFile virtualFile = resolved.getContainingFile().getVirtualFile();
                return XDebuggerUtil.getInstance().createPositionByOffset(virtualFile, resolved.getTextOffset());
            }
        });
    }

    private static void readActionInPooledThread(@NotNull Runnable runnable) {
        ApplicationManager.getApplication().executeOnPooledThread(() ->
                ApplicationManager.getApplication().runReadAction(runnable));
    }

    @NotNull
    @Override
    public ThreeState computeInlineDebuggerData(@NotNull XInlineDebuggerDataCallback callback) {
        computeSourcePosition(callback::computed);
        return ThreeState.YES;
    }

    @Override
    public boolean canNavigateToSource() {
        return true;
    }

    @Override
    public boolean canNavigateToTypeSource() {
        // Todo
        return false;
    }

    @Override
    public void computeTypeSourcePosition(@NotNull XNavigatable navigatable) {
        // Todo
    }

    // Todo - Add the rest of the bal types
    private enum BallerinaXValueType {
        STRING("String"),
        BOOLEAN("Boolean"),
        INT("Int"),
        FLOAT("Float"),
        LONG("Long"),
        DECIMAL("Decimal");

        private String value;

        BallerinaXValueType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
