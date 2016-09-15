package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.shard;

import me.matt11matthew.atherialrunes.game.network.bungeecord.BungeeServerTracker;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

@Deprecated
public class Shard {

	private String name;
	private String ip;
	private int port;
	private ServerType type;
	private int maxPlayerCount;
	private String bungeeName;
	
	public Shard(String name, String ip, int port, ServerType type, int maxPlayerCount, String bungeeName) {
		this.name = name;
		this.ip = ip;
		this.port = port;
		this.type = type;
		this.maxPlayerCount = maxPlayerCount;
		this.bungeeName = bungeeName;
	}

	public String getInfo() {
		return name + ", " + ip + ":" + port + " Type: " + type.name;
	}

	public boolean isOnline() {
		boolean online = true;
		try {
			Socket s = new Socket(ip, port);
			s.close();
		} catch (UnknownHostException e) {
			online = false;
		} catch (IOException e) {
			online = false;
		}
		return online;
	}

	public String getName() {
		return this.toString().replaceAll("_", "-");
	}


	public int getOnlinePlayers() {
		return BungeeServerTracker.getPlayersOnline(bungeeName);
	}

	public String getBungeeName() {
		return bungeeName;
	}

	public int getMaxPlayerCount() {
		return maxPlayerCount;
	}

	public ServerType getServerType() {
		return type;
	}
}
