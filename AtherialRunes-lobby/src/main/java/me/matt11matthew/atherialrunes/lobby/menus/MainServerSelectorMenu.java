package me.matt11matthew.atherialrunes.lobby.menus;

import me.matt11matthew.atherialrunes.item.AtherialItem;
import me.matt11matthew.atherialrunes.menu.Menu;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

public class MainServerSelectorMenu extends Menu {

    public MainServerSelectorMenu() {
        super("&c&lServer Selector", 9);

        AtherialItem atherialrunes = new AtherialItem(Material.BEDROCK);
        atherialrunes.setName("&b&lAtherial Runes &c&lComing Soon");
        atherialrunes.addLore("&7https://discord.gg/xc98pdD");
        atherialrunes.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);

        AtherialItem practiceServer = new AtherialItem(Material.GOLD_SWORD);
        practiceServer.setName("&bOG DR PracticeServer &7(Click)");
        practiceServer.addLore("&7https://discord.gg/xc98pdD");
        practiceServer.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);

        AtherialItem dr = new AtherialItem(Material.END_CRYSTAL);
        dr.setName("&aAtherial Runes DR &7(Click)");
        dr.addLore("&7A fork of DungeonRealms!");
        dr.addLore("&7Connects you to DR lobby!");

        AtherialItem infoBook = new AtherialItem(Material.BOOK);
        infoBook.setName("&6&lWelcome To Atherial Runes");
        infoBook.addLore("&7Click on a server");
        infoBook.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);

        setItem(2, practiceServer.build());
        setItem(4, dr.build());
        setItem(6, atherialrunes.build());
        setItem(8, infoBook.build());

    }
}
