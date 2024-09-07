package core.commands;

import com.google.common.base.Strings;
import core.Main;
import core.interfaces.Colors;
import core.settings.MessageConfig;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.justrpg.api.commands.PlayerCommand;
import pl.justrpg.api.util.Util;

public class HelpopCommand extends PlayerCommand implements Colors {

    public HelpopCommand() {
        super("helpop", "", "/helpop <tresc>", "core.cmd.helpop", new String[0]);
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(args.length < 1){
            return Util.sendMsg(player, MessageConfig.MESSAGE_COMMAND_GETUSAGE.replace("{USAGE}", getUsage()));
        }
        String message = "";
        for(int i = 0; i < args.length; i++){
            message = " " + args[i];
        }
        //TODO: Slowdown pisania na helpop
        String finalMessage = message;
        Bukkit.getOnlinePlayers().stream().filter(p -> ((Player) p).hasPermission("core.readhelpop")).forEach(p -> Util.sendMsg(p, ChatColor.DARK_RED + "HELPOP " + ImportantColor + player.getName() + MainColor + ": " + finalMessage));
        return true;
    }
}
