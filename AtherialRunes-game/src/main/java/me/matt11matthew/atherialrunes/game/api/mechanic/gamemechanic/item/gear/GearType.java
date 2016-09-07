package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.gear;

public enum GearType {

    ARMOR("Armor"),
    WEAPON("Weapon");

    String name;

    GearType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
