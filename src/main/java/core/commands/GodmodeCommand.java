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

public class GodmodeCommand extends Command implements Colors {

    public GodmodeCommand() {
        super("god", "", "", "core.cmd.god", new String[0]);
    }

    void setGod(Player p, CommandSender changer){
        User u = UserManager.getUser(p.getUniqueId());
        if (u == null) {
            return;
        }
        u.setGod(!u.isGod());
        if (changer == null) {
            Util.sendMsg(p, MainColor + "Godmode zostal dla ciebie " + ImportantColor + (u.isGod() ? ChatColor.GREEN + "wlaczony" : ChatColor.DARK_RED + "wylaczony") + MainColor + "!");
        }
        else {
            String c = changer.getName().equalsIgnoreCase("CONSOLE") ? "konsole" : changer.getName();
            Util.sendMsg(p, MainColor + "Godmode zostal dla ciebie " + ImportantColor + (u.isGod() ? ChatColor.GREEN + "wlaczony" : ChatColor.DARK_RED + "wylaczony") + MainColor + " przez " + ImportantColor + c + MainColor + "!");
            Util.sendMsg(changer, MainColor + "Godmode zostal dla ciebie " + ImportantColor + p.getName() + MainColor + " zmieniony na " + ImportantColor + (u.isGod() ? ChatColor.GREEN + "wlaczony" : ChatColor.DARK_RED + "wylaczony") + MainColor + "!");
        }
    }

    public boolean onExecute(CommandSender commandSender, String[] args) {
        if(args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null){
                return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
            }
            setGod(target, commandSender);
            return true;
        }
        setGod((Player)commandSender, null);
        return true;
    }
}
