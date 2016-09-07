package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.mute;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandUnmute extends AtherialCommand {

	public CommandUnmute(String command, String usage, String description) {
		super(command, usage, description);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			//ban player <time> <reason>
			if (args.length >= 1) {
				String pname = args[0];
				GamePlayer gp = Main.getGamePlayer(pname);
				String reasonString = null;
				if (args.length > 1) {
					StringBuilder reason = new StringBuilder(args[1]);
		            for (int arg = 1; arg < args.length; arg++) reason.append(" ").append(args[arg]);
		            reasonString = reason.toString();
				}
				if (reasonString == null) {
					reasonString = "Unmuted";
				}
				sender.sendMessage(Utils.colorCodes("&c&lUNMUTED &c" + pname + ""));
				sender.sendMessage(Utils.colorCodes("&f&lReason: &c" + reasonString));
				Main.sendMessageToStaff("&c" + sender.getName() + " has &lUNMUTED &c" + pname);
				gp.unmute(reasonString);
				return true;
			}
		}
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!Rank.isStaff(player.getName())) {
				return true;
			}
			if (args.length >= 1) {
				String pname = args[0];
				GamePlayer gp = Main.getGamePlayer(pname);
				String reasonString = null;
				if (args.length > 1) {
					StringBuilder reason = new StringBuilder(args[1]);
		            for (int arg = 1; arg < args.length; arg++) reason.append(" ").append(args[arg]);
		            reasonString = reason.toString();
				}
				if (reasonString == null) {
					reasonString = "Unmuted";
				}
				player.sendMessage(Utils.colorCodes("&c&lUNMUTED &c" + pname + ""));
				player.sendMessage(Utils.colorCodes("&f&lReason: &c" + reasonString));
				Main.sendMessageToStaff("&c" + player.getName() + " has &lUNMUTED &c" + pname);
				gp.unmute(reasonString);
				return true;
			}
		}
		return true;
	}

	public List<String> getOnlinePlayers() {
		List<String> players = new ArrayList<String>();
		for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
			players.add(pl.getName());
		}
		return players;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.isStaff(player.getName())) {
				if (args.length == 1) {
					return Utils.getOnlinePlayerNames(args);
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
		return null;
	}
}
		