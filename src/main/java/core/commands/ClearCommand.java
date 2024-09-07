package core.commands;

import core.interfaces.Colors;
import core.settings.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.justrpg.api.commands.PlayerCommand;
import pl.justrpg.api.util.Util;

public class ClearCommand extends PlayerCommand implements Colors {

    public ClearCommand() {
        super("clear", "clear", "/clear [gracz]", "core.cmd.clear", new String[0]);
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(args.length == 1){
            if(Bukkit.getPlayer(args[0]) != null){
                Player target = Bukkit.getPlayer(args[0]);
                target.getInventory().clear();
                target.getInventory().setArmorContents(new ItemStack[4]);
                return Util.sendMsg(player, MainColor + "Wyczyszczono ekwipunek gracza " + ImportantColor + target.getName());
            }else{
                return Util.sendMsg(player, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
            }
        }
        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[4]);
        return Util.sendMsg(player, MainColor + "Wyczyszczono tobie ekwipunek");
    }
}
