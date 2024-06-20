package org.example;

/*
 * This program writes the opening lines of the US Constitution to a file named "MyConstitution.txt"
 * and reads the content back for verification.
 */

public class MyConstitutionWriter {

    public static void main(String[] args) {
        String filename = "MyConstitution.txt";
        String content = "We the People of the United States, in Order to form a more perfect Union,\n" +
                "establish Justice, insure domestic Tranquility, provide for the common defence,\n" +
                "promote the general Welfare, and secure the Blessings of Liberty to ourselves and our Posterity,\n" +
                "do ordain and establish this Constitution for the United States of America.";
        FileWriterUtil.writeToFile(filename, content);

        // Append additional text to the file
        String additionalContent = "This is an appended line.";
        FileWriterUtil.appendToFile(filename, additionalContent);
    }
}
