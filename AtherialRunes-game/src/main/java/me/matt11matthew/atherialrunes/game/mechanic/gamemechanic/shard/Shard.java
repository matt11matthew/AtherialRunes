package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.shard;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

@Deprecated
public enum Shard {

	US_0("us0", "localhost", 25571, ServerType.DEVELOPER, 200);
	
	String name;
	String ip;
	int port;
	ServerType type;
	int maxPlayerCount;
	
	Shard(String name, String ip, int port, ServerType type, int maxPlayerCount) {
		this.name = name;
		this.ip = ip;
		this.port = port;
		this.type = type;
		this.maxPlayerCount = maxPlayerCount;
	}

	public String getInfo() {
		return name + ", " + ip + ":" + port + " Type: " + type.name;
	}

	public boolean isOnline() {
		boolean online = false;
	    try {
	       Socket s = new Socket(ip, port);
	        s.close();
	        online = true;
	     } catch (UnknownHostException e) {
	    	 
	     } catch (IOException e) {
	    	 
	     }
		return online;
	}

	public String getName() {
		return this.toString().replaceAll("_", "-");
	}


}
