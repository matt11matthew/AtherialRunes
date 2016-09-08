package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats.tier;

public abstract class StatTier {

    private int low;
    private int high;

    public StatTier(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }
}
