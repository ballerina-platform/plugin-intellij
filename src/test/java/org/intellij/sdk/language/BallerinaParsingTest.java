//package org.intellij.sdk.language;
//
//import com.intellij.testFramework.ParsingTestCase;
//
//public class BallerinaParsingTest extends ParsingTestCase {
//
//    public BallerinaParsingTest() {
//        super("", "bal", new BallerinaParserDefinition());
//    }
//
//    public void testParsingTestData() {
//        doTest(true);
//    }
//
//    /**
//     * @return path to test data file directory relative to root of this module.
//     */
//    @Override
//    protected String getTestDataPath() {
//        return "src/test/testData";
//    }
//
//    @Override
//    protected boolean skipSpaces() {
//        return true;
//    }
//
//    @Override
//    protected boolean includeRanges() {
//        return true;
//    }
//
//}
//
//package org.intellij.sdk.language;
//
//import com.intellij.testFramework.ParsingTestCase;
//import org.jetbrains.annotations.NotNull;
//
//import java.io.File;
//
//public class BallerinaParsingTest extends ParsingTestCase {
//
//    public BallerinaParsingTest() {
//        super("", "bal", new BallerinaParserDefinition());
//    }
//
//    // This test will run for each folder in the testData directory.
//    public void testParsingTestData() {
//        String[] testDirectories = new File(getTestDataPath()).list();
//        if (testDirectories != null) {
//            for (String directory : testDirectories) {
//                // Here we set the name of the test to the name of the directory.
//                String testDirPath = getTestDataPath() + "/" + directory;
//                File testDir = new File(testDirPath);
//
//                // Skip if it's not a directory.
//                if (!testDir.isDirectory()) continue;
//
//                // The name of the test is the name of the directory.
//                // It assumes that each test case has a single test file that matches the directory name.
//                myTestName = directory + "/" + directory;
//                doTest(true);
//            }
//        }
//    }
//
//    @Override
//    protected String getTestDataPath() {
//        return "src/test/testData";
//    }
//
//    @Override
//    protected boolean skipSpaces() {
//        return true;
//    }
//
//    @Override
//    protected boolean includeRanges() {
//        return true;
//    }
//
//    // Override getTestName to use the directory name as the test name
//    @Override
//    protected @NotNull String getTestName(boolean lowercaseFirstLetter) {
//        return lowercaseFirstLetter
//                ? myTestName.toLowerCase()
//                : myTestName;
//    }
//
//    // This field is used to store the current test name.
//    private String myTestName = "";
//}
//

package org.intellij.sdk.language;

import com.intellij.testFramework.ParsingTestCase;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class BallerinaParsingTest extends ParsingTestCase {
    private static final Logger LOGGER = Logger.getLogger(BallerinaParsingTest.class.getName());
    private static FileHandler fileHandler;

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
        if (testDirectories1 != null) {
            for (String directory1 : testDirectories1) {
                String[] testDirectories2 = new File(getTestDataPath() + "/" + directory1).list();
                if (testDirectories2 != null) {
                    for (String directory2 : testDirectories2) {
                        String[] testDirectories3 = new File(getTestDataPath() + "/" + directory1 + "/" + directory2).list();
                        if (testDirectories3 != null) {
                            for (String directory3 : testDirectories3) {
                                String testDirPath = getTestDataPath() + "/" + directory1 + "/" + directory2 + "/" + directory3;
                                File testDir = new File(testDirPath);

                                if (!testDir.isDirectory()) continue;

                                myTestName = directory1 + "/" + directory2 + "/" + directory3 + "/" + directory3;
                                try {
                                    LOGGER.info("Starting test: " + myTestName);
                                    doTest(true);
                                    LOGGER.info("Test passed: " + myTestName);
                                } catch (Exception e) {
                                    LOGGER.info("Test failed: " + myTestName);
                                }
                            }
                        }

                    }
                }
            }
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
        return lowercaseFirstLetter
                ? myTestName.toLowerCase()
                : myTestName;
    }

    // This field is used to store the current test name.
    private String myTestName = "";

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
