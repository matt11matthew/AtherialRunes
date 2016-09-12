package me.matt11matthew.atherialrunes.game.utils.async;

import me.matt11matthew.atherialrunes.game.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;

public class GameUtils {

    public static <T> void submitAsyncCallback(Callable<T> callable, Consumer<Future<T>> consumer) {
        // FUTURE TASK //
        FutureTask<T> task = new FutureTask<>(callable);

        // BUKKIT'S ASYNC SCHEDULE WORKER
        new BukkitRunnable() {
            @Override
            public void run() {
                // RUN FUTURE TASK ON THREAD //
                task.run();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        // ACCEPT CONSUMER //
                        if (consumer != null)
                            consumer.accept(task);
                    }
                }.runTask(Main.getInstance());
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    /**
     * Utility type for calling async tasks with callbacks.
     *
     * @param callable Callable type
     * @param consumer Consumer task
     * @param <T>      Type of data
     * @author apollosoftware
     */
    public static <T> void submitAsyncWithAsyncCallback(Callable<T> callable, Consumer<Future<T>> consumer) {
        // FUTURE TASK //
        FutureTask<T> task = new FutureTask<>(callable);

        // BUKKIT'S ASYNC SCHEDULE WORKER
        new BukkitRunnable() {
            @Override
            public void run() {
                // RUN FUTURE TASK ON THREAD //
                task.run();
                // ACCEPT CONSUMER //
                if (consumer != null)
                    consumer.accept(task);
            }
        }.runTaskAsynchronously(Main.getInstance());
    }
}
