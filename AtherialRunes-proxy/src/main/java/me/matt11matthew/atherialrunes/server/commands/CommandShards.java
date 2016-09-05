package me.matt11matthew.atherialrunes.server.commands;

import me.matt11matthew.atherialrunes.server.Main;
import me.matt11matthew.atherialrunes.server.shard.ShardManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class CommandShards extends Command {

	public CommandShards(String name) {
		super(name, "shards.list");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		sender.sendMessage(Main.colorCodes("&f&l============================"));
		ShardManager.shards.values().forEach(shard -> {
			sender.sendMessage(Main.colorCodes("&f&l" + shard.getPrefix() + shard.getId() + " &7" + shard.getShardType().getName()));
		});
		sender.sendMessage(Main.colorCodes("&f&l============================"));
		return;	
	}

}
