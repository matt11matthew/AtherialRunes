package me.matt11matthew.atherialrunes.game.utils;

import java.util.Random;

public class RandomUtils {

    public static int random(int low, int high) {
        Random r = new Random();
        int num = r.nextInt((high - low + 1)) + low;
        if (num < 1) {
            num = 1;
        }
        return num;
    }
}
