/*
 * Copyright (c) 2019, WSO2 Inc. (http://wso2.com) All Rights Reserved.
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
package io.ballerina.plugins.idea.extensions.server;

import com.google.gson.JsonElement;

/**
 * Represents a Ballerina AST response.
 */
public class BallerinaASTResponse {

    private JsonElement ast;
    private boolean parseSuccess;

    public BallerinaASTResponse(JsonElement ast, boolean parseSuccess) {
        this.ast = ast;
        this.parseSuccess = parseSuccess;
    }

    public JsonElement getAst() {
        return ast;
    }

    public void setAst(JsonElement ast) {
        this.ast = ast;
    }

    public boolean isParseSuccess() {
        return parseSuccess;
    }

    public void setParseSuccess(boolean parseSuccess) {
        this.parseSuccess = parseSuccess;
    }
}
