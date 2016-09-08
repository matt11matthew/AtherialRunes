package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats.tier.T1;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats.tier.T2;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats.tier.T3;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats.tier.T4;

import java.util.Random;

public class StatValues {

    private T1 t1;
    private T2 t2;
    private T3 t3;
    private T4 t4;

    public StatValues(T1 t1, T2 t2, T3 t3, T4 t4) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
    }

    public double get(Object tier) {
        if (tier instanceof T1) {
            return random(t1.getLow(), t1.getHigh());
        } else if (tier instanceof T2) {
            return random(t2.getLow(), t2.getHigh());
        } else if (tier instanceof T2) {
            return random(t2.getLow(), t2.getHigh());
        } else if (tier instanceof T3) {
            return random(t3.getLow(), t3.getHigh());
        } else if (tier instanceof T4) {
            return random(t4.getLow(), t4.getHigh());
        }
        return 0.0D;
    }

    private double random(double low, double high) {
        Random r = new Random();
        double num = r.nextInt((int) (high - low + 1)) + (int) low;
        if (num < 1) {
            num = 1;
        }
        return num;
    }
}
