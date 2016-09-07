package me.matt11matthew.atherialrunes.exceptions;

public class NotJsonFileException extends Exception {

    public NotJsonFileException(String par1) {
        super(par1 + " is not a json file.");
    }
}
