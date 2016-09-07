package me.matt11matthew.atherialrunes.game.player;

import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;

public class PlayerStatistics {

    private final GamePlayer gamePlayer;

    public PlayerStatistics(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public int t1mobKills, t2mobKills, t3mobKills, t4mobKills;

    public int playerKills;

}
