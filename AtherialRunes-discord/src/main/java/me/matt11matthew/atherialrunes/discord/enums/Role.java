package me.matt11matthew.atherialrunes.discord.enums;

public enum Role {

    LEAD("Leads"),
    LEAD_DEVELOPER("Lead Developer"),
    DEVELOPER("Developer"),
    LORE_LEAD("Lore Lead"),
    LEAD_BUILDER("Lead Builder"),
    BUILDER("Builder"),
    GLOBAL_ADMIN("GLOBAL_ADMIN");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
