package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.damage;

import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.damage.events.PlayerDamagePlayerEvent;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.gear.weapon.Weapon;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageMechanics extends ListenerMechanic {

    @Override
    public void onEnable() {
        print("-----------------------------------------");
        print("[DamageMechanics] Enabling...");
        print("-----------------------------------------");
        registerListeners();
    }

    @Override
    public void onDisable() {
        print("-----------------------------------------");
        print("[DamageMechanics] Disabling...");
        print("-----------------------------------------");
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.NORMAL;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (isPlayer(e.getDamager(), e.getEntity())) {
            Player attacker = getPlayer(e.getDamager());
            Player player = getPlayer(e.getEntity());
            PlayerDamagePlayerEvent event = new PlayerDamagePlayerEvent(Main.getGamePlayer(player), Main.getGamePlayer(attacker));
            Bukkit.getPluginManager().callEvent(event);
            e.setCancelled(true);
            if (event.isCancelled()) {
                return;
            }
            processDamage(Main.getGamePlayer(attacker), Main.getGamePlayer(player));
        }
    }

    private void processDamage(GamePlayer player, GamePlayer l) {
        double damage = 0.0D;
        Weapon wep = player.getWeapon();
        switch (wep) {
            case FIST:
                damage = 1.0D;
                break;
            case WEAPON:
                damage += player.getWeaponDamage();
                break;
        }
        if (damage > 0.0D) {
            l.damageByPlayer(player, damage);
        }
    }

    public Player getPlayer(Entity entity) {
        if (entity instanceof Arrow) {
            Arrow arrow = (Arrow) entity;
            return (Player) arrow.getShooter();
        }
        if (entity instanceof Projectile) {
            Projectile projectile = (Projectile) entity;
            return (Player) projectile.getShooter();
        }
        return (Player) entity;
    }

    public boolean isPlayer(Entity attacker, Entity player) {
        boolean isPlayer = false;
        if ((player.getType() == EntityType.PLAYER) && (attacker.getType() == EntityType.PLAYER)) {
            isPlayer = true;
        } else if ((player.getType() == EntityType.PLAYER) && (attacker.getType() == EntityType.ARROW)) {
            Arrow arrow = (Arrow) attacker;
            if (arrow.getShooter() instanceof Player) {
                isPlayer = true;
            }

        } else if ((player.getType() == EntityType.PLAYER) && (attacker instanceof Projectile)) {
            Projectile projectile = (Projectile) attacker;
            if (projectile.getShooter() instanceof Player) {
                isPlayer = true;
            }
        }
        return isPlayer;
    }
}