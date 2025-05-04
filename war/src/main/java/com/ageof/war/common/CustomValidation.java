package com.ageof.war.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class CustomValidation {

    private static Logger logger = LoggerFactory.getLogger(CustomValidation.class);

    public static boolean checkForValidInput(String input){
        final String regex = "(([a-zA-Z]+#[0-9]+);)*([a-zA-Z]+#[0-9]+)";
        return Pattern.matches(regex, input);
    }

    public static void raiseErrorIfInputIsInvalidOrNotInProperFormat(String input){
        if(!checkForValidInput(input)){
            Utility.printErrorLog(logger, "Input is invalid (or) Not in proper format as expected");
            System.exit(0);
        }
    }
}
