package com.ageof.war.exception;

public class NoChanceOfWinning extends RuntimeException {

    @Override
    public String getMessage() {
        return "There is no chance of winning";
    }
}
