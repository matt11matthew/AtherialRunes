package me.matt11matthew.atherialrunes.game;

import me.matt11matthew.atherialrunes.game.registry.DataRegistry;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Giovanni on 6-9-2016.
 */
public class ItemRegistry implements DataRegistry
{
    //TODO The value must be the custom item. <?>
    private ConcurrentHashMap<ItemStack, ?> itemRegistry;

    @Override
    public boolean call()
    {
        this.itemRegistry = new ConcurrentHashMap<>();
        return false;
    }

    @Override
    public ConcurrentHashMap<?, ?> getRegistry()
    {
        return itemRegistry;
    }

    @Override
    public Field get(Class<?> caller)
    {
        try
        {
            return caller.getDeclaredField("profile." + caller.getName() + ".meta");
        } catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

