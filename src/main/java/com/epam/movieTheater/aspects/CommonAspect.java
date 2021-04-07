package com.epam.movieTheater.aspects;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommonAspect {
    public void outputCounter(Object obj, Map<String, Integer> map, String descriptor) {
        if (obj != null) {
            System.out.println("\n" + descriptor);
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }
}
