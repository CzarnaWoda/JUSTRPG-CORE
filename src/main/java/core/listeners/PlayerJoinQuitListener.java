package core.listeners;

import core.data.User;
import core.interfaces.Colors;
import core.managers.UserManager;
import core.settings.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.justrpg.api.util.Util;

public class PlayerJoinQuitListener implements Listener, Colors {

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        User u = UserManager.getUser(p);
        if(u != null) {
            final Location last = u.getLastLocation();
            p.teleport(last);
        }
        if(u == null) {
            p.teleport(p.getWorld().getSpawnLocation());
            u = UserManager.createUser(p);
        }
        UserManager.joinToGame(p);
        for(String s : MessageConfig.MESSAGE_JOIN)
            Util.sendMsg(p, Util.fixColor(Util.getReplacement(s.replace("{PLAYER}", p.getDisplayName())
                    .replace("{ONLINE}", String.valueOf(Bukkit.getOnlinePlayers())))));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoinLater(PlayerJoinEvent e) {
        e.setJoinMessage(null);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerKickLater(PlayerKickEvent e)
    {
        e.setLeaveMessage(null);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerKick(PlayerKickEvent e)
    {
        final Player p = e.getPlayer();
        quitGame(p);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        UserManager.leaveFromGame(p);
        quitGame(p);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuitLater(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    private static void quitGame(Player p) {
        /*Combat combat = CombatManager.getCombat(p);
        if(combat == null)
            return;
        if(!combat.hasFight())
            return;
        p.setHealth(0.0D);
        Guild g = GuildManager.getGuild(p);
        Bukkit.broadcastMessage(Util.fixColor(Util.replaceString(SpecialSigns + "* " + WarningColor_2 + BOLD + "ANTYLOGOUT" + SpecialSigns + " * " + MainColor + "Gracz " + (g == null ? "" : SpecialSigns + "[" + ImportantColor + g.getTag() + SpecialSigns + "]") +  ImportantColor + p.getName() + MainColor + " wylogowal sie podczas " + ImportantColor + "walki")));
        User u = UserManager.getUser(p);
        u.addLogouts(1);*/
    }

}
