package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.events;

import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AtherialChatEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private String message;
	private GamePlayer gamePlayer;
	private boolean cancel = false;

	public AtherialChatEvent(GamePlayer gamePlayer, String message) {
		this.gamePlayer = gamePlayer;
		this.message = message;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}
}