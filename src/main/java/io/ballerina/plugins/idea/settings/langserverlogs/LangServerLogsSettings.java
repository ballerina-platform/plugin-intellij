/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package io.ballerina.plugins.idea.settings.langserverlogs;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Attribute;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Language server debug logs settings provider.
 */
@State(name = "LangServerLogs", storages = @Storage(value = "editor.langserver.logs.xml"))
public class LangServerLogsSettings implements PersistentStateComponent<LangServerLogsSettings> {

    @Attribute
    private boolean enableLangServerDebugLogs = false;
    @Attribute
    private boolean enableLangServerTraceLogs = false;

    public static LangServerLogsSettings getInstance(Project project) {
        return ServiceManager.getService(project, LangServerLogsSettings.class);
    }

    @Nullable
    @Override
    public LangServerLogsSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull LangServerLogsSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public boolean isLangServerDebugLogsEnabled() {
        return enableLangServerDebugLogs;
    }

    public boolean isLangServerTraceLogsEnabled() {
        return enableLangServerTraceLogs;
    }

    public void setIsLangServerDebugLogsEnabled(boolean enableLangServerDebugLogs) {
        this.enableLangServerDebugLogs = enableLangServerDebugLogs;
    }

    public void setIsLangServerTraceLogsEnabled(boolean enableLangServerTraceLogs) {
        this.enableLangServerTraceLogs = enableLangServerTraceLogs;
    }
}
