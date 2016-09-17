package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.spawner.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.spawner.SpawnerMechanics;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import me.matt11matthew.atherialrunes.game.utils.IntegerUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandHideMs extends AtherialCommand {
    public CommandHideMs(String command, String usage, String description, List<String> aliases) {
        super(command, usage, description, aliases);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            GamePlayer gp = Main.getGamePlayer(player);
            if (Rank.isGM(gp.getName())) {
                if (args.length == 1) {
                    if (IntegerUtils.isInt(args[0])) {
                        int amount = 0;
                        for (Location location : SpawnerMechanics.spawners.keySet()) {
                            if (location.distance(player.getLocation()) <= Integer.parseInt(args[0])) {
                                location.getBlock().setType(Material.AIR);
                                amount++;
                            }
                        }
                        gp.msg(MessageType.CHAT, "&aHiding " + amount + " spawners");
                        gp.msg(MessageType.CHAT, "&7Radius: " + args[0].trim());
                        return true;
                    } else {
                        gp.msg(MessageType.CHAT, "&c/hidems <radius>");
                        return true;
                    }
                } else {
                    gp.msg(MessageType.CHAT, "&c/hidems <radius>");
                    return true;
                }
            }
        }
        return true;
    }
}