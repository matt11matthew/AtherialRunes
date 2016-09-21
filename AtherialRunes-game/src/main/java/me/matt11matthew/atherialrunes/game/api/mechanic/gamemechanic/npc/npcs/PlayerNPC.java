package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.npc.npcs;

import com.mojang.authlib.GameProfile;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.npc.NPCMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.npc.enums.NPCType;
import net.minecraft.server.v1_9_R2.EntityPlayer;
import net.minecraft.server.v1_9_R2.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_9_R2.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_9_R2.PlayerInteractManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.CraftServer;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class PlayerNPC implements INPC {

    private String name;
    private boolean visible;
    private Location location;
    private EntityPlayer npcPlayer;
    private UUID uniqueId;
    private String id;

    public PlayerNPC(String name, Location location, UUID uniqueId, String id) {
        this.name = name;
        this.id = id;
        this.location = location;
        this.uniqueId = uniqueId;
        this.visible = true;
        this.npcPlayer = new EntityPlayer(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) location.getWorld()).getHandle(), new GameProfile(uniqueId, name), new PlayerInteractManager(((CraftWorld) location.getWorld()).getHandle()));
    }

    @Override
    public void spawn() {
        Bukkit.getOnlinePlayers().stream().filter(player -> player.getWorld() != null).forEach(player -> {
            CraftPlayer craftPlayer = ((CraftPlayer) player);
            craftPlayer.getHandle().setLocation(location.getX(), location.getZ(), location.getY(), location.getYaw(), location.getPitch());
            craftPlayer.getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npcPlayer));
            craftPlayer.getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(npcPlayer));
            PacketPlayOutPlayerInfo packet3 = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npcPlayer);
            new BukkitRunnable() {
                @Override
                public void run() {
                    craftPlayer.getHandle().playerConnection.sendPacket(packet3);
                }
            }.runTaskLater(Main.getInstance(), 10L);

        });
        NPCMechanics.npcs.put(id, this);
        update();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void despawn() {
        Bukkit.getOnlinePlayers().stream().filter(player -> player.getWorld() != null).forEach(player -> {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npcPlayer));
        });
        NPCMechanics.npcs.remove(id);
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public NPCType getNPCType() {
        return NPCType.PLAYER;
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public void save() {
        try {
            boolean newNPC = false;
            File file = new File(Main.getInstance().getDataFolder() + "/playernpcs/");
            if (!file.exists()) {
                file.mkdirs();
            }
            File npcFile = new File(Main.getInstance().getDataFolder() + "/playernpcs/", id + ".playernpc");
            if (!npcFile.exists()) {
                npcFile.createNewFile();
                newNPC = true;
            }
            FileWriter fileWriter = new FileWriter(file);
            if (newNPC) {
                fileWriter.write("uuid=" + uniqueId.toString() + System.lineSeparator());
                fileWriter.write("name=" + name + System.lineSeparator());
                fileWriter.write("location.x=" + (int) location.getX() + System.lineSeparator());
                fileWriter.write("location.y=" + (int) location.getY() + System.lineSeparator());
                fileWriter.write("location.z=" + (int) location.getZ() + System.lineSeparator());
                fileWriter.write("location.world=" + location.getWorld().getName() + System.lineSeparator());
                fileWriter.write("id=" + id + System.lineSeparator());
                fileWriter.close();
                return;
            } else {
                npcFile.delete();
                save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EntityPlayer getNpcPlayer() {
        return npcPlayer;
    }

    public void update() {
        npcPlayer.setInvisible((visible) ? false : true);
    }

    public void delete() {
        File npcFile = new File(Main.getInstance().getDataFolder() + "/playernpcs/", id + ".playernpc");
        if (npcFile.exists()) {
            npcFile.delete();
        }
    }
}
