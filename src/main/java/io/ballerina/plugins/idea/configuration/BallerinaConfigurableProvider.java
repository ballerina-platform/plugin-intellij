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

import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurableProvider;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.options.UnnamedConfigurable;
import com.intellij.openapi.project.Project;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import io.ballerina.plugins.idea.settings.autodetect.BallerinaAutoDetectionConfigurable;
import io.ballerina.plugins.idea.settings.experimental.BallerinaExperimentalFeatureConfigurable;
import io.ballerina.plugins.idea.settings.langserverlogs.LangServerLogsConfigurable;
import io.ballerina.plugins.idea.settings.soucenavigation.BallerinaSourceNavigationConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Responsible for providing Ballerina configurations in Settings window.
 */
public class BallerinaConfigurableProvider extends ConfigurableProvider {

    @NotNull
    private final Project myProject;

    public BallerinaConfigurableProvider(@NotNull Project project) {
        myProject = project;
    }

    @Nullable
    @Override
    public Configurable createConfigurable() {
        //        Configurable projectSettingsConfigurable = new BallerinaProjectSettingsConfigurable(myProject);
        Configurable librariesConfigurable = new BallerinaLibrariesConfigurableProvider(myProject).createConfigurable();
        Configurable sdkConfigurable = BallerinaSdkService.getInstance(myProject).createSdkConfigurable();
        Configurable autoDetectionConfigurable = new BallerinaAutoDetectionConfigurable(myProject, false);
        Configurable experimentalFeatureConfigurable = new BallerinaExperimentalFeatureConfigurable(myProject, false);
        Configurable lsLogConfigurable = new LangServerLogsConfigurable(myProject, false);
        Configurable sourceNavigationConfigurable = new BallerinaSourceNavigationConfigurable(myProject, false);

        BallerinaCompositeConfigurable configurableWithSDK = new BallerinaCompositeConfigurable(sdkConfigurable,
                librariesConfigurable, autoDetectionConfigurable, experimentalFeatureConfigurable,
                lsLogConfigurable, sourceNavigationConfigurable);
        BallerinaCompositeConfigurable configurableWithoutSDK = new BallerinaCompositeConfigurable(
                librariesConfigurable, autoDetectionConfigurable, experimentalFeatureConfigurable,
                lsLogConfigurable, sourceNavigationConfigurable);

        return sdkConfigurable != null ? configurableWithSDK : configurableWithoutSDK;
    }

    private static class BallerinaCompositeConfigurable extends SearchableConfigurable.Parent.Abstract {

        private Configurable[] myConfigurables;

        public BallerinaCompositeConfigurable(Configurable... configurables) {
            myConfigurables = configurables;
        }

        @Override
        protected Configurable[] buildConfigurables() {
            return myConfigurables;
        }

        @NotNull
        @Override
        public String getId() {
            return "Ballerina";
        }

        @Nls
        @Override
        public String getDisplayName() {
            return "Ballerina";
        }

        @Nullable
        @Override
        public String getHelpTopic() {
            return null;
        }

        @Override
        public void disposeUIResources() {
            super.disposeUIResources();
            myConfigurables = null;
        }
    }

    /**
     * Responsible for creating project settings.
     */
    public static class BallerinaProjectSettingsConfigurable extends BallerinaModuleAwareConfigurable {

        public BallerinaProjectSettingsConfigurable(@NotNull Project project) {
            super(project, "Project Settings", null);
        }

        @NotNull
        @Override
        protected UnnamedConfigurable createModuleConfigurable(Module module) {
            return new BallerinaModuleSettingsConfigurable(module, false);
        }
    }
}
