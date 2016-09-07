package me.matt11matthew.atherialrunes.reflection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public enum Version {

	V1_8(47),
	V1_9_1(108),
	V1_9_2(109),
	V1_9_3(110),
	V1_9_4(110),
	V1_10(210);
	
	int protocolVersion;

	/**
	 *
	 * @param protocolVersion contructer
     */
	Version(int protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	/**
	 *
	 * @return the version
     */
	public int getProtocolVersion() {
		return protocolVersion;
	}

	/**
	 *
	 * @param player the player
	 * @return the version
     */
	public static int getProtocolVersion(Player player) {
		try {
			String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
			Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
			Class<?> entityPlayerClass = Class.forName("net.minecraft.server." + version + ".EntityPlayer");
			Class<?> playerConnectionClass = Class.forName("net.minecraft.server." + version + ".PlayerConnection");
			Class<?> networkManagerClass = Class.forName("net.minecraft.server." + version + ".NetworkManager");

			Object entityPlayer;
			{
				Method method = craftPlayerClass.getDeclaredMethod("getHandle");
				method.setAccessible(true);
				entityPlayer = method.invoke(player);
			}

			Object playerConnection;
			{
				Field field = entityPlayerClass.getDeclaredField("playerConnection");
				field.setAccessible(true);
				playerConnection = field.get(entityPlayer);
			}

			Object networkManager;
			{
				Field field = playerConnectionClass.getDeclaredField("networkManager");
				field.setAccessible(true);
				networkManager = field.get(playerConnection);
			}

			int v = 0;
			{
				Method method = networkManagerClass.getDeclaredMethod("getVersion");
				method.setAccessible(true);
				v = (int) method.invoke(networkManager);
			}
			return v;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 *
	 * @param player the player
	 * @return the players ping
     */
	public static int getPing(Player player) {
		try {
			String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
			Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
			Class<?> entityPlayerClass = Class.forName("net.minecraft.server." + version + ".EntityPlayer");
			Class<?> playerConnectionClass = Class.forName("net.minecraft.server." + version + ".PlayerConnection");
			Class<?> networkManagerClass = Class.forName("net.minecraft.server." + version + ".NetworkManager");

			Object entityPlayer;
			{
				Method method = craftPlayerClass.getDeclaredMethod("getHandle");
				method.setAccessible(true);
				entityPlayer = method.invoke(player);
			}

			Object playerConnection;
			{
				Field field = entityPlayerClass.getDeclaredField("playerConnection");
				field.setAccessible(true);
				playerConnection = field.get(entityPlayer);
			}

			Object networkManager;
			{
				Field field = playerConnectionClass.getDeclaredField("networkManager");
				field.setAccessible(true);
				networkManager = field.get(playerConnection);
			}

			int ping = 0;
			{
				Method method = networkManagerClass.getDeclaredMethod("getPing");
				method.setAccessible(true);
				ping = (int) method.invoke(networkManager);
			}
			return ping;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean is1_10(Player player) {
		return (getProtocolVersion(player) == 210);
	}
	
	public static boolean is1_9_4(Player player) {
		return (getProtocolVersion(player) == 110);
	}
	
	public static boolean is1_9_3(Player player) {
		return (getProtocolVersion(player) == 110);
	}
	
	public static boolean is1_9_2(Player player) {
		return (getProtocolVersion(player) == 109);
	}
	
	public static boolean is1_9_1(Player player) {
		return (getProtocolVersion(player) == 108);
	}
	
	public static boolean is1_9_0(Player player) {
		return (getProtocolVersion(player) == 107);
	}
	
	public static boolean is1_9(Player player) {
		return ((is1_9_0(player)) || (is1_9_1(player)) || (is1_9_2(player)) || (is1_9_3(player)) || (is1_9_4(player)));
	}
	
	public static boolean is1_8(Player player) {
		return (getProtocolVersion(player) == 47);
	}
}
