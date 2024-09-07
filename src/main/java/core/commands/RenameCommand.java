package core.commands;

import core.interfaces.Colors;
import core.settings.MessageConfig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.justrpg.api.commands.PlayerCommand;
import pl.justrpg.api.util.Util;

public class RenameCommand extends PlayerCommand implements Colors {

    public RenameCommand() {
        super("rename", "", "/rename <name>", "core.cmd.rename", new String[0]);
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(player.getItemInHand() != null){
            String msg = "";
            for(int i = 0; i < args.length; i++){
                msg += " " + args[i];
            }
            String[] allFromMsg = msg.split(" ");
            if(allFromMsg.length == 0){
                return Util.sendMsg(player, MessageConfig.MESSAGE_COMMAND_GETUSAGE.replace("{USAGE}", getUsage()));
            }
            ItemStack is = player.getItemInHand();
            ItemMeta itemMeta = is.getItemMeta();
            itemMeta.setDisplayName(Util.fixColor(msg));
            is.setItemMeta(itemMeta);
            return Util.sendMsg(player, MainColor + "Twoj item w rece zostal nazwany: " + Util.fixColor(msg));
        }else{
            return Util.sendMsg(player, MainColor + "Nie masz przedmiotu w rece!");
        }
    }
}
