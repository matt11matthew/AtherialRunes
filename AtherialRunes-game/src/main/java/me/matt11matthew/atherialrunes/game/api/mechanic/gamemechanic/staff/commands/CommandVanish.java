package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandVanish extends AtherialCommand {

	public CommandVanish(String command, String usage, String description, List<String> aliases) {
		super(command, usage, description, aliases);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.isGM(player.getName())) {
				vanish(player);
				return true;
			}
		}
		return true;
	}

	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		return null;
	}
	
	public static void vanish(Player player) {
		GamePlayer gp = Main.getGamePlayer(player.getName());
		if (gp.isVanished()) {
			gp.setVanished(false);
			gp.msg(MessageType.ACTION, "&aYour now visible");
			player.setGameMode(GameMode.SURVIVAL);
			return;
		} else {
			gp.setVanished(true);
			gp.msg(MessageType.ACTION, "&aYour now invisible");
			player.setGameMode(GameMode.SPECTATOR);
			return;
		}
	}
}
		