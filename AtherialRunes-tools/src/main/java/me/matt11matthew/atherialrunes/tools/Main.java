package me.matt11matthew.atherialrunes.tools;

import me.matt11matthew.atherialrunes.tools.menus.MainMenu;
import me.matt11matthew.atherialrunes.tools.sql.ConnectionPool;
import me.matt11matthew.atherialrunes.tools.user.User;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static User user;

    public static void main(String[] args) {
        ConnectionPool.getConnection();
        MainMenu menu = new MainMenu();
        menu.setSize(new Dimension(500, 500));
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.setVisible(true);
    }

    public static User getUser() {
        return user;
    }
}
