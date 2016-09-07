package me.matt11matthew.atherialrunes.game.commands;

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


public class CommandSetRank extends AtherialCommand {

	/**
	 *
	 * @param command the command
	 * @param usage the usage
	 * @param description the description
	 * @param aliases the aliases
	 */
	public CommandSetRank(String command, String usage, String description, List<String> aliases) {
		super(command, usage, description, aliases);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 2) {
				String pname = args[0];
				String rank = args[1];
				try {
					Rank.valueOf(rank.toUpperCase());
				} catch (Exception e) {
					sender.sendMessage(Utils.colorCodes("&cUnknown rank !"));
					return true;
				}
				if ((Bukkit.getPlayerExact(pname) != null) && (Bukkit.getPlayerExact(pname).isOnline())) {
					Player t = Bukkit.getPlayerExact(pname);
					t.sendMessage(Utils.colorCodes("&eYour rank is now" + Rank.valueOf(rank.toUpperCase()).getChatColor() + rank.toUpperCase()));
					
				}
				GamePlayer gp = Main.getGamePlayer(pname);
				gp.setRank(Rank.valueOf(rank.toUpperCase()));
				sender.sendMessage(Utils.colorCodes("&eYou set " + pname + "'s rank to " + Rank.valueOf(rank.toUpperCase()).getChatColor() + rank.toUpperCase()));
				return true;
			} else {
				sender.sendMessage(Utils.colorCodes("&c/setrank <PLAYER> <RANK>"));
				return true;
			}
		}
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.isGM(player.getName())) {
				if (args.length == 2) {
					String pname = args[0];
					String rank = args[1];
					try {
						Rank.valueOf(rank.toUpperCase());
					} catch (Exception e) {
						player.sendMessage(Utils.colorCodes("&cUnknown rank !"));
						return true;
					}
					if ((Bukkit.getPlayerExact(pname) != null) && (Bukkit.getPlayerExact(pname).isOnline())) {
						Player t = Bukkit.getPlayerExact(pname);
						t.sendMessage(Utils.colorCodes("&eYour rank is now" + Rank.valueOf(rank.toUpperCase()).getChatColor() + rank.toUpperCase()));
						
					}
					GamePlayer gp = Main.getGamePlayer(pname);
					gp.setRank(Rank.valueOf(rank.toUpperCase()));
					player.sendMessage(Utils.colorCodes("&eYou set " + pname + "'s rank to " + Rank.valueOf(rank.toUpperCase()).getChatColor() + rank.toUpperCase()));
					return true;
				} else {
					player.sendMessage(Utils.colorCodes("&c/setrank <PLAYER> <RANK>"));
					return true;
				}
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
			if (Rank.isGM(player.getName())) {
				if (args.length == 1) {
					return Utils.getOnlinePlayerNames(args);
				} else if (args.length == 2) {
					List<String> ranks = Rank.getRanks();
					return Utils.getPossibleCompletionsForGivenArgs(args, ranks, false);
				}
				return null;
			}
		} else {
			return null;
		}
		return null;
	}
}
		