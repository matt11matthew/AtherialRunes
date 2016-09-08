package me.matt11matthew.atherialrunes.game.api.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerToggle {

    public List<String> toggles = new ArrayList<>();
    private GamePlayer gamePlayer;

    public PlayerToggle(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public List<String> getToggles() {
        return toggles;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public boolean isDebug() {
        return (toggles.contains("debug"));
    }

    public void addToggles(List<String> toggles) {
        this.toggles.addAll(toggles);
    }
}
