package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.player;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.database.DatabaseAPI;
import me.matt11matthew.atherialrunes.database.data.player.Ban;
import me.matt11matthew.atherialrunes.database.data.player.LocalData;
import me.matt11matthew.atherialrunes.database.data.player.Mute;
import me.matt11matthew.atherialrunes.database.data.player.PlayerData;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.enums.Unicodes;
import me.matt11matthew.atherialrunes.game.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.rank.ChatChannel;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.rank.events.AtherialGlobalChatEvent;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.rank.events.AtherialLocalChatEvent;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.rank.events.AtherialTradeChatEvent;
import me.matt11matthew.atherialrunes.game.player.GamePlayer;
import me.matt11matthew.atherialrunes.player.AtherialPlayer;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class PlayerMechanics extends ListenerMechanic {

	@Override
	public void onEnable() {
		print("[PlayerMechanics] Enabling...");
		registerListeners();
	}

	@Override
	public void onDisable() {
		print("[PlayerMechanics] Disabling...");
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.MONITOR;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPreLogin(AsyncPlayerPreLoginEvent e) {
		DatabaseAPI.getInstance().getUUIDData().update(e.getUniqueId().toString(), e.getName());
		DatabaseAPI.getInstance().getPlayerData(e.getName()).update();
		AtherialPlayer p = PlayerData.getAtherialPlayer(e.getName());
		GamePlayer gp = new GamePlayer(p.getName());
		gp.setRank(Rank.valueOf(p.getRank().toUpperCase()));
		gp.setChatChannel(ChatChannel.getChatChannelFromId(p.getChatChannel()));
		gp.setCombatTime(p.getCombatTime());
		gp.setEXP(p.getEXP());
		gp.setLevel(p.getLevel());
		gp.setVanished(gp.isVanished());
		gp.setSkillPoints(p.getSkillPoints());
		gp.setGold(p.getGold());
		gp.setSilver(p.getSilver());
		gp.setCopper(p.getCopper());
		GamePlayer.players.put(e.getUniqueId().toString(), gp);
		if (Rank.isStaff(gp.getName())) {
			Main.staff.add(gp.getName());
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		new LocalData().update(player);
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Mute mute = Mute.getMute(player.getName());
				if (mute.isMuted()) {
					String time = Utils.parseMilis(mute.getTime());
					String reason = mute.getReason();
					player.sendMessage(Utils.colorCodes("&cYour currently &lMUTED"));
					player.sendMessage(Utils.colorCodes("&f&lTime: &c" + time));
					player.sendMessage(Utils.colorCodes("&f&lReason: &c" + reason));
					return;
				}
				
			}
		}.runTaskLater(Main.getInstance(), 15L);
	}
	
	@EventHandler
	public void onChat(AtherialGlobalChatEvent e) {
		Mute mute = Mute.getMute(e.getGamePlayer().getName());
		Player player = Bukkit.getPlayerExact(e.getGamePlayer().getName());
		if (mute.isMuted()) {
			String time = Utils.parseMilis(mute.getTime());
			String reason = mute.getReason();
			player.sendMessage(Utils.colorCodes("&cYour currently &lMUTED"));
			player.sendMessage(Utils.colorCodes("&f&lTime: &c" + time));
			player.sendMessage(Utils.colorCodes("&f&lReason: &c" + reason));
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onChat(AtherialTradeChatEvent e) {
		Mute mute = Mute.getMute(e.getGamePlayer().getName());
		Player player = Bukkit.getPlayerExact(e.getGamePlayer().getName());
		if (mute.isMuted()) {
			String time = Utils.parseMilis(mute.getTime());
			String reason = mute.getReason();
			player.sendMessage(Utils.colorCodes("&cYour currently &lMUTED"));
			player.sendMessage(Utils.colorCodes("&f&lTime: &c" + time));
			player.sendMessage(Utils.colorCodes("&f&lReason: &c" + reason));
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onChat(AtherialLocalChatEvent e) {
		Mute mute = Mute.getMute(e.getGamePlayer().getName());
		Player player = Bukkit.getPlayerExact(e.getGamePlayer().getName());
		if (mute.isMuted()) {
			String time = Utils.parseMilis(mute.getTime());
			String reason = mute.getReason();
			player.sendMessage(Utils.colorCodes("&cYour currently &lMUTED"));
			player.sendMessage(Utils.colorCodes("&f&lTime: &c" + time));
			player.sendMessage(Utils.colorCodes("&f&lReason: &c" + reason));
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onLogin(PlayerLoginEvent e) {
		Player player = e.getPlayer();
		Ban ban = Ban.getBan(player.getName());
		if (ban.isBanned()) {
			if (ban.canUnban()) {
				ban.setUnbanReason("Time");
				ban.setTime(0);
				Ban.save(ban);
				return;
			} else {
				e.disallow(Result.KICK_BANNED, Utils.colorCodes(ban.getReason()));
				Ban.save(ban);
				return;
			}
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		GamePlayer gp = Main.getGamePlayer(player.getName());
		AtherialPlayer ap = PlayerData.getAtherialPlayer(player.getName());
		ap.setRank(gp.getRank().toString());
		ap.setChatChannel(gp.getChatChannel().getId());
		ap.setShard("1");
		ap.setCombatTime(gp.getCombatTime());
		ap.setEXP(gp.getEXP());
		ap.setSkillPoints(gp.getSkillPoints());
		ap.setLevel(gp.getLevel());
		ap.setVanished(gp.isVanished());
		ap.setGold(gp.getGold());
		ap.setSilver(gp.getSilver());
		ap.setCopper(gp.getCopper());
		if (gp.isInCombat()) {
			Location loc = player.getLocation();
			Zombie zombie = loc.getWorld().spawn(loc, Zombie.class);
			zombie.setAI(false);
			zombie.setBaby(false);
			zombie.setCanPickupItems(false);
			zombie.setCollidable(false);
			zombie.setCustomName(gp.getDisplayName());
			zombie.setCustomNameVisible(true);
			Location head = zombie.getEyeLocation();
			List<String> holo = new ArrayList<>();
			holo.set(1, Utils.colorCodes((int) zombie.getHealth() + " &c" + Unicodes.COMMON_HEART.get()));

		}
		
		DatabaseAPI.getInstance().getPlayerData(player.getName()).save(player.getUniqueId().toString());
		DatabaseAPI.getInstance().getUUIDData().save(player);
		
		GamePlayer.players.remove(player.getUniqueId().toString());
		if (Main.staff.contains(player.getName())) {
			Main.staff.remove(player.getName());
		}
		new LocalData().save(player);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		e.setCancelled(Constants.DISABLE);
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		e.setCancelled(Constants.DISABLE);
	}
}
