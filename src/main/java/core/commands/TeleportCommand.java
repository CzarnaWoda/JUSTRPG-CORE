package core.commands;

import core.interfaces.Colors;
import core.settings.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.justrpg.api.commands.Command;
import pl.justrpg.api.util.Util;

public class TeleportCommand extends Command implements Colors {

    public TeleportCommand() {
        super("teleport", "", "/teleport <gracz/x,y,z> [gracz/x,y,z]", "core.cmd.teleport", new String[] {"tp"});
    }

    public boolean onExecute(CommandSender commandSender, String[] args) {
        if(commandSender instanceof Player){
            if(args.length < 1){
                return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_GETUSAGE.replace("{USAGE}", getUsage()));
            }
            if(Bukkit.getPlayer(args[0]) != null){
                Player target = Bukkit.getPlayer(args[0]);
                ((Player)commandSender).teleport(target.getLocation());
                return Util.sendMsg(commandSender, MainColor + "Zostales przeteleportowany do " + ImportantColor + target.getName() + MainColor + "!");
            }
            if(args.length == 2){
                if(Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[1]) != null){
                    Player teleport = Bukkit.getPlayer(args[0]);
                    Player target = Bukkit.getPlayer(args[1]);
                    teleport.teleport(target.getLocation());
                    Util.sendMsg(teleport, MainColor + "Zostales przeteleportowany do " + ImportantColor + target.getName() +  MainColor + " przez " + ImportantColor + commandSender.getName() + MainColor + "!");
                    return Util.sendMsg(commandSender, MainColor + "Przeteleportowales gracza " + ImportantColor + teleport.getName() + MainColor + " do gracza " + ImportantColor + target.getName() + MainColor + "!");
                }else {
                    return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
                }
            }
            if(args.length == 3){
                int x = Integer.parseInt(args[0]);
                int y = Integer.parseInt(args[1]);
                int z = Integer.parseInt(args[2]);
                Location l = new Location(((Player)commandSender).getWorld(), x, y, z);
                ((Player)commandSender).teleport(l);
                return Util.sendMsg(commandSender, MainColor + "Zostales przeteleportowany do lokacji " + ImportantColor + "x: " + x + " y: " + y + " z: " + z + MainColor + "!");
            }
            if(args.length == 4){
                if(Bukkit.getPlayer(args[0]) != null){
                    Player target = Bukkit.getPlayer(args[0]);
                    int x = Integer.parseInt(args[1]);
                    int y = Integer.parseInt(args[2]);
                    int z = Integer.parseInt(args[3]);
                    Location l = new Location(target.getWorld(), x, y, z);
                    target.teleport(l);
                    Util.sendMsg(commandSender, MainColor + "Przeteleportowales gracza " + ImportantColor + target.getName() + MainColor + " do lokacji: " + ImportantColor + "x: " + x + " y: " + y + " z: " + z + MainColor + "!");
                    return Util.sendMsg(target, MainColor + "Zostales przeteleportowany do lokacji " + ImportantColor + "x: " + x + " y: " + y + " z: " + z + MainColor + " przez: " + ImportantColor + commandSender.getName() + MainColor + "!");
                }
            }else{
                return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
            }
        }else{
            if(args.length < 2){
                return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_GETUSAGE.replace("{USAGE}", getUsage()));
            }
            if(args.length == 4){
                if(Bukkit.getPlayer(args[0]) != null){
                    Player target = Bukkit.getPlayer(args[0]);
                    int x = Integer.parseInt(args[1]);
                    int y = Integer.parseInt(args[2]);
                    int z = Integer.parseInt(args[3]);
                    Location l = new Location(target.getWorld(), x, y, z);
                    target.teleport(l);
                    Util.sendMsg(commandSender, MainColor + "Przeteleportowales gracza " + ImportantColor + target.getName() + MainColor + " do lokacji: " + ImportantColor + "x: " + x + " y: " + y + " z: " + z + MainColor + "!");
                    return Util.sendMsg(target, MainColor + "Zostales przeteleportowany do lokacji " + ImportantColor + "x: " + x + " y: " + y + " z: " + z + MainColor + " przez: " + ImportantColor + "KONSOLA" + MainColor + "!");
                }
            }
            if(args.length == 2){
                if(Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[1]) != null){
                    Player teleport = Bukkit.getPlayer(args[0]);
                    Player target = Bukkit.getPlayer(args[1]);
                    teleport.teleport(target.getLocation());
                    Util.sendMsg(teleport, MainColor + "Zostales przeteleportowany do " + ImportantColor + target.getName() + MainColor + " przez " + ImportantColor + "KONSOLA" + MainColor + "!");
                    return Util.sendMsg(commandSender, MainColor + "Przeteleportowales gracza " + ImportantColor + teleport.getName() + MainColor + " do gracza " + ImportantColor + target.getName() + MainColor + "!");
                }else {
                    return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
                }
            }else{
                return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_GETUSAGE.replace("{USAGE}", getUsage()));
            }
        }
        return false;
    }
}
