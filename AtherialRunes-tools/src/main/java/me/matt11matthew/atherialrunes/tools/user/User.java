package me.matt11matthew.atherialrunes.tools.user;

import me.matt11matthew.atherialrunes.tools.Utils;

public class User {

    private String username;
    private String password = null;
    private int accessLevel;
    private int loginCount;
    private int logoutCount;
    private String minecraftUUID;

    public User(String username, String password) {
        this.username = username;
        String pass = Utils.getString("adminlogins", "username=" + username, "password");
        if (pass == null) {
            return;
        }
        if (!password.equals(pass)) {
            return;
        }
        this.password = password;
    }

    public static User login(String username, String password) {
        User user = new User(username, password);
        if (user.getPassword() == null) {
            return null;
        }
        user.minecraftUUID = Utils.getString("adminlogins", "username=" + username, "minecraftuuid");
        user.loginCount = Utils.getInt("adminlogins", "username=" + username, "logincount");
        user.logoutCount = Utils.getInt("adminlogins", "username=" + username, "logoutcount");
        user.accessLevel = Utils.getInt("adminlogins", "username=" + username, "accesslevel");
        return user;
    }

    public String getMinecraftUUID() {
        return minecraftUUID;
    }

    public void setMinecraftUUID(String minecraftUUID) {
        this.minecraftUUID = minecraftUUID;
    }

    public int getLogoutCount() {
        return logoutCount;
    }

    public void setLogoutCount(int logoutCount) {
        this.logoutCount = logoutCount;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
