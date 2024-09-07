package core.commands;

import core.data.User;
import core.interfaces.Colors;
import core.managers.UserManager;
import core.settings.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.justrpg.api.commands.Command;
import pl.justrpg.api.commands.PlayerCommand;
import pl.justrpg.api.util.Util;

public class FlyCommand extends Command implements Colors {

    public FlyCommand() {
        super("fly", "", "/fly [gracz]", "core.cmd.fly", new String[0]);
    }

    void setFly(Player p, CommandSender changer){
        User u = UserManager.getUser(p.getUniqueId());
        if (u == null) {
            return;
        }
        p.setAllowFlight(!u.isFly());
        p.setFlying(!u.isFly());
        u.setFly(!u.isFly());
        if (changer == null) {
            Util.sendMsg(p, MainColor + "Latanie zostalo dla ciebie " + ImportantColor + (u.isFly() ? ChatColor.GREEN + "wlaczone" : ChatColor.DARK_RED + "wylaczone") + MainColor + "!");
        }
        else {
            String c = changer.getName().equalsIgnoreCase("CONSOLE") ? "konsole" : changer.getName();
            Util.sendMsg(p, MainColor + "Latanie zostalo dla ciebie " + ImportantColor + (u.isFly() ? ChatColor.GREEN + "wlaczone" : ChatColor.DARK_RED + "wylaczone") + MainColor + " przez " + ImportantColor + c + MainColor + "!");
            Util.sendMsg(changer, MainColor + "Latanie dla gracza " + ImportantColor + p.getName() + MainColor + " zostal zmieniony na " + ImportantColor + (u.isFly() ? ChatColor.GREEN + "wlaczone" : ChatColor.DARK_RED + "wylaczone") + MainColor + "!");
        }
    }

    public boolean onExecute(CommandSender commandSender, String[] args) {
        if(args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null){
                return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
            }
            setFly(target, commandSender);
            return true;
        }
        setFly((Player)commandSender, null);
        return true;
    }
}
