package me.matt11matthew.atherialrunes.game.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.matt11matthew.atherialrunes.utils.Utils;

@Deprecated
public class BungeeUtilsOld implements PluginMessageListener {

    private static JavaPlugin plugin;
    
    static BungeeUtilsOld instance = null;
    
    public static BungeeUtilsOld getInstance() {
    	if (instance == null) {
    		instance = new BungeeUtilsOld();
    	}
    	return instance;
    }
    
    public static void setPlugin(JavaPlugin jplugin) {
    	plugin = jplugin;
    }
    
    public static JavaPlugin getPlugin() {
    	return plugin;
    }
    
    public static HashMap<String, String[]> playerlist = new HashMap<String, String[]>();
    public static HashMap<String, Integer> playercount = new HashMap<String, Integer>();
    public static List<String> servers = new ArrayList<String>();
    
    public static void askPlayerCount(String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("PlayerCount");
            out.writeUTF(server);
        } catch (IOException e) {
            System.out.println("I/O Exception while asking for player count on server '" + server + "'.");
        }

        // OR, if you don't need to send it to a specific player

        if (Bukkit.getOnlinePlayers().size() > 0)
            ((Player) Bukkit.getOnlinePlayers().toArray()[0]).sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }
    
    public static void askServerList() {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("GetServers");
        } catch (IOException e) {
            System.out.println("I/O Exception while asking for server list.");
        }
    }
    
    public static void askPlayerList(String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("PlayerList");
            out.writeUTF(server);
        } catch (IOException e) {
            System.out.println("I/O Exception while asking for player list.");
        }
    }
    
    public static void task() {
    	AtherialRunnable.getInstance().runRepeatingTask(new Runnable() {
			
			@Override
			public void run() {
				askServerList();
				for (String server : servers) {
					askPlayerCount(server);
					askPlayerList(server);
				}
			}
		}, 10L, 10L);
    }
    
    public static void load() {
    	Bukkit.getServer().getMessenger().registerIncomingPluginChannel(getPlugin(), "BungeeCord", BungeeUtilsOld.getInstance());
    	task();
    }

    @Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) return;
		
		try {
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
			String command = in.readUTF();
			
			if (command.equals("GetServers")) {
				String[] serverList = in.readUTF().split(", ");
				for (String server : serverList) {
					if (!servers.contains(server)) {
						servers.add(server);
					} else {
						servers.remove(server);
						servers.add(server);
					}
				}
			}
			if (command.equals("PlayerList")) {
				String server = in.readUTF();
				String[] playerList = in.readUTF().split(", ");
				if (!playerlist.containsKey(server)) {
					playerlist.put(server, playerList);
				} else {
					playerlist.remove(server);
					playerlist.put(server, playerList);
				}
			}
			if (command.equals("PlayerCount")) {
				String server = in.readUTF();
				int playerCount = in.readInt();
				if (!playercount.containsKey(server)) {
					playercount.put(server, playerCount);
				} else {
					playercount.remove(server);
					playercount.put(server, playerCount);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public static void sendToServer(String playerName, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(playerName);
        out.writeUTF(serverName);
        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);

        if (player != null)
            player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    /**
     * @param channel  Type of custom Channel (actually sub)
     * @param message  Message to send.
     * @param contents Contents of the internal guts.
     * @since 1.0
     */

    public static void sendNetworkMessage(String channel, String subChannel, String message, String... contents) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(subChannel);
        out.writeUTF(message);

        for (String s : contents)
            out.writeUTF(s);

        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);

        if (player != null)
            player.sendPluginMessage(plugin, channel, out.toByteArray());
    }

    /**
     * Send a player a message through the Bungee channel.
     *
     * @param playerName Player to send message to.
     * @param message    Message to send to the player specified above.
     * @apiNote Make sure to use ChatColor net.md_5.bungee.api.ChatColor!
     * @since 1.0
     */
    public static void sendPlayerMessage(String playerName, String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Message");
        out.writeUTF(playerName);
        out.writeUTF(message);
        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        if (player != null)
            player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

	public static int getPlayerCount(String name) {
		if (playercount.containsKey(name)) {
			return playercount.get(name);
		}
		return 0;
	}
	
	public static List<String> getPlayerList(String server) {
		List<String> players = new ArrayList<String>();
		for (String player : playerlist.get(server)) {
			players.add(player);
		}
		return players;
	}
	
	public static List<String> getAllPlayers() {
		List<String> players = new ArrayList<String>();
		for (String server : servers) {
			for (String player : playerlist.get(server)) {
				players.add(player);
			}
		}
		return players;
	}
	
	public static void broadcastToAllPlayers(String msg) {
		for (String player : getAllPlayers()) {
			sendPlayerMessage(player, Utils.colorCodes(msg));
		}
	}
}