package com.techelevator;

import java.util.HashMap;
import java.util.Map;

public class MessingAround {
    public String animalGroupName(String animalName) {
        Map<String, String> animalMap = new HashMap<>();
        animalMap.put("rhino","Crash");
        animalMap.put("giraffe","Tower");
        animalMap.put("elephant","Herd");
        animalMap.put("lion","Pride");
        animalMap.put("crow","Murder");
        animalMap.put("pigeon","Kit");
        animalMap.put("flamingo","Pat");
        animalMap.put("deer","Herd");
        animalMap.put("dog","Pack");
        animalMap.put("crocodile","Float");

        for (String key : animalMap.keySet()) {
            System.out.println(animalMap.get(key));
        }

        if (!animalMap.containsKey(animalName.toLowerCase())) {
            return animalMap.get(animalName.toLowerCase());
        } else {
            return "unknown";
        }
    }

}
