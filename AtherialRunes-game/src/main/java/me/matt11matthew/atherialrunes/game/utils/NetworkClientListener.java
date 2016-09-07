package me.matt11matthew.atherialrunes.game.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.esotericsoftware.kryonet.Connection;

import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.network.packet.BasicMessagePacket;

public class NetworkClientListener extends ListenerMechanic {

    static NetworkClientListener instance = null;

    public static NetworkClientListener getInstance() {
        if (instance == null) {
            instance = new NetworkClientListener();
        }
        return instance;
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof BasicMessagePacket) {
            BasicMessagePacket packet = (BasicMessagePacket) object;

            byte[] data = packet.data;
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(data));

            try {
                String task = in.readUTF();
                if (task.equals("StaffMessage")) {
                    String msg = ChatColor.translateAlternateColorCodes('&', in.readUTF());
                    Bukkit.getOnlinePlayers().forEach(p -> {
                        if (Rank.isStaff(p.getName())) p.sendMessage(msg);
                    });
                }
                if (task.equals("Reboot")) {
                    Main.stop();
                }
                if (task.equals("Sayall")) {
                	String msg = ChatColor.translateAlternateColorCodes('&', in.readUTF());
                	Bukkit.getOnlinePlayers().forEach(player -> {
                		player.sendMessage(msg);
                	});
                }
                Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
                    try {
                        
                    	if (task.equals("Broadcast")) {
                            String message = ChatColor.translateAlternateColorCodes('&', in.readUTF());
                            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(message));
                        }
                    
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public void onEnable() {
		if (Main.getClient() == null) return;

		Main.getInstance().getLogger().info("[NetworkClientListener] Registering client packet listener...");
        Main.getClient().registerListener(this);
	}

	@Override
	public void onDisable() {
		if (Main.getClient() == null) return;

        Main.getInstance().getLogger().info("[NetworkClientListener] Unregistering client packet listener...");
        Main.getClient().removeListener(this);
		
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.HIGH;
	}



}