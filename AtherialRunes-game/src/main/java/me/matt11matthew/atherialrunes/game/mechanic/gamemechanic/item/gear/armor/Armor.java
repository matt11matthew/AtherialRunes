package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.item.gear.armor;

//TODO fix slot numbers
public enum Armor {

    HELMET(1),
    CHESTPLATE(5),
    LEGGINGS(5),
    BOOTS(3);

    int slot;

    Armor(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }
}
