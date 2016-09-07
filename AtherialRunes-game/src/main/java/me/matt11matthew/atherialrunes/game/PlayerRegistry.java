package me.matt11matthew.atherialrunes.game;

import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.api.registry.DataRegistry;

import java.lang.reflect.Field;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Giovanni on 5-9-2016.
 */
public class PlayerRegistry implements DataRegistry
{
    private ConcurrentHashMap<UUID, GamePlayer> playerRegistry;

    @Override
    public boolean call()
    {
        this.playerRegistry = new ConcurrentHashMap<>();
        return false;
    }

    public ConcurrentHashMap<UUID, GamePlayer> getRegistry()
    {
        return playerRegistry;
    }

    public GamePlayer getPlayer(UUID uuid)
    {
        return playerRegistry.get(uuid);
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
