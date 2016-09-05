package me.matt11matthew.atherialrunes.game.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.zone.Zone;

public class RegionUtils {

    public static WorldGuardPlugin getWg() {
    	return (WorldGuardPlugin) org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
    }
    
    public static boolean isWorldGuardEnabled() {
    	Plugin p = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
    	return (p.isEnabled()) ? true : false;
    }

    public static ApplicableRegionSet getRegionSet(Location loc) {
        return getWg().getRegionManager(loc.getWorld()).getApplicableRegions(loc);
    }

    @SuppressWarnings("deprecation")
	public static boolean allowsPvP(Location loc) {
        return getRegionSet(loc).allows(DefaultFlag.PVP);
    }

    @SuppressWarnings("deprecation")
	public static boolean allowsMobDamage(Location loc) {
        return getRegionSet(loc).allows(DefaultFlag.MOB_DAMAGE);
    }
    
    public static boolean isSafeZone(Location loc) {
    	return ((!allowsMobDamage(loc)) && (!allowsPvP(loc))) ? true : false;
    }
    
    public static boolean isChaoticZone(Location loc) {
    	return ((allowsMobDamage(loc)) && (allowsPvP(loc))) ? true : false;
    }
    
    public static boolean isWildernessZone(Location loc) {
    	return ((allowsMobDamage(loc)) && (!allowsPvP(loc))) ? true : false;
    }
    
    public static Zone getZone(Location loc) {
    	if (isSafeZone(loc)) {
    		return Zone.SAFE;
    	} else if (isChaoticZone(loc)) {
    		return Zone.WAR;
    	} else if (isWildernessZone(loc)) {
    		return Zone.WILDERNESS;
    	} else {
    		return Zone.WILDERNESS;
    	}
    }
}