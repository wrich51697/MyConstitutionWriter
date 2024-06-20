package org.example;

/*
 * Utility class for reading content from a file.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class FileReaderUtil {

    private static final Logger LOGGER = Logger.getLogger(FileReaderUtil.class.getName());

    /**
     * Reads the content of a specified file and returns it as a string.
     * @param filename The name of the file to read from.
     * @return The content of the file as a string.
     */
    public static String readFileContent(String filename) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            LOGGER.info("Successfully read from the file: " + filename);
        } catch (IOException e) {
            LOGGER.severe("Error reading the file: " + e.getMessage());
        }
        return content.toString().trim();
    }
}

