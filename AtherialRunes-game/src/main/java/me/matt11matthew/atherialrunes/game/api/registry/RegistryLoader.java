package me.matt11matthew.atherialrunes.game.api.registry;

import com.google.common.collect.Maps;
import me.matt11matthew.atherialrunes.game.api.backend.EnumType;
import me.matt11matthew.atherialrunes.game.api.backend.Manager;
import me.matt11matthew.atherialrunes.game.api.exception.LoaderNotHandledException;

import java.util.Map;

/**
 * Created by Giovanni on 5-9-2016.
 * <p>
 * A class that loads/assembles/disassembles data holding registries.
 */
@Manager(type = {EnumType.ASSEMBLY, EnumType.DISASSEMBLY, EnumType.LOADER})
public class RegistryLoader
{
    private static Map<String, DataRegistry> registryMap;

    private static RegistryLoader registryLoader;

    private static boolean loaded;

    public static RegistryLoader load() throws LoaderNotHandledException
    {
        if (!loaded)
        {
            try
            {
                registryLoader = new RegistryLoader();
                registryMap = Maps.newHashMap();
                loaded = true;
                return registryLoader;
            } catch (Exception e)
            {
                System.out.println("RegistryLoader not initialized properly.");
            }
        } else
        {
            throw new LoaderNotHandledException(".load() handled twice.");
        }
        return null;
    }

    public static RegistryLoader call() throws LoaderNotHandledException
    {
        if (!loaded)
        {
            throw new LoaderNotHandledException("loader not initialized");
        } else
        {
            return registryLoader;
        }
    }

    public RegistryLoader register(DataRegistry dataRegistry) throws LoaderNotHandledException
    {
        if (loaded)
        {
            registryMap.put(dataRegistry.getClass().getName(), dataRegistry);
        } else
        {
            throw new LoaderNotHandledException("loader not initialized.");
        }
        return registryLoader;
    }

    public RegistryLoader manageLoad()
    {
        registryMap.values().forEach(DataRegistry::call);
        return registryLoader;
    }

    public static Map<String, DataRegistry> getRegistries()
    {
        return registryMap;
    }

    public DataRegistry getRegistryByName(String name)
    {
        for (DataRegistry dataRegistry : registryMap.values())
        {
            if (dataRegistry.getClass().getName() == name)
            {
                return dataRegistry;
            }
        }
        return null;
    }
}
