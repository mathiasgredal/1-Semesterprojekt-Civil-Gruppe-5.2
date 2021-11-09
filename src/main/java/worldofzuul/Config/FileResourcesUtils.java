package worldofzuul.Config;

import worldofzuul.Exceptions.CannotFindResourceException;

import java.io.*;


public class FileResourcesUtils {
    /**
     * This is a way to get files in the resource folder independent of the
     * place it is run from(IDEA, jar, unit, maven...)
     * From: github.com/mkyong/core-java/blob/master/java-io/src/main/java/com/mkyong/io/utils/FileResourcesUtils.java#L74
     * License: MIT
     *
     * @param fileName relative to the resource folder
     * @return A stream with the file in
     * @author Yong Mook Kim
     */
    public static InputStream getFileFromResourceAsStream(String fileName) throws CannotFindResourceException {
        ClassLoader classLoader = FileResourcesUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new CannotFindResourceException(fileName);
        } else {
            return inputStream;
        }
    }
}
