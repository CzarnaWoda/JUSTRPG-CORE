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
import pl.justrpg.api.util.Util;

public class MsgCommand extends Command implements Colors {

    public MsgCommand() {
        super("msg", "", "/msg <gracz> <tekst>", "core.cmd.msg", new String[] {"tell", "whisper", "pw"});
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if(commandSender instanceof Player){
            if(Bukkit.getPlayer(args[0]) != null){
                Player target = Bukkit.getPlayer(args[0]);
                Player p = (Player) commandSender;
                User u = UserManager.getUser(p);
                String msg = "";
                for(int i = 1; i < args.length; i++){
                    msg += args[i] + " ";
                }
                String[] allFromMsg = msg.split(" ");
                if(allFromMsg.length == 0){
                    return Util.sendMsg(p, MessageConfig.MESSAGE_COMMAND_GETUSAGE.replace("{USAGE}", getUsage()));
                }
                if(u.getLastMsg() == null || u.getLastMsg().getName() != target.getName()){
                    u.setLastMsg(target);
                }
                Util.sendMsg(target, ImportantColor + p.getName() + MainColor + " ->> " + ImportantColor + "Ty" + MainColor + ": " + ChatColor.BLUE + msg);
                return Util.sendMsg(p, ImportantColor + "Ty" + MainColor + " ->> " + ImportantColor + target.getName() + MainColor + ": " + ChatColor.BLUE + msg);
            }else{
                return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
            }
        }else{
            if(Bukkit.getPlayer(args[0]) != null){
                Player target = Bukkit.getPlayer(args[0]);
                String msg = "";
                for(int i = 1; i < args.length; i++){
                    msg += " " + args[i];
                }
                String[] allFromMsg = msg.split(" ");
                if(allFromMsg.length == 0){
                    return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_GETUSAGE.replace("{USAGE}", getUsage()));
                }
                Util.sendMsg(target, ImportantColor + commandSender.getName().replace("CONSOLE", "KONSOLA") + MainColor + " ->> " + ImportantColor + "Ty" + MainColor + ": " + ChatColor.BLUE + msg);
                return Util.sendMsg(commandSender, ImportantColor + "Ty" + MainColor + " ->> " + ImportantColor + target.getName() + MainColor + ": " + ChatColor.BLUE + msg);
            }else{
                return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
            }
        }
    }
}
