package core.commands;

import core.Main;
import core.data.User;
import core.interfaces.Colors;
import core.managers.UserManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.justrpg.api.commands.PlayerCommand;
import pl.justrpg.api.util.Util;

public class VanishCommand extends PlayerCommand implements Colors {

    public VanishCommand() {
        super("vanish", "", "/vanish", "core.cmd.vanish", new String[] {"v"});
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        User u = UserManager.getUser(p);
        if(!u.isVanish()){
            u.setVanish(true);
            p.getWorld().strikeLightningEffect(p.getLocation());
            p.getWorld().strikeLightningEffect(p.getLocation());
            p.getWorld().strikeLightningEffect(p.getLocation());
            //TODO: Nametag '[VANISH] NICK'
            Bukkit.getOnlinePlayers().forEach(player -> {
                if(player.getName() != p.getName()){
                    player.hidePlayer(Main.getPlugin(), p);
                }
            });
            Bukkit.getOnlinePlayers().stream().filter(players -> ((Player) players).hasPermission("core.cmd.vanish")).forEach(pe -> Util.sendMsg(pe, ChatColor.BLUE + "VANISH " + ImportantColor + p.getName() + MainColor + " wlaczyl vanisha"));
            return Util.sendMsg(p, MainColor + "Wlaczyles tryb " + ImportantColor + "vanisha");
        }else{
            u.setVanish(false);
            p.getWorld().strikeLightningEffect(p.getLocation());
            p.getWorld().strikeLightningEffect(p.getLocation());
            p.getWorld().strikeLightningEffect(p.getLocation());
            Bukkit.getOnlinePlayers().forEach(player -> {
                if(player.getName() != p.getName()){
                    player.showPlayer(Main.getPlugin(), p);
                }
            });
            Bukkit.getOnlinePlayers().stream().filter(players -> ((Player) players).hasPermission("core.cmd.vanish")).forEach(pe -> Util.sendMsg(pe, ChatColor.BLUE + "VANISH " + ImportantColor + p.getName() + MainColor + " wylaczyl vanisha"));
            return Util.sendMsg(p, MainColor + "Wylaczyles tryb " + ImportantColor + "vanisha");
        }
    }
}
