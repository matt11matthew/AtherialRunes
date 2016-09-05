package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.damage.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.matt11matthew.atherialrunes.game.player.GamePlayer;

public class PlayerDamagePlayerEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	
	private GamePlayer attacker;
	private GamePlayer gamePlayer;
	private boolean cancel = false;
	private double damage;
	
	public PlayerDamagePlayerEvent(GamePlayer gamePlayer, GamePlayer attacker, double damage) {
		this.gamePlayer = gamePlayer;
		this.attacker = attacker;
		this.damage = damage;
	}
	
	@Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

	public GamePlayer getGamePlayer() {
		return gamePlayer;
	}

	@Override
	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	public GamePlayer getAttacker() {
		return attacker;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

}