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

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Custom configuration options for Ballerina Language server.
 *
 * @since 2.0.0
 */
@Service(Service.Level.APP)
@State(name = "BallerinaLSConfigSettings", storages = @Storage("ballerinaLSConfigSettings.xml"))
public final class BallerinaLSConfigSettings implements PersistentStateComponent<BallerinaLSConfigSettings.State> {

    private State myState = new State();

    public static BallerinaLSConfigSettings getInstance() {
        return ServiceManager.getService(BallerinaLSConfigSettings.class);
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

    public void setDebugLog(boolean debugLog) {
        myState.debugLog = debugLog;
    }

    public void setTraceLog(boolean traceLog) {
        myState.traceLog = traceLog;
    }

    public boolean isDebugLog() {
        return myState.debugLog;
    }

    public boolean isTraceLog() {
        return myState.traceLog;
    }


    public static class State {

        public boolean debugLog = false;
        public boolean traceLog = false;
    }
}
