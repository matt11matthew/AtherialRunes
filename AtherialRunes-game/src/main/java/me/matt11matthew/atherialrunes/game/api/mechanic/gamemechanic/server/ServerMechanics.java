package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.server;

import me.matt11matthew.atherialrunes.game.GameConstants;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.utils.AtherialRunnable;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServerMechanics extends ListenerMechanic {

    boolean deny = false;
    private long rebootTime = 0L;
    static ServerMechanics instance = null;

    @Override
    public void onEnable() {
        print("-----------------------------------------");
        print("[ServerMechanics] Enabling...");
        print("-----------------------------------------");
        registerListeners();
        setRebootTime(4, 0, 0);
    }

    public static ServerMechanics getInstance() {
        if (instance == null) {
            instance = new ServerMechanics();
        }
        return instance;
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

    public long getRebootTime() {
        return rebootTime;
    }

    public long getSecondsUntilReboot() {
        long second = TimeUnit.MILLISECONDS.toSeconds(rebootTime) - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        if (second - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) > 0) {
            second = second - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        }
        return second;
    }

    int rebootTask = 0;

    public void rebootTask() {
        rebootTask = AtherialRunnable.getInstance().runRepeatingTask(() -> {
            long seconds = getSecondsUntilReboot();
            String msg = null;
            switch ((int) seconds) {
                case 1800:
                    msg = "&cServer rebooting in 30 Minutes";
                    break;
                case 300:
                    msg = "&cServer rebooting in 10 Minutes";
                    break;
                case 120:
                    msg = "&cServer rebooting in 2 Minutes";
                    break;
                case 60:
                    msg = "&cServer rebooting in 1 Minute";
                    break;
                case 1:
                    msg = "&cServer rebooting in 1 Second";
                    break;
                case 0:
                    msg = "&cServer rebooting...";
                    reboot();
                    break;
                default:
                    break;
            }
            if ((seconds == 30) || (seconds == 10) || ((seconds <= 5) && (seconds > 1))) {
                msg = "&cServer rebooting in " + seconds + " Seconds";
            }
            if (msg != null) {
                Bukkit.getServer().broadcastMessage(Utils.colorCodes(msg));
                msg = null;
            }
        }, 20L, 20L);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        String msg = e.getMessage();
        List<String> bannedCommands = new ArrayList<>(Arrays.asList("/rl", "/reload", "/restart", "/stop", "/minecraft:", "/bukkit:", "me"));
        bannedCommands.stream().filter(banned -> banned.contains(msg)).forEach(banned -> {
            e.setCancelled(true);
        });
    }

    private void reboot() {
        deny = true;
        AtherialRunnable.getInstance().cancelTask(rebootTask);
        kick();
        Bukkit.getServer().spigot().restart();
    }

    public String parseRebootTime() {
        long seconds = getSecondsUntilReboot();
        int hour = 0;
        int minute = 0;
        int second = (int) seconds;
        while (second >= 60) {
            second -= 60;
            minute += 1;
        }
        while (minute >= 60) {
            minute -= 60;
            hour += 1;
        }
        return hour + "&a" + hour + "&lh &a" + minute + "&lm &a" + second + "&ls";
    }

    public void setRebootTime(long rebootTime) {
        long time = (System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(rebootTime));
        this.rebootTime = time;
    }

    public void setRebootTime(int hours, int minutes, int seconds) {
        int min = minutes;
        int sec = seconds;
        int hour = hours;
        while (hour > 0) {
            min += 60;
            hour -= 1;
        }
        while (min > 0) {
            sec += 60;
            min -= 1;
        }
        setRebootTime(sec);
        return;
    }
}
