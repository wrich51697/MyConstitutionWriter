package org.example;

/*
 * Utility class for writing content to a file.
 */

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class FileWriterUtil {

    private static final Logger LOGGER = Logger.getLogger(FileWriterUtil.class.getName());

    /**
     * Writes the provided content to a specified file.
     * @param filename The name of the file to write to.
     * @param content The content to write to the file.
     */
    public static void writeToFile(String filename, String content) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(content);
            LOGGER.info("Successfully wrote to the file: " + filename);
        } catch (FileNotFoundException e) {
            LOGGER.severe("Error: The file could not be created or opened. " + e.getMessage());
        }
    }

    /**
     * Appends the provided content to a specified file.
     * @param filename The name of the file to append to.
     * @param content The content to append to the file.
     */
    public static void appendToFile(String filename, String content) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename, true))) {
            writer.println(content);
            LOGGER.info("Successfully appended to the file: " + filename);
        } catch (FileNotFoundException e) {
            LOGGER.severe("Error: The file could not be created or opened for appending. " + e.getMessage());
        } catch (IOException e) {
            LOGGER.severe("Error: An I/O error occurred. " + e.getMessage());
        }
    }
}

