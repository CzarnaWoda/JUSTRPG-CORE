package core.commands;

import core.interfaces.Colors;
import core.settings.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.justrpg.api.commands.PlayerCommand;
import pl.justrpg.api.util.Util;

public class InvseeCommand extends PlayerCommand implements Colors {

    public InvseeCommand() {
        super("invsee", "", "/invsee <ec/eq> <gracz>", "core.cmd.invsee", new String[] { "oi" });
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(args.length < 2){
            return Util.sendMsg(player, MessageConfig.MESSAGE_COMMAND_GETUSAGE.replace("{USAGE}", getUsage()));
        }
        if(args[0].equalsIgnoreCase("ec")){
            if(Bukkit.getPlayer(args[1]) != null){
                Player target = Bukkit.getPlayer(args[1]);
                player.openInventory(target.getEnderChest());
            }else{
                return Util.sendMsg(player, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
            }
        }else if(args[0].equalsIgnoreCase("eq")){
            if(Bukkit.getPlayer(args[1]) != null){
                Player target = Bukkit.getPlayer(args[1]);
                player.openInventory(target.getInventory());
            }else{
                return Util.sendMsg(player, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
            }
        }
        return false;
    }
}
