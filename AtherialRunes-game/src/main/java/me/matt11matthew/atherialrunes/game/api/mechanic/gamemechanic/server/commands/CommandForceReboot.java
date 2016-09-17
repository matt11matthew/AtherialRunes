package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.server.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.network.NetworkUtils;
import me.matt11matthew.atherialrunes.game.network.ShardInfo;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandForceReboot extends AtherialCommand {
    public CommandForceReboot(String command, String usage, String description) {
        super(command, usage, description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!Rank.isGM(player.getName())) {
                return true;
            }
        }
        if (args.length == 1) {
            ShardInfo shard = ShardInfo.getByPseudoName(args[0]);
            if (shard == null) {
                sender.sendMessage(Utils.colorCodes("&c/forcereboot <shard>"));
                return true;
            }
            NetworkUtils.sendPacketCrossServer("[reboot]shard:" + shard.getPseudoName() + ",who:" + sender.getName(), Integer.parseInt(shard.getShardID()), true);
            sender.sendMessage(Utils.colorCodes("&aRebooting " + shard.getPseudoName()));
            return true;
        } else {
            sender.sendMessage(Utils.colorCodes("&c/forcereboot <shard>"));
            return true;
        }
    }
}