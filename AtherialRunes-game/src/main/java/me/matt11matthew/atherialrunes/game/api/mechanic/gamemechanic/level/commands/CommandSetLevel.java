package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.level.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.utils.IntegerUtils;
import me.matt11matthew.atherialrunes.utils.Utils;

public class CommandSetLevel extends AtherialCommand {

	public CommandSetLevel(String command, String usage, String description, List<String> aliases) {
		super(command, usage, description, aliases);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!Rank.isGM(player.getName())) {
				return true;
			}
		}
		if (args.length == 2) {
			String pname = args[0];
			String rawLevel = args[1];
			if (!IntegerUtils.isInt(rawLevel)) {
				sender.sendMessage(Utils.colorCodes("&c/setlevel <player> <level>"));
				return true;
			}
			try {
				GamePlayer gp = Main.getGamePlayer(pname);
				gp.setLevel(Integer.parseInt(rawLevel));
				sender.sendMessage(Utils.colorCodes("&cYou've set " + pname + "'s &lLEVEL &cto " + rawLevel));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
		}
		return true;
	}
}
