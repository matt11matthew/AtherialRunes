package me.matt11matthew.atherialrunes.game.utils.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncUtils {

    public static ExecutorService pool = Executors.newFixedThreadPool(14);
}
