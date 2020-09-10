/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.ballerina;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;

/**
 * Parent class related to code insight tests.
 */
public abstract class BallerinaCodeInsightFixtureTestCase extends LightPlatformCodeInsightFixtureTestCase {

    protected static String getTestDataPath(String path) {
        return "src/test/resources/testData/" + path;
    }

    protected void setUpProjectSdk() {
        ApplicationManager.getApplication().runWriteAction(() -> {
            Sdk sdk = getProjectDescriptor().getSdk();
            ProjectJdkTable.getInstance().addJdk(sdk);
            ProjectRootManager.getInstance(myFixture.getProject()).setProjectSdk(sdk);
        });
    }

    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return null;
    }
}
