package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.rank;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.rank.events.AtherialGlobalChatEvent;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.rank.events.AtherialLocalChatEvent;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.rank.events.AtherialTradeChatEvent;
import me.matt11matthew.atherialrunes.game.player.GamePlayer;
import me.matt11matthew.atherialrunes.utils.Utils;

public class RankMechanics extends ListenerMechanic {

	public static List<String> bannedwords = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		print("[RankMechanics] Enabling...");
		registerListeners();
	}

	@Override
	public void onDisable() {
		print("[RankMechanics] Disabling...");
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.NORMAL;
	}
	
	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		e.setCancelled(true);
		GamePlayer gp = Main.getGamePlayer(player.getName());
		switch (gp.getChatChannel()) {
			case LOCAL:
				AtherialLocalChatEvent atherialLocalChatEvent = new AtherialLocalChatEvent(Main.getGamePlayer(player.getName()), ChatColor.stripColor(e.getMessage()));
				Bukkit.getServer().getPluginManager().callEvent(atherialLocalChatEvent);
				if (!atherialLocalChatEvent.isCancelled()) {
					String msg = atherialLocalChatEvent.getGamePlayer().getRank().getChatMessage(player.getName(), ChatColor.stripColor(e.getMessage()));
					sendMessageToLocalPlayers(msg, player);
				}
				break;
			case TRADE:
				AtherialTradeChatEvent atherialTradeChatEvent = new AtherialTradeChatEvent(Main.getGamePlayer(player.getName()), ChatColor.stripColor(e.getMessage()));
				Bukkit.getServer().getPluginManager().callEvent(atherialTradeChatEvent);
				if (!atherialTradeChatEvent.isCancelled()) {
					String msg = atherialTradeChatEvent.getGamePlayer().getRank().getChatMessage(player.getName(), ChatColor.stripColor(e.getMessage()));
					Bukkit.getServer().broadcastMessage(Utils.colorCodes(gp.getChatChannel().getPrefix() + "&r " + msg));
				}
				break;
			case GLOBAL:
				AtherialGlobalChatEvent atherialGlobalChatEvent = new AtherialGlobalChatEvent(Main.getGamePlayer(player.getName()), ChatColor.stripColor(e.getMessage()));
				Bukkit.getServer().getPluginManager().callEvent(atherialGlobalChatEvent);
				if (!atherialGlobalChatEvent.isCancelled()) {
					String msg = atherialGlobalChatEvent.getGamePlayer().getRank().getChatMessage(player.getName(), ChatColor.stripColor(e.getMessage()));
					Bukkit.getServer().broadcastMessage(Utils.colorCodes(gp.getChatChannel().getPrefix() + "&r " + msg));
				}
				break;
			default:
				AtherialLocalChatEvent atherialLocalChatEvent1 = new AtherialLocalChatEvent(Main.getGamePlayer(player.getName()), ChatColor.stripColor(e.getMessage()));
				Bukkit.getServer().getPluginManager().callEvent(atherialLocalChatEvent1);
				if (!atherialLocalChatEvent1.isCancelled()) {
					String msg = atherialLocalChatEvent1.getGamePlayer().getRank().getChatMessage(player.getName(), ChatColor.stripColor(e.getMessage()));
					sendMessageToLocalPlayers(msg, player);
				}
				break;
		}
	}

	public static String fixCaps(String s) {
		String return_string = "";
		if (s.contains(" ")) {
			for (String ss : s.split(" ")) {
				String s1 = wordToLowerCase(ss);
				return_string = return_string + " " + s1;
			}
		} else {
			String s1 = wordToLowerCase(s);
			return_string = return_string + " " + s1;
		}
		return return_string.trim();
	}
	
	public static String filter(String s) {
		String return_string = "";
		if (s.contains(" ")) {
			for (String ss : s.split(" ")) {
				String s1 = replaceBannedWords(ss);
				return_string = return_string + " " + s1;
			}
		} else {
			String s1 = replaceBannedWords(s);
			return_string = return_string + " " + s1;
		}
		return return_string.trim();
	}
	
	public static String generateDots(String bannedword) {
		String return_string = "";
		for (int i = 0; i < bannedword.length(); i++) {
			return_string = return_string + "*";
		}
		return return_string.trim();
	}
	
	public static String replaceBannedWords(String s) {
		for (String ss : bannedwords) {
			if (s.toLowerCase().contains(ss.toLowerCase())) {
				s = s.toLowerCase();	
				s = s.replaceAll(ss, generateDots(ss));
				return s.trim();
			}
		}
		return s;
	
	}
	
	public static void sendMessageToLocalPlayers(String msg, Player sender) {
		List<Player> to_send = new ArrayList<Player>();
        for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
            if (pl.getName().equalsIgnoreCase(sender.getName())) {
                continue;
            }
            if (!pl.getWorld().getName().equalsIgnoreCase(pl.getWorld().getName())) {
                continue;
            }
            if (pl.getLocation().distanceSquared(sender.getLocation()) > 16384) {
                continue;
            }
            to_send.add(pl);
        }
        if (to_send.size() <= 0) {
            sender.sendMessage(Utils.colorCodes(msg));
            sender.sendMessage(Utils.colorCodes("&7&oNo one is nearby!"));
            return;
        }
        for (Player pl : to_send) {
            pl.sendMessage(Utils.colorCodes(msg));
        }
        sender.sendMessage(Utils.colorCodes(msg));
        return;
	}
 	
	public static String wordToLowerCase(String s) {
		if (s.substring(0, 1).startsWith(s.substring(0, 1).toUpperCase())) {
			s = s.toUpperCase();
			String l = s.substring(1, s.length());
			l = l.toLowerCase().trim();
			String ll = s.substring(0, 1);
			return ll + l;
		}
		return s;
	}
	
	@EventHandler
	public void onTabComplete(PlayerChatTabCompleteEvent e) {
		Player player = e.getPlayer();
		GamePlayer gamePlayer = Main.getGamePlayer(player.getName());
		ChatChannel channel = gamePlayer.getChatChannel();
		e.getTabCompletions().clear();
		switch (channel) {
			case LOCAL:
				channel = ChatChannel.GLOBAL;
				break;
			case GLOBAL:
				channel = ChatChannel.TRADE;
				break;
			case TRADE:
				channel = ChatChannel.LOCAL;
				break;
			default:
				channel = ChatChannel.LOCAL;
				break;
		}
		gamePlayer.setChatChannel(channel);
		player.sendMessage(Utils.colorCodes("&3You are now speaking in " + channel.getPrefix()));
	}
}
