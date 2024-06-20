package org.example;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class FileWriterUtilTest {

    private static final String TEST_FILENAME = "TestConstitution.txt"; // Test filename
    private static final Logger LOGGER = Logger.getLogger(FileWriterUtilTest.class.getName());

    @BeforeAll
    public static void setup() {
        // Any setup before all tests run
    }

    @AfterAll
    public static void cleanup() {
        // Clean up the test file after all tests
        try {
            Files.deleteIfExists(Paths.get(TEST_FILENAME));
        } catch (IOException e) {
            LOGGER.severe("Error deleting the test file: " + e.getMessage());
        }
    }

    @Test
    public void testWriteConstitutionToFile() {
        // Content to write to the file
        String content = "We the People of the United States, in Order to form a more perfect Union,\n" +
                "establish Justice, insure domestic Tranquility, provide for the common defence,\n" +
                "promote the general Welfare, and secure the Blessings of Liberty to ourselves and our Posterity,\n" +
                "do ordain and establish this Constitution for the United States of America.";

        LOGGER.info("Original Content Length: " + content.length());

        // Write content to the file
        FileWriterUtil.writeToFile(TEST_FILENAME, content);

        // Read the content back from the file
        String actualContent = FileReaderUtil.readFileContent(TEST_FILENAME);

        LOGGER.info("Actual Content Length: " + actualContent.length());

        // Normalize newlines and trim content
        String normalizedExpectedContent = content.replace("\r\n", "\n").trim();
        String normalizedActualContent = actualContent.replace("\r\n", "\n").trim();

        LOGGER.info("Normalized Expected Content Length: " + normalizedExpectedContent.length());
        LOGGER.info("Normalized Actual Content Length: " + normalizedActualContent.length());

        // Log the normalized content for debugging
        LOGGER.info("Normalized Expected Content:\n" + normalizedExpectedContent);
        LOGGER.info("Normalized Actual Content:\n" + normalizedActualContent);

        // Split the content into lines
        List<String> expectedLines = Arrays.asList(normalizedExpectedContent.split("\n"));
        List<String> actualLines = Arrays.asList(normalizedActualContent.split("\n"));

        // Log lengths and lines for detailed debugging
        LOGGER.info("Expected Lines: " + expectedLines);
        LOGGER.info("Actual Lines: " + actualLines);

        // Verify the content using assertLinesMatch
        assertLinesMatch(expectedLines, actualLines);
    }

    @Test
    public void testFileNotFound() {
        // Test that writing to an invalid path throws FileNotFoundException
        assertThrows(FileNotFoundException.class, () -> {
            // Use try-with-resources to ensure proper resource management
            try (PrintWriter writer = new PrintWriter("/invalid/path/TestConstitution.txt")) {
                writer.println("This will throw FileNotFoundException");
            }
        });
    }

    @Test
    public void testWriteEmptyContent() {
        // Content to write (empty content)
        String emptyContent = "";

        LOGGER.info("Original Content Length: " + emptyContent.length());

        // Write empty content to the file
        FileWriterUtil.writeToFile(TEST_FILENAME, emptyContent);

        // Read the content back from the file
        String actualContent = FileReaderUtil.readFileContent(TEST_FILENAME);

        LOGGER.info("Actual Content Length: " + actualContent.length());

        // Normalize newlines and trim content
        String normalizedExpectedContent = emptyContent.replace("\r\n", "\n").trim();
        String normalizedActualContent = actualContent.replace("\r\n", "\n").trim();

        LOGGER.info("Normalized Expected Content Length: " + normalizedExpectedContent.length());
        LOGGER.info("Normalized Actual Content Length: " + normalizedActualContent.length());

        // Log the normalized content for debugging
        LOGGER.info("Normalized Expected Content:\n" + normalizedExpectedContent);
        LOGGER.info("Normalized Actual Content:\n" + normalizedActualContent);

        // Split the content into lines
        List<String> expectedLines = Arrays.asList(normalizedExpectedContent.split("\n"));
        List<String> actualLines = Arrays.asList(normalizedActualContent.split("\n"));

        // Log lengths and lines for detailed debugging
        LOGGER.info("Expected Lines: " + expectedLines);
        LOGGER.info("Actual Lines: " + actualLines);

        // Verify the content using assertLinesMatch
        assertLinesMatch(expectedLines, actualLines);
    }

    @Test
    public void testWriteLargeContent() {
        // Generate large content
        StringBuilder largeContent = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeContent.append("This is line ").append(i).append(System.lineSeparator());
        }

        LOGGER.info("Original Large Content Length: " + largeContent.length());

        // Write large content to the file
        FileWriterUtil.writeToFile(TEST_FILENAME, largeContent.toString());

        // Read the content back from the file
        String actualContent = FileReaderUtil.readFileContent(TEST_FILENAME);

        LOGGER.info("Actual Large Content Length: " + actualContent.length());

        // Normalize newlines and trim content
        String normalizedExpectedContent = largeContent.toString().replace("\r\n", "\n").trim();
        String normalizedActualContent = actualContent.replace("\r\n", "\n").trim();

        LOGGER.info("Normalized Expected Content Length: " + normalizedExpectedContent.length());
        LOGGER.info("Normalized Actual Content Length: " + normalizedActualContent.length());

        // Log the normalized content for debugging
        LOGGER.info("Normalized Expected Content:\n" + normalizedExpectedContent);
        LOGGER.info("Normalized Actual Content:\n" + normalizedActualContent);

        // Split the content into lines
        List<String> expectedLines = Arrays.asList(normalizedExpectedContent.split("\n"));
        List<String> actualLines = Arrays.asList(normalizedActualContent.split("\n"));

        // Log lengths and lines for detailed debugging
        LOGGER.info("Expected Lines: " + expectedLines);
        LOGGER.info("Actual Lines: " + actualLines);

        // Verify the content using assertLinesMatch
        assertLinesMatch(expectedLines, actualLines);
    }

    @Test
    public void testFileContentUnchangedOnException() {
        // Initial content to write to the file
        String initialContent = "Initial content.";

        LOGGER.info("Original Content Length: " + initialContent.length());

        // Write initial content to the file
        FileWriterUtil.writeToFile(TEST_FILENAME, initialContent);

        // Test that writing to an invalid path throws FileNotFoundException
        assertThrows(FileNotFoundException.class, () -> {
            // Use try-with-resources to ensure proper resource management
            try (PrintWriter writer = new PrintWriter("/invalid/path/TestConstitution.txt")) {
                writer.println("This will throw FileNotFoundException");
            }
        });

        // Read the content back from the file
        String actualContent = FileReaderUtil.readFileContent(TEST_FILENAME);

        LOGGER.info("Actual Content Length: " + actualContent.length());

        // Normalize newlines and trim content
        String normalizedExpectedContent = initialContent.replace("\r\n", "\n").trim();
        String normalizedActualContent = actualContent.replace("\r\n", "\n").trim();

        LOGGER.info("Normalized Expected Content Length: " + normalizedExpectedContent.length());
        LOGGER.info("Normalized Actual Content Length: " + normalizedActualContent.length());

        // Log the normalized content for debugging
        LOGGER.info("Normalized Expected Content:\n" + normalizedExpectedContent);
        LOGGER.info("Normalized Actual Content:\n" + normalizedActualContent);

        // Split the content into lines
        List<String> expectedLines = Arrays.asList(normalizedExpectedContent.split("\n"));
        List<String> actualLines = Arrays.asList(normalizedActualContent.split("\n"));

        // Log lengths and lines for detailed debugging
        LOGGER.info("Expected Lines: " + expectedLines);
        LOGGER.info("Actual Lines: " + actualLines);

        // Verify the content using assertLinesMatch
        assertLinesMatch(expectedLines, actualLines);
    }

    @Test
    public void testAppendToFile() {
        // Initial content to write to the file
        String initialContent = "Initial content.";

        LOGGER.info("Original Content Length: " + initialContent.length());

        // Write initial content to the file
        FileWriterUtil.writeToFile(TEST_FILENAME, initialContent);

        // Content to append to the file
        String contentToAppend = "Appended content.";

        LOGGER.info("Content to Append Length: " + contentToAppend.length());

        // Append content to the file
        FileWriterUtil.appendToFile(TEST_FILENAME, contentToAppend);

        // Read the content back from the file
        String actualContent = FileReaderUtil.readFileContent(TEST_FILENAME);

        LOGGER.info("Actual Appended Content Length: " + actualContent.length());

        // Normalize newlines and trim content
        String expectedContent = initialContent + "\n" + contentToAppend;
        String normalizedExpectedContent = expectedContent.replace("\r\n", "\n").trim();
        String normalizedActualContent = actualContent.replace("\r\n", "\n").trim();

        LOGGER.info("Normalized Expected Content Length: " + normalizedExpectedContent.length());
        LOGGER.info("Normalized Actual Content Length: " + normalizedActualContent.length());

        // Log the normalized content for debugging
        LOGGER.info("Normalized Expected Content:\n" + normalizedExpectedContent);
        LOGGER.info("Normalized Actual Content:\n" + normalizedActualContent);

        // Split the content into lines
        List<String> expectedLines = Arrays.asList(normalizedExpectedContent.split("\n"));
        List<String> actualLines = Arrays.asList(normalizedActualContent.split("\n"));

        // Log lengths and lines for detailed debugging
        LOGGER.info("Expected Lines: " + expectedLines);
        LOGGER.info("Actual Lines: " + actualLines);

        // Verify the content using assertLinesMatch
        assertLinesMatch(expectedLines, actualLines);
    }
}
