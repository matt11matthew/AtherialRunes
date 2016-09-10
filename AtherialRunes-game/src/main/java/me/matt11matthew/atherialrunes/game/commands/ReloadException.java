package me.matt11matthew.atherialrunes.game.commands;

public class ReloadException extends Exception {

    public ReloadException(String var1) {
        super("Could not reload " + var1);
    }
}
