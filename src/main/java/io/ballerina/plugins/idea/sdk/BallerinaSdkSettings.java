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

package io.ballerina.plugins.idea.sdk;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Stores ballerina sdk selection settings.
 *
 * @since 2.0.0
 */
@Service(Service.Level.APP)
@State(name = "BallerinaSdkSettings", storages = @Storage("ballerinaSdkSettings.xml"))
public final class BallerinaSdkSettings implements PersistentStateComponent<BallerinaSdkSettings.State> {

    private State myState = new State();

    public static BallerinaSdkSettings getInstance() {
        return ServiceManager.getService(BallerinaSdkSettings.class);
    }

    @Nullable
    @Override
    public State getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull State state) {
        this.myState = state;
    }

    public String getBallerinaSdkPath() {
        return myState.ballerinaSdkPath;
    }

    public void setBallerinaSdkPath(String path) {
        myState.ballerinaSdkPath = path;
    }

    public String getBallerinaSdkVersion() {
        return myState.ballerinaSdkVersion;
    }

    public void setBallerinaSdkVersion(String version) {
        myState.ballerinaSdkVersion = version;
    }

    public boolean isUseCustomSdk() {
        return myState.useCustomSdk;
    }

    public void setUseCustomSdk(boolean useCustomSdk) {
        myState.useCustomSdk = useCustomSdk;
    }

    public static class State {

        public boolean useCustomSdk = false;
        public String ballerinaSdkPath = "";
        public String ballerinaSdkVersion = "";
    }
}
