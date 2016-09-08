package me.matt11matthew.atherialrunes.game.player;

import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;

public class PlayerStatistics {

    private final GamePlayer gamePlayer;
    private int t1MobKills, t2MobKills, t3MobKills, t4MobKills;
    private int playerKills;
    private int totalNotoriety, gainedNotoriety, lostNotoriety;
    private int damageDone, damageTaken;
    private long firstLogin;

    public PlayerStatistics(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }
}
