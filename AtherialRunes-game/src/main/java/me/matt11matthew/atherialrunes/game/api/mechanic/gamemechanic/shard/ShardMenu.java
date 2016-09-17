package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.shard;

import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.network.ShardInfo;
import me.matt11matthew.atherialrunes.item.AtherialItem;
import me.matt11matthew.atherialrunes.menu.Menu;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShardMenu extends Menu {

	public static final int GREEN = 5;
	public static final int RED = 14;
	public static final String NAME = "Shard Selector";
	public static final int SLOTS = 9;

	public ShardMenu(GamePlayer gp) {
		super(NAME, SLOTS);

		AtherialItem divider = new AtherialItem(Material.THIN_GLASS);
		divider.setName(" ");

		List<ShardInfo> shards = new ArrayList<>();
		ShardMechanics.shards.values().forEach(shard -> {
			shards.add(shard);
		});

		Collections.sort(shards, (i1, i2) -> new Integer(i2.getShard().getOnlinePlayers()).compareTo(i1.getShard().getOnlinePlayers()));

		shards.forEach(shard -> {
			addItem(shard.getShardItem(gp).build());
		});

		int i = 0;
		for (ItemStack is : inventory.getContents()) {
			if ((is == null) || (is.getType() == Material.AIR)) {
				setItem(i, divider.build());
			}
			i++;
		}
	}
}
