package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.zone;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;

public class ZoneMechanics extends ListenerMechanic
{

    @Override
    public void onEnable()
    {
        print("[ZoneMechanics] Enabling...");
        registerListeners();
    }

    @Override
    public void onDisable()
    {
        print("[ZoneMechanics] Disabing...");
    }

    @Override
    public LoadPriority getLoadPriority()
    {
        return LoadPriority.NORMAL;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        Player p = e.getPlayer();
        Location from = e.getFrom();
        Location to = e.getTo();
        if ((from.getX() != to.getX()) || (from.getY() != to.getY()) || (from.getZ() != to.getZ()))
        {
            e.setCancelled(true);
            p.teleport(from);
        }
    }

}