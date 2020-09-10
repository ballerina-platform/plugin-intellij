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

package io.ballerina.plugins.idea.configuration;

import com.intellij.ui.HideableDecorator;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Hide-able decorator to list all roots.
 */
class ListenableHideableDecorator extends HideableDecorator {

    private final Collection<MyListener> myListeners = ContainerUtil.newSmartList();

    ListenableHideableDecorator(@NotNull JPanel panel, @NotNull String displayName,
                                @NotNull JComponent content) {
        super(panel, displayName, false);
        setContentComponent(content);
    }

    void addListener(@NotNull MyListener listener) {
        myListeners.add(listener);
    }

    @Override
    protected void on() {
        for (MyListener listener : myListeners) {
            listener.on();
        }
        super.on();
    }

    @Override
    protected void off() {
        for (MyListener listener : myListeners) {
            listener.beforeOff();
        }
        super.off();
        for (MyListener listener : myListeners) {
            listener.afterOff();
        }
    }

    public static class MyListener {
        public void on() {

        }

        public void beforeOff() {

        }

        public void afterOff() {

        }
    }
}
