package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.server;

import java.util.concurrent.TimeUnit;

public class ServerUtils {

    public static int convertStringToInt(String s) {
        int i = 0;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {

        }
        return i;
    }

    public static String parseMilis(long l) {
        long sec = TimeUnit.MILLISECONDS.toSeconds(l);
        if (sec - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) > 0) {
            sec = sec - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        }
        long hour = 0;
        long min = 0;
        while (sec >= 60) {
            sec -= 60;
            min += 1;
        }
        while (min >= 60) {
            min -= 60;
            hour += 1;
        }
        String returnString = "&a" + hour + "&lh &a" + min + "&lm &a" + sec + "&ls";
        return returnString;
    }

    public static boolean reboot(long l) {
        return (System.currentTimeMillis() >= l);
    }

    public static long convertToMillis(int hours) {
        try {
            return System.currentTimeMillis() + TimeUnit.HOURS.toMillis(hours);
        } catch (Exception e) {
            return 0;
        }
    }

    public static long convertSecondsToMillis(int seconds) {
        try {
            return System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(seconds);
        } catch (Exception e) {
            return 0;
        }
    }
}
