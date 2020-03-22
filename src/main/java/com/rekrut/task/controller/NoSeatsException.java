package com.rekrut.task.controller;

public class NoSeatsException extends Exception {
    public NoSeatsException(String message){
        super("No seats left!" + message);
    }
}
