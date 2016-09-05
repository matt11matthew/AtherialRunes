package me.matt11matthew.atherialrunes.server.shard;

public class ShardThread extends Thread {
	
	private ShardServer server;
	
	public ShardThread(ShardServer server) {
		this.server = server;
	}
	
	public void run() {
		if (!server.isConnected()) {
			server.connect();
		}
		
	}
}