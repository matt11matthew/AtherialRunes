package me.matt11matthew.atherialrunes.game.api.player;

public class PlayerToggle {

    public String toggles;
    private GamePlayer gamePlayer;

    public PlayerToggle(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public String getToggles() {
        return toggles;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public boolean isDebug() {
        return (toggles.contains("debug"));
    }

    public void addToggles(String toggles) {
        this.toggles = toggles;
    }
}
