package me.matt11matthew.atherialrunes.game.api.exception;

/**
 * Created by Giovanni on 5-9-2016.
 */
public class LoaderNotHandledException extends Exception
{
    public LoaderNotHandledException(String par1)
    {
        super("Failed to handle registry loading; " + par1);
    }
}
