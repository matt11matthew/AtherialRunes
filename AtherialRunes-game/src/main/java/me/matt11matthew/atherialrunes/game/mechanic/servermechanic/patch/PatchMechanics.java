package me.matt11matthew.atherialrunes.game.mechanic.servermechanic.patch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scheduler.BukkitRunnable;

import io.netty.buffer.Unpooled;
import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.utils.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_9_R2.EnumHand;
import net.minecraft.server.v1_9_R2.PacketDataSerializer;
import net.minecraft.server.v1_9_R2.PacketPlayOutCustomPayload;


public class PatchMechanics extends ListenerMechanic {
	
	private ItemStack patchBook;

	@Override
	public void onEnable() {
		print("[PatchMechanics] Enabling...");
		registerListeners();
		
		loadBook();
	}

	private void loadBook() {
		List<String> pages = new ArrayList<>();

        try {
            // READ PATCH NOTE FILE //
            InputStream fileIn = Main.getInstance().getResource("patchnotes.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(fileIn));

            String str;
            int curCharacters = 0;

            StringBuilder builder = new StringBuilder();

            while ((str = in.readLine()) != null) {
                curCharacters += 2; // line breaks count as 2 characters
                str = str.replace("<build>", "#" + Constants.BUILD);

                String text = ChatColor.translateAlternateColorCodes('&', str);

                if (text.trim().length() > 0)
                    curCharacters += text.length();

                if (curCharacters >= 180) {
                    curCharacters = 0;
                    pages.add(builder.toString());
                    builder = new StringBuilder();
                }

                // APPEND BOOK PAGE ///
                builder.append(text).append("\n");
            }

            if (curCharacters < 180)
                pages.add(builder.toString());

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);       
        BookMeta bm = (BookMeta) book.getItemMeta();
        bm.setDisplayName(Utils.colorCodes("&cPatch Notes"));
        bm.setAuthor("matt11matthew");
        bm.setPages(pages);
        book.setItemMeta(bm);
        book.setAmount(1);
        this.patchBook = book;
	}
	
	public void open(Player player) {
		final ItemStack savedItem = player.getInventory().getItemInMainHand();

        player.getInventory().setItemInMainHand(new ItemStack(getPatchBook()));
        PacketDataSerializer packetdataserializer = new PacketDataSerializer(Unpooled.buffer());

        packetdataserializer.a(EnumHand.MAIN_HAND);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutCustomPayload("MC|BOpen", packetdataserializer));

        player.getInventory().setItemInMainHand(savedItem);
        return;
	}

	@Override
	public void onDisable() {
		print("[PatchMechanics] Disabling...");
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.NORMAL;
	}

	public ItemStack getPatchBook() {
		return patchBook;
	}

	public void setPatchBook(ItemStack patchBook) {
		this.patchBook = patchBook;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		new BukkitRunnable() {
			
			@Override
			public void run() {
				TextComponent comp = new TextComponent(Utils.colorCodes("&7[&c&lPATCH NOTES&7]"));
				TextComponent comp1 = new TextComponent(Utils.colorCodes("&aClick to view "));
		        comp.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/patchnotes"));
				comp1.addExtra(comp);
				player.spigot().sendMessage(comp1);
			}
		}.runTaskLater(Main.getInstance(), 15L);
	}
}
