package me.matt11matthew.atherialrunes.player;

import me.matt11matthew.atherialrunes.database.data.player.PlayerData;
import me.matt11matthew.atherialrunes.database.data.player.UUIDData;

public class AtherialPlayer {
	
	private String name;
	private String uuid;
	private boolean online;
	private PlayerData playerData;
	private String shard;
	private String rank;
	private int chatChannel;
	private boolean newPlayer;
	private int combatTime;
	private int level;
	private int exp;
	private int skillPoints;
	private boolean vanished;
	private int gold;
	private int copper;
	private int silver;
	private String nick;
	private boolean adminMode;
	
	public String getName() {
		return name;
	}
	
	public String getUUID() {
		return uuid;
	}
	
	public AtherialPlayer(String name) {
		this.name = name;
		this.uuid = UUIDData.getUUID(name);
		this.playerData = new PlayerData(name);
	}
	
	public PlayerData getPlayerData() {
		return playerData;
	}
	
	public boolean isOnline() {
		return online;
	}

	public void kick(String reason) {
		
	}
	
	public void msg(String msg) {
		
	}

	public String getShard() {
		return shard;
	}

	public void setShard(String shard) {
		this.shard = shard;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
	
	public String getRank() {
		return rank;
	}
	
	public void setRank(String rank) {
		this.rank = rank;
	}

	public void setNewPlayer(boolean newPlayer) {
		this.newPlayer = newPlayer;
	}
	
	public boolean isNewPlayer() {
		return newPlayer;
	}

	public int getChatChannel() {
		return chatChannel;
	}

	public void setChatChannel(int chatChannel) {
		this.chatChannel = chatChannel;
	}

	public int getCombatTime() {
		return combatTime;
	}

	public void setCombatTime(int combatTime) {
		this.combatTime = combatTime;
	}

	public boolean isVanished() {
		return vanished;
	}

	public void setVanished(boolean vanished) {
		this.vanished = vanished;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getEXP() {
		return exp;
	}

	public void setEXP(int exp) {
		this.exp = exp;
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

	public boolean isInAdminMode() {
		return adminMode;
	}

	public void setAdminMode(boolean adminMode) {
		this.adminMode = adminMode;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
}
