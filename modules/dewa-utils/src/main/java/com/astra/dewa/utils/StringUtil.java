package com.astra.dewa.utils;

import java.io.File;
import java.net.URL;

public class StringUtil {

    public static boolean hasValue(Object o) {
        if (o == null) {
            return false;
        } else if (o.toString().trim().equals("")) {
            return false;
        } else if (o.toString().isEmpty()) {
            return false;
        } else if (o.toString().equals("0")) {
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String str) {
        try {
            Double aDouble = new Double(nullFloat(str.trim()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String nullFloat(String str) {
        return (!hasValue(str)) ? "0" : str;
    }

    public static String getFileExtension(File file) {
        String extension = "";
        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }
        return extension;
    }

    public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    public static String[] splitBySpace(String value) {
        return value.split("\\s+");
    }
}
