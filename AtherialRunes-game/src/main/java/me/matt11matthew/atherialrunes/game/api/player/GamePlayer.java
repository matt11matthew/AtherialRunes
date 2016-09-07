package me.matt11matthew.atherialrunes.game.api.player;

import me.matt11matthew.atherialrunes.database.data.player.PlayerData;
import me.matt11matthew.atherialrunes.database.data.player.UUIDData;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.ChatChannel;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.zone.Zone;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import me.matt11matthew.atherialrunes.game.utils.RegionUtils;
import me.matt11matthew.atherialrunes.network.bungeecord.BungeeUtils;
import me.matt11matthew.atherialrunes.player.AtherialPlayer;
import me.matt11matthew.atherialrunes.sound.AtherialSound;
import me.matt11matthew.atherialrunes.utils.BanUtils;
import me.matt11matthew.atherialrunes.utils.MuteUtils;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.HashMap;

public class GamePlayer {
	
	public static HashMap<String, GamePlayer> players = new HashMap<String, GamePlayer>();
	
	private String name;
	private String uuid;
	private Rank rank;
	private int combatTime;
	private ChatChannel chatChannel;
	private boolean vanished;
	private int level;
	private int exp;
	private String expBarMessage;
	private int skillPoints;
	private int gold;
	private int silver;
	private int copper;
	private String nick;
	private boolean adminMode;
	
	public GamePlayer(String name) {
		this.name = name;
		this.uuid = UUIDData.getUUID(name);
	}
	
	public String getName() {
		return name;
	}
	
	public String getUUID() {
		return uuid;
	}
	
	public AtherialPlayer getAtherialPlayer() {
		return (PlayerData.players.containsKey(uuid)) ? PlayerData.players.get(uuid) : new AtherialPlayer(name);
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public ChatChannel getChatChannel() {
		return chatChannel;
	}

	public void setChatChannel(ChatChannel chatChannel) {
		this.chatChannel = chatChannel;
	}

	public void kick(String by, String reason) {
		BungeeUtils.sendNetworkMessage("BungeeCord", "KickPlayer", name, Utils.colorCodes("&c" + reason + " [" + by + "]"));
	}
	
	public void ban(String by, String reason, String rawTime) {
		long time = Utils.convertStringToMillis(rawTime);
		BanUtils.ban(name, reason, by, time);
		try {
			kick(by, reason);
		} catch (Exception e) {
			return;
		}
	}
	
	public void mute(String by, String reason, String rawTime) {
		long time = Utils.convertStringToMillis(rawTime);
		MuteUtils.mute(name, reason, by, time);
	}
	
	public void unmute(String reason) {
		MuteUtils.unmute(name, reason);
	}

	public void unban(String reason) {
		BanUtils.unban(name, reason);		
	}

	public int getCombatTime() {
		return combatTime;
	}

	public void setCombatTime(int combatTime) {
		this.combatTime = combatTime;
	}

	public boolean isInCombat() {
		return (combatTime > 0) ? true : false;
	}
	
	public Player getPlayer() {
		try {
			if ((Bukkit.getPlayerExact(name) != null) && (Bukkit.getPlayerExact(name).isOnline())) {
				Player player = Bukkit.getPlayerExact(name);
				return player;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void msg(MessageType type, String msg) {
		if ((Bukkit.getPlayerExact(name) != null) && (Bukkit.getPlayerExact(name).isOnline())) {
			Player player = Bukkit.getPlayerExact(name);
			type.send(msg, player);
			return;
		}
	}
	
	public Zone getZone() {
		if ((Bukkit.getPlayerExact(name) != null) && (Bukkit.getPlayerExact(name).isOnline())) {
			Player player = Bukkit.getPlayerExact(name);
			return RegionUtils.getZone(player.getLocation());
		}
		return Zone.SAFE;
	}

	public String getDisplayName() {
		return Utils.colorCodes("&f" + name);
	}

	public boolean isVanished() {
		return vanished;
	}

	public void setVanished(boolean vanished) {
		this.vanished = vanished;
	}

	public void setLevel(int level) {
		this.level = level;
		this.exp = 0;
	}
	
	public int getLevel() {
		return level;
	}

	public int getEXP() {
		return exp;
	}

	public void setEXP(int exp) {
		this.exp = exp;
	}

	public void fw() {
		Player player = getPlayer();
		Firework f = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
		
		FireworkMeta fm = f.getFireworkMeta();
		fm.addEffect(FireworkEffect.builder()
				.flicker(false)
				.trail(true)
				.with(Type.BURST)
				.withColor(Color.ORANGE)
				.withFade(Color.WHITE)
				.build());
		fm.setPower(1);
		f.setFireworkMeta(fm);
		
	}

	public void sound(AtherialSound sound) {
		sound.play(getPlayer());
		
	}

	public String getExpBarMessage() {
		return expBarMessage;
	}

	public void setExpBarMessage(String expBarMessage) {
		this.expBarMessage = expBarMessage;
	}

	public int getSkillPoints() {
		return skillPoints;
	}

	public void setSkillPoints(int skillPoints) {
		this.skillPoints = skillPoints;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getCopper() {
		return copper;
	}

	public void setCopper(int copper) {
		this.copper = copper;
	}

	public int getSilver() {
		return silver;
	}

	public void setSilver(int silver) {
		this.silver = silver;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setAdminMode(boolean adminMode) {
		this.adminMode = adminMode;
	}

	public boolean isInAdminMode() {
		return adminMode;
	}

	public boolean isLegit() {
		return (!adminMode);
	}
}
