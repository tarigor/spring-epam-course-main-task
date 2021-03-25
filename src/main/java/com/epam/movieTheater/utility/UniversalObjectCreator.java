package com.epam.movieTheater.utility;

import com.epam.movieTheater.entity.Rating;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class UniversalObjectCreator {

    private static Object myObj;

    public static Object objectCreator(String className) {

        Scanner scanner = new Scanner(System.in);
        ArrayList parametersSet = new ArrayList<>();
        Class[] parametersType = null;
        try {
            Class clazz = Class.forName("com.epam.movieTheater.entity." + className);
            int i = 0;
            int j = 0;
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                Parameter[] parameters = constructor.getParameters();

                parametersType = new Class[parameters.length];
                System.out.println("lenght " + parameters.length);

                for (Parameter p : parameters) {
                    parametersType[i] = p.getType();
                    //parameters set
                    System.out.println("type: " + p.getType());
                    if (parametersType[i].getName().contains("String")) {
                        System.out.println("input a " + p.getName() + " parameter which is String type:");
                        String field = scanner.nextLine();
                        parametersSet.add(j, field);
                    }
                    if (parametersType[i].getName().contains("Integer")) {
                        System.out.println("input a " + p.getName() + " parameter which is Integer type:");
                        Integer field = scanner.nextInt();
                        parametersSet.add(j, field);
                    }
                    if (parametersType[i].getName().contains("Double")) {
                        System.out.println("input a " + p.getName() + " parameter which is Double type:");
                        scanner.useLocale(Locale.UK);
                        Double field1 = scanner.nextDouble();
                        parametersSet.add(j, field1);
                    }
                    if (parametersType[i].getName().contains("Rating")) {
                        System.out.println("input a " + p.getName() + " parameter which is Rating type:");
                        String field1 = scanner.next();
                        parametersSet.add(j, Rating.valueOf(field1));
                    }
                    if (parametersType[i].getName().contains("Date")) {
                        System.out.println("input a " + p.getName() + " parameter which is Date type (dd.MM.yyy/hh:mm):");
                        String field1 = scanner.next();
                        Date date = new SimpleDateFormat("dd.MM.yyy/hh:mm").parse(field1);
                        parametersSet.add(j, date);
                    }
                    i++;
                    j++;
                }
            }
            Constructor obj = clazz.getDeclaredConstructor(parametersType);
            myObj = obj.newInstance(parametersSet.toArray());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return myObj;
    }
}
