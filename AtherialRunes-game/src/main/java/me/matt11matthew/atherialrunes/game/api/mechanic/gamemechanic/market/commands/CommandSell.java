package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.gear.weapon.Weapon;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.MarketMechanics;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandSell extends AtherialCommand {

    public CommandSell(String command, String usage, String description) {
        super(command, usage, description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            GamePlayer gp = Main.getGamePlayer(player);
            try {
                if (gp.getWeapon() != Weapon.FIST) {
                    ItemStack weapon = gp.getWeaponItem();
                    int price = Integer.parseInt(args[0]);
                    MarketMechanics.getInstance().sellItem(weapon, price, gp.getName());
                    gp.msg(MessageType.CHAT, "&cYou've sold an item for " + price + "g");
                    return true;
                }
            } catch (NumberFormatException e) {
                gp.msg(MessageType.CHAT, "&c/sell <price>");
                return true;
            }
        }
        return true;
    }
}
