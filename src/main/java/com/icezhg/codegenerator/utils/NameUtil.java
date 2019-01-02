package com.icezhg.codegenerator.utils;


import java.util.Arrays;

/**
 * Created by zhongjibing on 2018/12/27.
 */
public class NameUtil {
    private static final String REGEX_SEPARATOR = "_";
    private static final String REGEX_REPLACEMENT = "^is_";

    public static String className(String tableName) {
        String[] names = tableName.toLowerCase().split(REGEX_SEPARATOR);
        StringBuilder className = new StringBuilder();
        for (String s : names) {
            className.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        }
        return className.toString();
    }

    public static String fieldName(String columnName) {
        String[] names = columnName.toLowerCase().replaceFirst(REGEX_REPLACEMENT, "").split(REGEX_SEPARATOR);
        StringBuilder fieldName = new StringBuilder(names[0]);
        for (String s : Arrays.copyOfRange(names, 1, names.length)) {
            fieldName.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        }
        return fieldName.toString();
    }

}
