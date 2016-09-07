package me.matt11matthew.atherialrunes.game.api.registry;

import java.lang.reflect.Field;

/**
 * Created by Giovanni on 5-9-2016.
 */
public interface DataRegistry
{
    boolean call();

    Field get(Class<?> caller);
}
