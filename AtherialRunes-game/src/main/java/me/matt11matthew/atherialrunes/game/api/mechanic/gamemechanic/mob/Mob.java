package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob;

import me.matt11matthew.atherialrunes.exceptions.NotJsonFileException;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.utils.json.JSONUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.List;

public class Mob {

    private List<String> displayNames;
    private double minHealth;
    private double maxHealth;
    private double minDamage;
    private double maxDamage;
    private MobType mobType;
    private String id;
    private List<String> helmets;
    private List<String> chestplates;
    private List<String> leggings;
    private List<String> boots;
    private List<String> weapons;

    public Mob(String id) {
        this.id = id;
        File mobFile = new File(Main.getInstance().getDataFolder() + "/mobs/", id + ".json");

        JSONObject obj = null;
        try {
            obj = JSONUtils.convertStringToJSONObject(mobFile);
        } catch (NotJsonFileException e) {
            e.printStackTrace();
        }
        String type = (String) obj.get("type");

        String rawHealth = (String) obj.get("health");
        double minHealth = Double.parseDouble(rawHealth.split("-")[0]);
        double maxHealth = Double.parseDouble(rawHealth.split("-")[1]);

        String rawDamage = (String) obj.get("damage");
        double minDamage = Double.parseDouble(rawDamage.split("-")[0]);
        double maxDamage = Double.parseDouble(rawDamage.split("-")[1]);

        this.displayNames = (JSONArray) obj.get("names");
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.minHealth = minHealth;
        this.maxHealth = maxHealth;
        this.helmets = (JSONArray) obj.get("helmets");
        this.chestplates = (JSONArray) obj.get("chestplates");
        this.leggings = (JSONArray) obj.get("leggings");
        this.boots = (JSONArray) obj.get("boots");
        this.weapons = (JSONArray) obj.get("weapons");
        this.mobType = MobType.valueOf(type.toUpperCase());
    }

    public List<String> getDisplayNames() {
        return displayNames;
    }

    public void setDisplayNames(List<String> displayNames) {
        this.displayNames = displayNames;
    }

    public double getMinHealth() {
        return minHealth;
    }

    public void setMinHealth(double minHealth) {
        this.minHealth = minHealth;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(double minDamage) {
        this.minDamage = minDamage;
    }

    public double getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(double maxDamage) {
        this.maxDamage = maxDamage;
    }

    public MobType getMobType() {
        return mobType;
    }

    public void setMobType(MobType mobType) {
        this.mobType = mobType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getHelmets() {
        return helmets;
    }

    public void setHelmets(List<String> helmets) {
        this.helmets = helmets;
    }

    public List<String> getBoots() {
        return boots;
    }

    public void setBoots(List<String> boots) {
        this.boots = boots;
    }

    public List<String> getLeggings() {
        return leggings;
    }

    public void setLeggings(List<String> leggings) {
        this.leggings = leggings;
    }

    public List<String> getChestplates() {
        return chestplates;
    }

    public void setChestplates(List<String> chestplates) {
        this.chestplates = chestplates;
    }

    public List<String> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<String> weapons) {
        this.weapons = weapons;
    }
}
