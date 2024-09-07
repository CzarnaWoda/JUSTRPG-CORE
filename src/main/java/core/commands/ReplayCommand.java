package core.commands;

import core.data.User;
import core.interfaces.Colors;
import core.managers.UserManager;
import core.settings.MessageConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.justrpg.api.commands.PlayerCommand;
import pl.justrpg.api.util.Util;

public class ReplayCommand extends PlayerCommand implements Colors {

    public ReplayCommand() {
        super("replay", "", "/replay <tekst>", "core.cmd.replay", new String[] {"r"});
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        User u = UserManager.getUser(p);
        if(u.getLastMsg() != null){
            if(u.getLastMsg().isOnline()){
                Player target = u.getLastMsg();
                String msg = "";
                for(int i = 0; i < args.length; i++){
                    msg += args[i] + " ";
                }
                String[] allFromMsg = msg.split(" ");
                if(allFromMsg.length == 0){
                    return Util.sendMsg(p, MessageConfig.MESSAGE_COMMAND_GETUSAGE.replace("{USAGE}", getUsage()));
                }
                Util.sendMsg(target, ImportantColor + p.getName() + MainColor + " ->> " + ImportantColor + "Ty" + MainColor + ": " + ChatColor.BLUE + msg);
                return Util.sendMsg(p, ImportantColor + "Ty" + MainColor + " ->> " + ImportantColor + target.getName() + MainColor + ": " + ChatColor.BLUE + msg);
            }else{
                return Util.sendMsg(p, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
            }
        }else{
            return Util.sendMsg(p, WarningColor + "Nie pisales jeszcze do nikogo!");
        }
    }
}
