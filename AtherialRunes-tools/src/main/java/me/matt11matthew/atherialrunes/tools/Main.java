package me.matt11matthew.atherialrunes.tools;

import me.matt11matthew.atherialrunes.tools.menus.MainMenu;
import me.matt11matthew.atherialrunes.tools.user.User;

import javax.swing.*;

public class Main {

    private User user;

    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.setVisible(true);
    }

    public static User getUser() {
        return new Main().user;
    }
}
