package core.managers;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import core.data.User;
import org.bukkit.entity.Player;
import pl.justrpg.api.RPG;
import pl.justrpg.api.util.Logger;

public  class UserManager
{
    public static final HashMap<UUID, User> users;

    static {
        users = new HashMap<UUID, User>();
    }

    public static User getUser(final Player player) {
        return UserManager.users.get(player.getUniqueId());
    }

    public static User getUser(final UUID uuid) {
        return UserManager.users.get(uuid);
    }

    public static User getUser(final String name) {
        for ( User u : UserManager.users.values()) {
            if (u.getLastName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        return null;
    }

    public static Set<User> getUsersByIp(final String ip) {
        final HashSet<User> local = new HashSet<User>();
        for (User u : UserManager.users.values()) {
            if (u.getLastIP().equalsIgnoreCase(ip) || u.getFirstIP().equalsIgnoreCase(ip)) {
                local.add(u);
            }
        }
        return local;
    }

    public static User createUser(final Player p) {
        final User u = new User(p);
        //RankingManager.addRanking(u);
        UserManager.users.put(p.getUniqueId(), u);
        return u;
    }

    public static void joinToGame(final Player p)
    {
        final User u = getUser(p);
        if (u == null)
        {
            createUser(p);
        }
        else
        {
            u.setLastName(p.getName());
            u.setLastJoin(System.currentTimeMillis());
            u.setLastIP(p.getAddress().getHostString());
            p.setGameMode(u.getGameMode());
            p.setAllowFlight(u.isFly());
        }
    }

    public static void leaveFromGame(final Player p) {
        final User u = getUser(p);
        if (u == null) {
            Logger.severe("Dane uzytkownika '" + p.getName() + "' przepadly!");
            return;
        }
        u.setFly(p.getAllowFlight());
        u.setGameMode(p.getGameMode());
        u.setLastName(p.getName());
        u.setLastLocation(p.getLocation());
        u.addTimePlay((int)((System.currentTimeMillis() - u.getLastJoin()) / 1000L));
        u.setGod(p.hasPermission("core.god"));
        u.update(true);
    }

    public static void setup() {
        final ResultSet rs = RPG.getStore().query("SELECT * FROM `{P}users`");
        try {
            while (rs.next()) {
                final User e = new User(rs);
                UserManager.users.put(e.getUuid(), e);
                //RankingManager.addRanking(e);
            }
            Logger.info("Loaded " + UserManager.users.size() + " users!");
        }
        catch (SQLException sqlexception) {
            Logger.warning("An error occurred while loading users!", "Error: " + sqlexception.getMessage());
            sqlexception.printStackTrace();
        }
    }

    public static HashMap<UUID, User> getUsers() {
        return UserManager.users;
    }
    public static User getPlayerAccountByNick(final String player) {
        final List<User> copyList;
        synchronized (UserManager.users) {
            copyList = new ArrayList<User>(UserManager.users.values());
        }
        for (User acc : copyList) {
            if (acc.getLastName().equalsIgnoreCase(player)) {
                return acc;
            }
        }
        return null;
    }


    //public static int getPosition(User user) {
    //  for (RankList.Data<User> userData : TabThread.getInstance().getRankList().getTopPlayers())
    //       if (userData.getKey().equals(user))
    //           return TabThread.getInstance().getRankList().getTopPlayers().indexOf(userData)+1;
    //  return -1;
    // }
}
