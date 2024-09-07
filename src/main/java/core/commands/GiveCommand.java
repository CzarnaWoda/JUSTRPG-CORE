package core.commands;

import core.Main;
import core.interfaces.Colors;
import core.settings.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.justrpg.api.commands.Command;
import pl.justrpg.api.util.Util;

public class GiveCommand extends Command implements Colors {

    public GiveCommand() {
        super("give", "", "/give [gracz] <item:[data]> <amount>", "core.cmd.give", new String[0]);
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if(commandSender instanceof Player){
            if(args.length < 2){
                return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_GETUSAGE.replace("{USAGE}", getUsage()));
            }
            if(args.length == 2){
                String[] datas = args[0].split(":");
                Material m = Material.getMaterial(datas[0]);
                byte data = 0;
                int amount = Integer.parseInt(args[1]);
                if(datas.length > 1){
                    data = Byte.valueOf(datas[1]);
                }
                if(m == null){
                    return Util.sendMsg(commandSender, MainColor + "Taki przedmiot o takiej nazwie nie istnieje!");
                }
                ItemStack is = new ItemStack(m, amount, data);
                ((Player)commandSender).getInventory().addItem(is);
                return Util.sendMsg(commandSender, MainColor + "Otrzymales przedmiot " + ImportantColor + m.name() + ":" + data + "/" + amount);
            }else if(args.length == 3){
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null){
                    return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
                }
                String[] datas = args[1].split(":");
                Material m = Material.getMaterial(datas[0]);
                byte data = 0;
                int amount = Integer.parseInt(args[2]);
                if(datas.length > 1){
                    data = Byte.valueOf(datas[1]);
                }
                if(m == null){
                    return Util.sendMsg(commandSender, MainColor + "Taki przedmiot o takiej nazwie nie istnieje!");
                }
                ItemStack is = new ItemStack(m, amount, data);
                target.getInventory().addItem(is);
                Util.sendMsg(commandSender, MainColor + "Dales przedmiot " + ImportantColor + m.name() + ":" + data + "/" + amount + MainColor + " dla gracza " + ImportantColor + target.getName());
                return Util.sendMsg(target, MainColor + "Otrzymales przedmiot " + ImportantColor + m.name() + ":" + data + "/" + amount);
            }
        }else{
            if(args.length < 3){
                return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_GETUSAGE.replace("{USAGE}", getUsage()));
            }
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null){
                return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
            }
            String[] datas = args[1].split(":");
            Material m = Material.getMaterial(datas[0]);
            byte data = 0;
            int amount = Integer.parseInt(args[2]);
            if(datas.length > 1){
                data = Byte.valueOf(datas[1]);
            }
            if(m == null){
                return Util.sendMsg(commandSender, MainColor + "Taki przedmiot o takiej nazwie nie istnieje!");
            }
            ItemStack is = new ItemStack(m, amount, data);
            target.getInventory().addItem(is);
            Util.sendMsg(commandSender, MainColor + "Dales przedmiot " + ImportantColor + m.name() + ":" + data + "/" + amount + MainColor + " dla gracza " + ImportantColor + target.getName());
            return Util.sendMsg(target, MainColor + "Otrzymales przedmiot " + ImportantColor + m.name() + ":" + data + "/" + amount);
        }
        return false;
    }
}
