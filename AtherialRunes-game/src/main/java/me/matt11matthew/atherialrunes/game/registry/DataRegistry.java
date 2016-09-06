package me.matt11matthew.atherialrunes.game.registry;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Giovanni on 5-9-2016.
 */
public interface DataRegistry
{
    boolean call();

    ConcurrentHashMap<?, ?> getRegistry();

    Field get(Class<?> caller);
}
