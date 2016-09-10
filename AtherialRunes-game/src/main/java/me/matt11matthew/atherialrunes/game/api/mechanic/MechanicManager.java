package me.matt11matthew.atherialrunes.game.api.mechanic;

import java.util.HashMap;

public class MechanicManager {

	public static HashMap<String, Mechanic> mechanics = new HashMap<String, Mechanic>();

	public static void loadMechanics() {
		mechanics.values().stream().filter(mechanic -> mechanic.getLoadPriority() == LoadPriority.MONITOR).forEach(Mechanic::enable);
		mechanics.values().stream().filter(mechanic -> mechanic.getLoadPriority() == LoadPriority.HIGHEST).forEach(Mechanic::enable);
		mechanics.values().stream().filter(mechanic -> mechanic.getLoadPriority() == LoadPriority.HIGH).forEach(Mechanic::enable);
		mechanics.values().stream().filter(mechanic -> mechanic.getLoadPriority() == LoadPriority.NORMAL).forEach(Mechanic::enable);
		mechanics.values().stream().filter(mechanic -> mechanic.getLoadPriority() == LoadPriority.LOW).forEach(Mechanic::enable);
		mechanics.values().stream().filter(mechanic -> mechanic.getLoadPriority() == LoadPriority.LOWEST).forEach(Mechanic::enable);
	}

	public static void disableMechanics() {
		mechanics.values().stream().filter(mechanic -> mechanic.getLoadPriority() == LoadPriority.MONITOR).forEach(Mechanic::disable);
		mechanics.values().stream().filter(mechanic -> mechanic.getLoadPriority() == LoadPriority.HIGHEST).forEach(Mechanic::disable);
		mechanics.values().stream().filter(mechanic -> mechanic.getLoadPriority() == LoadPriority.HIGH).forEach(Mechanic::disable);
		mechanics.values().stream().filter(mechanic -> mechanic.getLoadPriority() == LoadPriority.NORMAL).forEach(Mechanic::disable);
		mechanics.values().stream().filter(mechanic -> mechanic.getLoadPriority() == LoadPriority.LOW).forEach(Mechanic::disable);
		mechanics.values().stream().filter(mechanic -> mechanic.getLoadPriority() == LoadPriority.LOWEST).forEach(Mechanic::disable);
	}
	
}