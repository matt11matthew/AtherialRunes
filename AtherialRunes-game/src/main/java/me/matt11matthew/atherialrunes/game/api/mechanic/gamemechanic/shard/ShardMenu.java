package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.shard;

import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import org.bukkit.Material;

import me.matt11matthew.atherialrunes.item.AtherialItem;
import me.matt11matthew.atherialrunes.menu.Menu;

public class ShardMenu extends Menu {
	
	public static final int GREEN = 5;
	public static final int RED = 14;
	public static final String NAME = "Shard Selector";
	public static final int SLOTS = 9;
	
	public ShardMenu(GamePlayer gp) {
		super(NAME, SLOTS);
		
		ShardMechanics.shards.values().forEach(shard -> {
			Shard info = new Shard(shard.getPseudoName(), shard.getAddress().getAddress(), shard.getAddress().getPort(), shard.getType(), shard.getMaxPlayers(), shard.getBungeeName());


			short dura = (short) ((info.isOnline()) ? 0 : RED);
			if (gp.getShard().equals(shard.getPseudoName())) {
				dura = GREEN;
			}
			AtherialItem item = new AtherialItem(Material.WOOL, dura);
			item.setName("&f&l" + shard.getPseudoName() + " &c(" + info.getOnlinePlayers() + "/" + shard.getMaxPlayers() + ")");
			item.addLore("&7" + shard.getBungeeName());
			addItem(item.build());
		});
	}
}
