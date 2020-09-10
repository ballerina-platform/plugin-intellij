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

package io.ballerina.plugins.idea.runconfig.application;

import com.intellij.psi.PsiFile;
import io.ballerina.plugins.idea.psi.impl.BallerinaPsiImplUtil;
import io.ballerina.plugins.idea.runconfig.BallerinaRunConfigurationProducerBase;
import org.jetbrains.annotations.NotNull;

/**
 * Produces Ballerina application configurations.
 */
public class BallerinaApplicationRunConfigurationProducer
        extends BallerinaRunConfigurationProducerBase<BallerinaApplicationConfiguration> implements Cloneable {

    public BallerinaApplicationRunConfigurationProducer() {
        super(BallerinaApplicationRunConfigurationType.getInstance());
    }

    @NotNull
    @Override
    protected String getConfigurationName(@NotNull PsiFile file) {
        String packageName = BallerinaPsiImplUtil.getPackage(file);
        if (!packageName.equals("")) {
            return packageName + " module";
        } else {
            return file.getName();
        }
    }
}
