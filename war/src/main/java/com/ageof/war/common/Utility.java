package com.ageof.war.common;

import org.slf4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utility {

    public static List<Map.Entry<String, Integer>> convertToMap(String input, String platoonDelimitter, String platoonAndItsCountDelimitter){
        Function<String, Map.Entry<String, Integer>> getPlatoonAndCountEntry = platoonWithCount -> {
            String[] info = platoonWithCount.split(platoonAndItsCountDelimitter);
            return Map.entry(info[0], Integer.parseInt(info[1]));
        };

        return Stream.of( input.strip().split(platoonDelimitter) )
        .map(getPlatoonAndCountEntry)
        .collect(Collectors.toList());
    }

    public static void printErrorLog(Logger logger, Object errorMessage){
        String errorColor = "\u001B[31m";
        logger.info(errorColor + errorMessage);
    }
}
