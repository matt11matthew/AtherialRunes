package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic;

import me.matt11matthew.atherialrunes.game.GameConstants;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;

public class ServerMechanics extends ListenerMechanic {

    boolean deny = false;

    @Override
    public void onEnable() {
        print("-----------------------------------------");
        print("[ServerMechanics] Enabling...");
        print("-----------------------------------------");
        registerListeners();
    }

    @Override
    public void onDisable() {
        deny = true;
        kick();
        print("-----------------------------------------");
        print("[ServerMechanics] Disabling...");
        print("-----------------------------------------");
    }

    private void kick() {
        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            player.kickPlayer(GameConstants.SERVER_REBOOTING_KICK_MESSAGE);
        });
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        if (deny) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "\n" + GameConstants.SERVER_REBOOTING_KICK_MESSAGE);
        }
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.MONITOR;
    }
}
