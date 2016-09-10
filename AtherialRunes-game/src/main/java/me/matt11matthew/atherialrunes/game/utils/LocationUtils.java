package me.matt11matthew.atherialrunes.game.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.List;

public class LocationUtils {

    static LocationUtils instance = null;

    public static LocationUtils getInstance() {
        if (instance == null) {
            instance = new LocationUtils();
        }
        return instance;
    }

    public List<Block> getCube(Location loc, Integer radius) {
        List<Block> blocks = new ArrayList<>();
        for (int x = (radius * -1) - 1; x <= (radius + 1); x++) {
            for (int y = (radius * -1); y <= radius; y++) {
                for (int z = (radius * -1) - 1; z <= (radius + 1); z++) {
                    Block b = loc.getWorld().getBlockAt(loc.getBlockX() + x, loc.getBlockY() + y, loc.getBlockZ() + z);

                    if (!b.getType().equals(Material.AIR) && (b != null)) {
                        blocks.add(b);
                    }
                }
            }
        }

        return blocks;
    }

    private List<Block> getBlocks(Location base, int changeX, int changeY, int changeZ) {
        List<Block> blocks = new ArrayList<>();
        for (int x = (base.getBlockX() - changeX); x <= (base.getBlockX() + changeX); x++) {
            for (int y = (base.getBlockY() - changeY); y <= (base.getBlockY() + changeY); y++) {
                for (int z = (base.getBlockZ() - changeZ); z <= (base.getBlockZ() + changeZ); z++) {
                    Location loc = new Location(base.getWorld(), x, y, z);
                    Block b = loc.getBlock();
                    if (!b.getType().equals(Material.AIR) && (b != null)) {
                        blocks.add(b);
                    }
                }
            }
        }

        return blocks;
    }

    public List<Block> getSquare(Block b, BlockFace face) {
        List<Block> blocks = new ArrayList<>();
        switch (face) {
            case DOWN:
                blocks.addAll(getBlocks(b.getLocation(), 1, 0, 1));
                break;
            case EAST:
                blocks.addAll(getBlocks(b.getLocation(), 0, 1, 1));
                break;
            case NORTH:
                blocks.addAll(getBlocks(b.getLocation(), 1, 1, 0));
                break;
            case SOUTH:
                blocks.addAll(getBlocks(b.getLocation(), 1, 1, 0));
                break;
            case UP:
                blocks.addAll(getBlocks(b.getLocation(), 1, 0, 1));
                break;
            case WEST:
                blocks.addAll(getBlocks(b.getLocation(), 0, 1, 1));
                break;
            default:
                break;
        }

        return blocks;
    }

    public List<Block> getSquare(Block b, BlockFace face, int i) {
        List<Block> blocks = new ArrayList<>();
        switch (face) {
            case DOWN:
                blocks.addAll(getBlocks(b.getLocation(), i, 0, i));//1
                break;
            case EAST:
                blocks.addAll(getBlocks(b.getLocation(), 0, i, i));
                break;
            case NORTH:
                blocks.addAll(getBlocks(b.getLocation(), i, i, 0));
                break;
            case SOUTH:
                blocks.addAll(getBlocks(b.getLocation(), i, i, 0));
                break;
            case UP:
                blocks.addAll(getBlocks(b.getLocation(), i, 0, i));
                break;
            case WEST:
                blocks.addAll(getBlocks(b.getLocation(), 0, i, i));
                break;
            default:
                break;
        }

        return blocks;
    }
}
