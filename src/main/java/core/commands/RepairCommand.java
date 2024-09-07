package core.commands;

import core.interfaces.Colors;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.justrpg.api.commands.PlayerCommand;
import pl.justrpg.api.util.Util;

import java.util.Arrays;

public class RepairCommand extends PlayerCommand implements Colors {

    public RepairCommand() {
        super("repair", "", "/repair [all]", "core.cmd.repair", new String[0]);
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(args.length == 1){
            for(ItemStack is : player.getInventory().getContents()){
                if(is != null){
                    is.setDurability((byte)0);
                }
            }
            for(ItemStack is : player.getInventory().getArmorContents()){
                if(is != null){
                    is.setDurability((byte)0);
                }
            }
            return Util.sendMsg(player, MainColor + "Naprawiono twoj caly " + ImportantColor + "ekwipunek");
        }
        player.getItemInHand().setDurability((byte)0);
        return Util.sendMsg(player, MainColor + "Naprawiono twoj item w " + ImportantColor + "rece");
    }
}
