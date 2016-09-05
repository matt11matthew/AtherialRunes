package me.matt11matthew.atherialrunes.server.shard;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.server.shard.enums.ShardType;

public class Shard {
	
	private String id = "0";
	private boolean isDevShard = true;
	private boolean isBetaShard = false;
	private boolean isLiveShard = false;
	private boolean isSubShard = false;
	private String prefix = "US-";
	private String motd = Constants.MOTD;
	private int maxplayers = 20;
	private String ip;
	private int port;
	private ShardServer shardServer = null;
	
	public Shard(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isSubShard() {
		return isSubShard;
	}
	
	public void setSubShard(boolean isSubShard) {
		this.isSubShard = isSubShard;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public boolean isBetaShard() {
		return isBetaShard;
	}
	
	public void setBetaShard(boolean isBetaShard) {
		this.isBetaShard = isBetaShard;
	}
	
	public boolean isLiveShard() {
		return isLiveShard;
	}
	
	public void setLiveShard(boolean isLiveShard) {
		this.isLiveShard = isLiveShard;
	}
	
	public boolean isDevShard() {
		return isDevShard;
	}
	
	public void setDevShard(boolean isDevShard) {
		this.isDevShard = isDevShard;
	}

	public String getMotd() {
		return motd;
	}

	public void setMotd(String motd) {
		this.motd = motd;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getMaxplayers() {
		return maxplayers;
	}

	public void setMaxplayers(int maxplayers) {
		this.maxplayers = maxplayers;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public ShardType getShardType() {
		if (isLiveShard) {
			return ShardType.LIVE;
		} else if (isDevShard) {
			return ShardType.DEV;
		} else if (isSubShard) {
			return ShardType.SUB;
		} else if (isBetaShard) {
			return ShardType.BETA;
		} else {
			return ShardType.DEV;
		}
		
	}
	
	public ShardServer getShardServer() {
		if (shardServer == null) {
			shardServer = new ShardServer(this);
			return shardServer;
		}
		return shardServer;
	}

	public int getOnlinePlayers() {
		return 0;
	}
	

	
}
