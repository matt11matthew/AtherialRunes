package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.shard;

import org.bukkit.Material;

import me.matt11matthew.atherialrunes.item.AtherialItem;
import me.matt11matthew.atherialrunes.menu.Menu;
import me.matt11matthew.atherialrunes.network.bungeecord.BungeeServerInfo;

public class ShardMenu extends Menu {
	
	public static final int GREEN = 5;
	public static final int RED = 14;
	public static final String NAME = "Shard Selector";
	public static final int SLOTS = 9;
	
	public ShardMenu() {
		super(NAME, SLOTS);
		
		ShardMechanics.shards.values().forEach(shard -> {
			BungeeServerInfo info = new BungeeServerInfo(shard.getShardID());
			AtherialItem item = new AtherialItem(Material.WOOL, (short) ((info.isOnline()) ? GREEN : RED));
			item.setName("&f&l" + shard.getPseudoName() + " &c(" + info.getOnlinePlayers() + "/" + info.getMaxPlayers() + ")");
			item.addLore("&7" + shard.getShardID());
			addItem(item.build());
		});
	}
}
