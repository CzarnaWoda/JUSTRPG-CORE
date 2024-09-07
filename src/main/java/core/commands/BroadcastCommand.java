package core.commands;

import core.interfaces.Colors;
import core.settings.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import pl.justrpg.api.commands.Command;
import pl.justrpg.api.util.Util;

public class BroadcastCommand extends Command implements Colors {

    public BroadcastCommand() {
        super("broadcast", "", "/broadcast <tekst>", "core.cmd.bc", new String[] {"bc", "alert"});
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        String msg = "";
        for(int i = 0; i < args.length; i++){
            msg += " " + args[i];
        }
        String[] allFromMsg = msg.split(" ");
        if(allFromMsg.length == 0){
            return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_GETUSAGE.replace("{USAGE}", getUsage()));
        }
        String finalMsg = msg;
        Bukkit.getOnlinePlayers().forEach(e -> {
            Util.sendMsg(e, ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "JUSTRPG " + MainColor + "->> " + ImportantColor + finalMsg);
        });
        return true;
    }
}
