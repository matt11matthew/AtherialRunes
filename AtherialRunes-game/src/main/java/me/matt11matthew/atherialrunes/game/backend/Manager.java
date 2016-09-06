package me.matt11matthew.atherialrunes.game.backend;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by Giovanni on 6-9-2016.
 */
@Target(value = ElementType.TYPE)
public @interface Manager
{
    EnumType[] type() default {EnumType.LOADER, EnumType.MANAGER};

    @Target(value = ElementType.TYPE)
    public @interface NetworkManager
    {
        EnumType[] type() default {EnumType.LOADER, EnumType.MANAGER};
    }
}
