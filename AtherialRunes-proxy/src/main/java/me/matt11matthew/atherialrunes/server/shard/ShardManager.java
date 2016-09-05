package me.matt11matthew.atherialrunes.server.shard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.server.Main;

public class ShardManager {
	
	public static HashMap<String, Shard> shards = new HashMap<String, Shard>();
	
	public static void setupShards() {
		File file = new File("severconfig.server");
		if (!file.exists()) {
			try {
				Main.print("Server config does not exist, creating...");
				file.createNewFile();
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write("ID:0,IP:localhost,Port:25700,Prefix:US-" + System.lineSeparator());
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				Main.print("Could not setup shards");
				
			}
		}
		Main.print("Loading shards...");
		String shardFileText = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			shardFileText = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (String servers : shardFileText.split("\n")) {
			if (servers != null) {
				String id = servers.split("ID:")[1].split(",")[0].trim();
				String ip = servers.split("IP:")[1].split(",")[0].trim();
				String port = servers.split("Port:")[1].split(",")[0].trim();
				String prefix = servers.split("Prefix:")[1].trim();
				String ss = getShardFile(prefix + id);
				Main.print(ss);
				Shard shard = new Shard(id);
				shard.setId(id);
				shard.setIp(ip);
				shard.setPort(Integer.parseInt(port));
				shard.setPrefix(prefix);
				String[] s = ss.split("\n");
				for (int i = 0; i < s.length; i++) {
					if (s[i].contains("beta: ")) {
						shard.setBetaShard(Boolean.parseBoolean(s[i].split("beta: ")[1].trim()));
					}
					if (s[i].contains("sub: ")) {
						shard.setSubShard(Boolean.parseBoolean(s[i].split("sub: ")[1].trim()));
					}
					if (s[i].contains("live: ")) {
						shard.setLiveShard(Boolean.parseBoolean(s[i].split("live: ")[1].trim()));
					}
					if (s[i].contains("dev: ")) {
						shard.setDevShard(Boolean.parseBoolean(s[i].split("dev: ")[1].trim()));
					}
					if (s[i].contains("maxplayers: ")) {
						shard.setMaxplayers(Integer.parseInt(s[i].split("maxplayers: ")[1].trim()));
					}
					if (s[i].contains("id: ")) {
						shard.setId(s[i].split("id: ")[1].trim());
					}
					if (s[i].contains("prefix: ")) {
						shard.setPrefix(s[i].split("prefix: ")[1].trim());
					}
				}
				shard.setMotd(Constants.MOTD);
				shards.put(shard.getId(), shard);
				shards.values().stream().forEach(shard_shard -> { 
					Main.print("***" + shard_shard.getPrefix() + shard_shard.getId() + "\n" + shard_shard.getIp() + ":" + shard_shard.getPort() + "***\n");
				});
			}
		}//0,(localhost,25566)_US-
	}
	
	public static String getShardFile(String shard) {
		File file = new File("servers/" + shard + "/");
		String text = null;
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File("servers/" + shard + "/", "shardconfig.shard");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			text = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return text;
	}
	static int i = 0;
	
	public static void start(Shard shard) {
		ShardServer server = new ShardServer(shard);
		server.connect();
	}
	
	public static void connectShards() {
		shards.values().forEach(shard -> {
			ShardThread shardThread = new ShardThread(shard.getShardServer());
			shardThread.start();
		});
	}
	
	public static void endShards() {
		shards.values().forEach(shard -> {
			shard.getShardServer().disconnect();
		});
	}
}

