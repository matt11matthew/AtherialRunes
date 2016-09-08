package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.notoriety;

import me.matt11matthew.atherialrunes.game.GameConstants;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.notoriety.events.WorldChangeToDayEvent;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.utils.AtherialRunnable;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;

public class NotorietyMechanics extends ListenerMechanic {

    boolean canCheck = true;

    @Override
    public void onEnable() {
        print("[NotorietyMechanics] Enabling...");
        registerListeners();
        task();
        timeTask();
        timeTask2();
    }

    @Override
    public void onDisable() {
        print("[NotorietyMechanics] Disabling...");
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.LOWEST;
    }

    public void task() {
        AtherialRunnable.getInstance().runRepeatingTask(new Runnable() {

            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    GamePlayer gp = Main.getGamePlayer(player);
                    int notoriety = gp.getNotoriety();
                    if ((notoriety >= 15) && (notoriety <= 24)) {
                        gp.setNameColor("&a");
                    } else if ((notoriety >= 25) && (notoriety <= 49)) {
                        gp.setNameColor("&e");
                    } else if ((notoriety >= 50) && (notoriety <= 74)) {
                        gp.setNameColor("&6");
                    } else if ((notoriety >= 75) && (notoriety <= 99)) {
                        gp.setNameColor("&c");
                    } else if (notoriety == 100) {
                        gp.setNameColor("&4");
                    } else {
                        gp.setNameColor("&7");
                    }
                });
            }
        }, 5L, 5L);
    }

    public void timeTask() {
        AtherialRunnable.getInstance().runRepeatingTask(new Runnable() {

            @Override
            public void run() {
                if (!canCheck) {
                    return;
                }
                World world = Bukkit.getWorld(GameConstants.WORLD_NAME);
                if (world.getTime() == GameConstants.MORNING_AT_TIME) {
                    Bukkit.getPluginManager().callEvent(new WorldChangeToDayEvent(world));
                    canCheck = false;
                }
            }
        }, 5L, 5L);
    }

    public void timeTask2() {
        AtherialRunnable.getInstance().runRepeatingTask(new Runnable() {

            @Override
            public void run() {
                if (!canCheck) {
                    canCheck = true;
                }
            }
        }, 20L, 20L);
    }

    @EventHandler
    public void onMorning(WorldChangeToDayEvent e) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            GamePlayer gp = Main.getGamePlayer(player);
            gp.daytime();
        });
    }
}