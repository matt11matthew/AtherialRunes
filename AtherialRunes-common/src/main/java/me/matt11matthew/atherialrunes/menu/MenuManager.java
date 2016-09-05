package me.matt11matthew.atherialrunes.menu;

import java.util.HashMap;

public class MenuManager {
	
	public static HashMap<String, Menu> menus = new HashMap<String, Menu>();
	
	public static void registerMenu(Menu menu) {
		menus.put(menu.getTitle(), menu);
	}
}
