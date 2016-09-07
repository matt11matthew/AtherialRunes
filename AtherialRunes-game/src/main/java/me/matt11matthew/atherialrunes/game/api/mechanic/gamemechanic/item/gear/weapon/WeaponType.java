package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.gear.weapon;

public enum WeaponType {

    SWORD("Sword"),
    AXE("Axe"),
    SCYTH("Hoe"),
    POLEARM("Spade"),
    BOW("Bow");

    String name;

    WeaponType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
