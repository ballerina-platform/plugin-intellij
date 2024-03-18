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

package io.ballerina.plugins.idea;

import com.intellij.testFramework.ParsingTestCase;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Handles the test cases for parser. Running the tests and displaying the results.
 *
 * @since 2.0.0
 */
public class BallerinaParsingTest extends ParsingTestCase {

    private static final Logger LOGGER = Logger.getLogger(BallerinaParsingTest.class.getName());
    private static FileHandler fileHandler;

    // This field is used to store the current test name.
    private String myTestName = "";

    static {
        try {
            // Delete the log file if it exists
            File logFile = new File("BallerinaParsingTest.log");
            if (logFile.exists()) {
                logFile.delete();
            }

            fileHandler = new FileHandler("BallerinaParsingTest.log", true);
            LOGGER.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.setLevel(Level.INFO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error setting up logger", e);
        }
    }

    public BallerinaParsingTest() {
        super("", "bal", new BallerinaParserDefinition());
    }

    // This test will run for each folder in the testData directory.
    public void testParsingTestData() {
        String[] testDirectories1 = new File(getTestDataPath()).list();
        if (testDirectories1 == null) {
            return;
        }

        for (String directory1 : testDirectories1) {
            processDirectoryLevel1(directory1);
        }
    }

    private void processDirectoryLevel1(String directory1) {
        String[] testDirectories2 = new File(getTestDataPath() + "/" + directory1).list();
        if (testDirectories2 == null) {
            return;
        }

        for (String directory2 : testDirectories2) {
            processDirectoryLevel2(directory1, directory2);
        }
    }

    private void processDirectoryLevel2(String directory1, String directory2) {
        String[] testDirectories3 = new File(getTestDataPath() + "/" + directory1 + "/" + directory2).list();
        if (testDirectories3 == null) {
            return;
        }

        for (String directory3 : testDirectories3) {
            processDirectoryLevel3(directory1, directory2, directory3);
        }
    }

    private void processDirectoryLevel3(String directory1, String directory2, String directory3) {
        String testDirPath = getTestDataPath() + "/" + directory1 + "/" + directory2 + "/" + directory3;
        File testDir = new File(testDirPath);

        if (!testDir.isDirectory()) {
            return;
        }

        myTestName = directory1 + "/" + directory2 + "/" + directory3 + "/" + directory3;
        try {
            LOGGER.info("Starting test: " + myTestName);
            doTest(true);
            LOGGER.info("Test passed: " + myTestName);
        } catch (Exception e) {
            LOGGER.info("Test failed: " + myTestName);
        }
    }

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/Parsing";
    }

    @Override
    protected boolean skipSpaces() {
        return true;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

    // Override getTestName to use the directory name as the test name
    @Override
    protected @NotNull String getTestName(boolean lowercaseFirstLetter) {
        return lowercaseFirstLetter ? myTestName.toLowerCase() : myTestName;
    }

    @Override
    protected void tearDown() throws Exception {
        try {
            if (fileHandler != null) {
                fileHandler.close();
            }
        } finally {
            super.tearDown();
        }
    }
}
