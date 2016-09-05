package me.matt11matthew.atherialrunes.menu;

import org.bukkit.Material;

import me.matt11matthew.atherialrunes.item.AtherialItem;

public class TestMenu extends Menu {

	public TestMenu() {
		super("Test", 9);
		
		AtherialItem test = new AtherialItem(Material.APPLE);
		test.addLore("&7Test");
		test.addLore("&7test1");
		test.setName("&aTest Item");
		
		addItem(test.build());
	}
}
