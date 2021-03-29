package com.epam.movieTheater.utility;

public class ServiceUtility {
    public static void outputMessageToConsole(String text, Object expression) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.append(text).append(expression));
    }
}
