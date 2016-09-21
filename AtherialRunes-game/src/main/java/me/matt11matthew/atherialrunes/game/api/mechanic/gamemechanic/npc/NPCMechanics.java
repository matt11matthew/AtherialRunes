package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.npc;

import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.npc.npcs.PlayerNPC;
import me.matt11matthew.atherialrunes.game.utils.UUIDUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class NPCMechanics extends ListenerMechanic {

	public static HashMap<String, PlayerNPC> npcs = new HashMap<>();

	@Override
	public void onEnable() {
		print("-----------------------------------------");
		print("[NPCMechanics] Enabling...");
		print("-----------------------------------------");
		registerListeners();
		loadPlayerNPCS();
	}

	@Override
	public void onDisable() {
		print("-----------------------------------------");
		print("[NPCMechanics] Disabling...");
		print("-----------------------------------------");
		savePlayerNPCS();
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.NORMAL;
	}

	public static void createPlayerNPC(Location location, String name, String id) {
		PlayerNPC playerNPC = new PlayerNPC(name, location, UUIDUtils.generateRandomUUID(), id);
		playerNPC.spawn();
	}

	public static void removePlayerNPC(String id) {
		PlayerNPC playerNPC = npcs.get(id);
		playerNPC.delete();
		playerNPC.despawn();
	}

	public void loadPlayerNPCS() {
		try {
			List<File> playerNPCFiles = Arrays.asList(new File(Main.getInstance().getDataFolder() + "/playernpcs/").listFiles());
			playerNPCFiles.forEach(npcFile -> {
				String npcFileText = null;
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(npcFile));
					StringBuilder sb = new StringBuilder();
					String line = br.readLine();
					while (line != null) {
						sb.append(line);
						sb.append(System.lineSeparator());
						line = br.readLine();
					}
					npcFileText = sb.toString();
				} catch (Exception e) {
					return;
				} finally {
					try {
						br.close();
					} catch (IOException e) {
						return;
					}
				}
				String[] npcText = npcFileText.split("\n");
				UUID uuid = null;
				String name = null;
				int x = 0;
				int y = 0;
				int z = 0;
				String id = null;
				String worldName = null;
				for (int i = 0; i < npcText.length; i++) {
					if (npcText[i].contains("uuid=")) {
						uuid = UUID.fromString(npcText[i].split("uuid=")[1].trim());
					}
					if (npcText[i].contains("name=")) {
						name = npcText[i].split("name=")[1].trim();
					}
					if (npcText[i].contains("location.x=")) {
						x = Integer.parseInt(npcText[i].split("location.x=")[1].trim());;
					}
					if (npcText[i].contains("location.y=")) {
						y = Integer.parseInt(npcText[i].split("location.y=")[1].trim());;
					}
					if (npcText[i].contains("location.z=")) {
						z = Integer.parseInt(npcText[i].split("location.z=")[1].trim());;
					}
					if (npcText[i].contains("location.world=")) {
						worldName = npcText[i].split("location.world=")[1].trim();
					}
					if (npcText[i].contains("id=")) {
						id = npcText[i].split("id=")[1].trim();
					}
				}
				Location location = new Location(Bukkit.getWorld(worldName), x, y, z);
				PlayerNPC npc = new PlayerNPC(name, location, uuid, id);
				npc.spawn();
			});
		} catch (Exception e) {
			return;
		}
	}

	public void savePlayerNPCS() {
		npcs.values().forEach(npc -> {
			npc.save();
			npc.despawn();
		});
		npcs.clear();
	}
}