package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.item.AtherialItem;
import me.matt11matthew.atherialrunes.menu.Menu;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CommandChannel extends AtherialCommand {

    public CommandChannel(String command, String usage, String description, List<String> aliases) {
        super(command, usage, description, aliases);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            GamePlayer gp = Main.getGamePlayer(player);
            new ChannelMenu(gp).open(player);
            return true;
        }
        return true;
    }

    public class ChannelMenu extends Menu {

        public static final String CHAT_CHANNEL_MENU_NAME = "Chat Channel Selector";
        public static final int CHAT_CHANNEL_MENU_SLOTS = 9;
        public ChannelMenu(GamePlayer gp) {
            super(CHAT_CHANNEL_MENU_NAME, CHAT_CHANNEL_MENU_SLOTS);

            AtherialItem local = new AtherialItem(Material.CHEST);
            local.setName("&7Local Chat");
            local.addLore("&7&oAllows you to speak in local chat");

            AtherialItem global = new AtherialItem(Material.ENDER_PORTAL_FRAME);
            global.setName("&bGlobal Chat");
            global.addLore("&7&oAllows you to speak in global chat");

            AtherialItem trade = new AtherialItem(Material.ENDER_CHEST);
            trade.setName("&aTrade Chat");
            trade.addLore("&7&oAllows you to speak in trade chat");

            AtherialItem guild = new AtherialItem(Material.ENCHANTMENT_TABLE);
            guild.setName("&3Guild Chat");
            guild.addLore("&7&oAllows you to speak in guild chat");

            AtherialItem div = new AtherialItem(Material.STAINED_GLASS_PANE, (short) 14);
            div.setName(" ");
            div.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
            ItemStack divider = div.build();

            switch (gp.getChatChannel()) {
                case LOCAL:
                    local.addGlow();
                    local.addItemFlag(ItemFlag.HIDE_ENCHANTS);
                    break;
                case GLOBAL:
                    global.addGlow();
                    global.addItemFlag(ItemFlag.HIDE_ENCHANTS);
                    break;
                case TRADE:
                    trade.addGlow();
                    trade.addItemFlag(ItemFlag.HIDE_ENCHANTS);
                    break;
            }
            setItem(0, divider);
            setItem(1, local.build());
            setItem(2, divider);
            setItem(3, global.build());
            setItem(4, divider);
            setItem(5, trade.build());
            setItem(6, divider);
            setItem(7, guild.build());
            setItem(8, divider);
        }
    }
}
