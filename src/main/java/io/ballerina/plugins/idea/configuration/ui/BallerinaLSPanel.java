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

package io.ballerina.plugins.idea.configuration.ui;

import com.intellij.util.ui.JBUI;
import io.ballerina.plugins.idea.preloading.BallerinaLSConfigSettings;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Ballerina language server related settings panel.
 *
 * @since 2.0.0
 */
public class BallerinaLSPanel implements BallerinaSettingsPanel {

    private JCheckBox traceLogsCheckBox = new JCheckBox("Enable language server trace logs");
    private JCheckBox debugLogsCheckBox = new JCheckBox("Enable language server debug logs");
    private final JPanel panel = new JPanel(new GridBagLayout());

    public BallerinaLSPanel() {
        init();
    }

    @Override
    public JComponent getPanel() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.insets = JBUI.insets(2, 0);

        panel.add(BallerinaSettingsPanelTitle.getTitleComponent("Language server settings"), gbc);
        gbc.gridy++;

        panel.add(traceLogsCheckBox, gbc);
        gbc.gridy++;

        panel.add(debugLogsCheckBox, gbc);
        gbc.gridy++;

        return panel;
    }

    @Override
    public void disposeUi() {
        traceLogsCheckBox = null;
        debugLogsCheckBox = null;
        panel.removeAll();
    }

    private void init() {
        traceLogsCheckBox.setSelected(BallerinaLSConfigSettings.getInstance().isTraceLog());
        debugLogsCheckBox.setSelected(BallerinaLSConfigSettings.getInstance().isDebugLog());
    }

    public boolean isTraceLogsEnabled() {
        return traceLogsCheckBox.isSelected();
    }

    public boolean isDebugLogsEnabled() {
        return debugLogsCheckBox.isSelected();
    }
}
