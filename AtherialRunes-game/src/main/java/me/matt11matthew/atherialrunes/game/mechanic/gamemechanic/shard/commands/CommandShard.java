package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.shard.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.shard.ShardMenu;
import me.matt11matthew.atherialrunes.game.player.GamePlayer;

public class CommandShard extends AtherialCommand {
	
	public CommandShard(String command, String usage, String description) {
		super(command, usage, description);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			GamePlayer gp = Main.getGamePlayer(player.getName());
			if (gp.isInCombat()) {
				gp.msg(MessageType.CHAT, "&cYou &ncannot&c shard while in &lCOMBAT");
				gp.msg(MessageType.CHAT, "&7You can shard in &c" + gp.getCombatTime() + "&ls");
				return true;
			}
			new ShardMenu().open(player);
			return true;
		}
		return false;
	}
}