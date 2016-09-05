package me.matt11matthew.atherialrunes.game.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.network.bungeecord.BungeeServerInfo;
import me.matt11matthew.atherialrunes.network.bungeecord.BungeeServerTracker;

public class BungeeChannelListener extends ListenerMechanic implements PluginMessageListener {

    static BungeeChannelListener instance = null;

    public static BungeeChannelListener getInstance() {
        if (instance == null) {
            instance = new BungeeChannelListener();
        }
        return instance;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equalsIgnoreCase("BungeeCord")) return;

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
        try {
            String subChannel = in.readUTF();

            try {
                if (subChannel.equals("IP")) {
                    String address = in.readUTF();

                    
                    return;
                }

                if (subChannel.equals("PlayerCount")) {
                    String server = in.readUTF();

                    if (in.available() > 0) {
                        int online = in.readInt();

                        BungeeServerInfo serverInfo = BungeeServerTracker.getOrCreateServerInfo(server);
                        serverInfo.setOnlinePlayers(online);
                    }
                }

            } catch (EOFException e) {
                // Do nothing.
            } catch (IOException e) {
                // This should never happen.
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void onEnable() {
		Main.getInstance().getLogger().info("[BungeeChannelListener] Registering Outbound/Inbound BungeeCord channels...");
        Bukkit.getMessenger().registerOutgoingPluginChannel(Main.getInstance(), "BungeeCord");
        Bukkit.getMessenger().registerIncomingPluginChannel(Main.getInstance(), "BungeeCord", this);

        BungeeServerTracker.startTask(3L);
        Main.getInstance().getLogger().info("[BungeeChannelListener] Finished Registering Outbound/Inbound BungeeCord channels ... OKAY!");
	}

	@Override
	public void onDisable() {
		 Main.getInstance().getLogger().info("[BungeeChannelListener] Unregistering Outbound/Inbound BungeeCord channels...");

	     Bukkit.getMessenger().unregisterIncomingPluginChannel(Main.getInstance(), "BungeeCord");
	     Bukkit.getMessenger().unregisterIncomingPluginChannel(Main.getInstance(), "BungeeCord", this);
		
	}

	@Override
	public LoadPriority getLoadPriority() {
		// TODO Auto-generated method stub
		return null;
	}
}