package me.matt11matthew.atherialrunes.lobby;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.matt11matthew.atherialrunes.item.AtherialItem;
import me.matt11matthew.atherialrunes.lobby.menus.MainServerSelectorMenu;
import me.matt11matthew.atherialrunes.utils.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack hand = player.getEquipment().getItemInMainHand();
        if ((hand != null) && (hand.getType() == Material.COMPASS)) {
            openServerSelector(player, 1);
        }
    }

    public static void sendToServer(String playerName, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(playerName);
        out.writeUTF(serverName);
        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);

        if (player != null)
            player.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            return;
        }
        e.setCancelled(true);
        if (e.getClickedInventory().getTitle().equals(Utils.colorCodes("&c&lServer Selector"))) {

            ItemStack cur = e.getCurrentItem();
            if ((cur != null) && (cur.getType() != Material.AIR)) {
                switch (e.getSlot()) {
                    case 2:
                        player.closeInventory();
                        player.sendMessage(Utils.colorCodes("&aSending you to PracticeServer"));
                        sendToServer(player.getName(), "practiceserver");
                        break;
                    case 4:
                        player.closeInventory();
                        player.sendMessage(Utils.colorCodes("&aSending..."));
                        sendToServer(player.getName(), "lobby");
                        break;
                    case 6:
                        player.closeInventory();
                        player.sendMessage(Utils.colorCodes("&c&lAtherial Runes is still in development!"));
                        TextComponent comp1 = new TextComponent(Utils.colorCodes("&aDiscord Link -> "));
                        TextComponent comp = new TextComponent(Utils.colorCodes("&6&l[Click]"));
                        comp.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/xc98pdD"));
                        comp1.addExtra(comp);
                        player.spigot().sendMessage(comp1);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player player = e.getPlayer();
        player.getInventory().clear();
        AtherialItem compass = new AtherialItem(Material.COMPASS);
        compass.setName("&aServer Selector &7(Right Click)");
        player.getInventory().setItem(0, compass.build());

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onInteractHandler(PlayerInteractEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerRunCommand(PlayerCommandPreprocessEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Bukkit.getServer().broadcastMessage(Utils.colorCodes("&7" + e.getPlayer().getName() + ": " + e.getMessage()));
    }

    public void openServerSelector(Player player, int menu) {
        switch (menu) {
            case 1:
                new MainServerSelectorMenu().open(player);
                break;
            default:
                break;
        }
    }
}
