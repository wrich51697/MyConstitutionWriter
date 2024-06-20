# FileWriterUtil and FileReaderUtil User Guide

## Overview

`FileWriterUtil` and `FileReaderUtil` are utility classes designed to simplify file operations in Java. 
They provide methods to write, append, and read content from text files.

## FileWriterUtil Methods

### writeToFile(String fileName, String content)

Writes the specified content to the specified file. If the file already exists, it will be overwritten.

**Usage Example:**
```java
FileWriterUtil.writeToFile("example.txt", "Hello, World!");
```
### appendToFile(String fileName, String content)
Appends the specified content to the specified file. If the file does not exist, it will be created.

Usage Example:
``` java
FileWriterUtil.appendToFile("example.txt", "Appending this content.");
```
## FileReaderUtil Methods
### readFileContent(String fileName)
Reads the content of the specified file and returns it as a String.

Usage Example:
```java
String content = FileReaderUtil.readFileContent("example.txt");
```
### Unit Tests
We have comprehensive unit tests to ensure the reliability and correctness of these utilities.
### Running Tests
To run the tests, use your IDE's built-in test runner or run the following command in the terminal:
```shell
mvn test
```
## Unit Test Cases
### testWriteConstitutionToFile
Verifies that the content of the Constitution is written to the file correctly.

### Key Points:
- Writes a predefined string to a file.
- Reads back the content and compares it to the expected value.
### testFileNotFound
Ensures that writing to an invalid path throws a FileNotFoundException.

### testWriteEmptyContent
Confirms that writing empty content to a file results in an empty file.

### testWriteLargeContent
Checks that large content is written to the file correctly.

### testFileContentUnchangedOnException
Ensures that the file content remains unchanged if an exception occurs while writing.

### testAppendToFile
Verifies that content can be appended to the file correctly.

## Example Usage in Tests
Here are some examples of how the tests are structured:

### testWriteConstitutionToFile {id="testwriteconstitutiontofile_1"}
```java
@Test
public void testWriteConstitutionToFile() {
    String content = "We the People of the United States, in Order to form a more perfect Union,\n" +
            "establish Justice, insure domestic Tranquility, provide for the common defence,\n" +
            "promote the general Welfare, and secure the Blessings of Liberty to ourselves and our Posterity,\n" +
            "do ordain and establish this Constitution for the United States of America.";
    
    FileWriterUtil.writeToFile(TEST_FILENAME, content);
    String actualContent = FileReaderUtil.readFileContent(TEST_FILENAME);
    assertLinesMatch(Arrays.asList(content.split("\n")), Arrays.asList(actualContent.split("\n")));
}
```
### testFileNotFound {id="testfilenotfound_1"}
```java
@Test
public void testFileNotFound() {
    assertThrows(FileNotFoundException.class, () -> {
        try (PrintWriter writer = new PrintWriter("/invalid/path/TestConstitution.txt")) {
            writer.println("This will throw FileNotFoundException");
        }
    });
}
```
### testAppendToFile {id="testappendtofile_1"}
```java
@Test
public void testAppendToFile() {
    String initialContent = "Initial content.";
    String contentToAppend = "Appended content.";
    
    FileWriterUtil.writeToFile(TEST_FILENAME, initialContent);
    FileWriterUtil.appendToFile(TEST_FILENAME, contentToAppend);
    String actualContent = FileReaderUtil.readFileContent(TEST_FILENAME);
    
    String expectedContent = initialContent + "\n" + contentToAppend;
    assertLinesMatch(Arrays.asList(expectedContent.split("\n")), Arrays.asList(actualContent.split("\n")));
}
```
### testWriteEmptyContent {id="testwriteemptycontent_1"}
```java
@Test
public void testWriteEmptyContent() {
    FileWriterUtil.writeToFile(TEST_FILENAME, "");
    String actualContent = FileReaderUtil.readFileContent(TEST_FILENAME);
    assertEquals("", actualContent);
}
```
### testWriteLargeContent {id="testwritelargecontent_1"}
```java
@Test
public void testWriteLargeContent() {
    StringBuilder largeContent = new StringBuilder();
    for (int i = 0; i < 1000; i++) {
        largeContent.append("This is line ").append(i).append("\n");
    }
    String expectedContent = largeContent.toString();
    
    FileWriterUtil.writeToFile(TEST_FILENAME, expectedContent);
    String actualContent = FileReaderUtil.readFileContent(TEST_FILENAME);
    assertLinesMatch(Arrays.asList(expectedContent.split("\n")), Arrays.asList(actualContent.split("\n")));
}
```
### testFileContentUnchangedOnException {id="testfilecontentunchangedonexception_1"}
```java
@Test
public void testFileContentUnchangedOnException() {
    String initialContent = "Initial content.";
    FileWriterUtil.writeToFile(TEST_FILENAME, initialContent);
    
    // Simulate an exception by passing a null content
    assertThrows(NullPointerException.class, () -> {
        FileWriterUtil.writeToFile(TEST_FILENAME, null);
    });
    
    String actualContent = FileReaderUtil.readFileContent(TEST_FILENAME);
    assertEquals(initialContent, actualContent);
}
```
## Conclusion
The FileWriterUtil and FileReaderUtil classes, along with their unit tests, ensure reliable and consistent file operations.
Keep this guide handy for reference, and always ensure that your tests cover all possible scenarios to maintain robustness.