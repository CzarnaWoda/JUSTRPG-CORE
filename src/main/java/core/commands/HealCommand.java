package core.commands;

import core.interfaces.Colors;
import core.settings.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.justrpg.api.commands.Command;
import pl.justrpg.api.util.Util;

public class HealCommand extends Command implements Colors {

    public HealCommand() {
        super("heal", "", "/heal [gracz]", "core.cmd.heal", new String[0]);
    }

    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (args.length == 0) {
                p.setHealth(p.getMaxHealth());
                p.setFoodLevel(20);
                p.setFireTicks(0);
                return Util.sendMsg(p, MainColor + "Zostales uleczony");
            }
            if (Bukkit.getPlayer(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);
                target.setHealth(target.getMaxHealth());
                target.setFoodLevel(20);
                target.setFireTicks(0);
                Util.sendMsg(target, MainColor + "Zostales uleczony przez " + ImportantColor + p.getName() + MainColor + "!");
                return Util.sendMsg(p, MainColor + "Uleczyles gracza " + ImportantColor + target.getName() + MainColor + "!");
            }
        } else {
            if (Bukkit.getPlayer(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);
                target.setHealth(target.getMaxHealth());
                target.setFoodLevel(20);
                target.setFireTicks(0);
                Util.sendMsg(target, MainColor + "Zostales uleczony przez " + ImportantColor + commandSender.getName().replace("CONSOLE", "KONSOLE") + MainColor + "!");
                return Util.sendMsg(commandSender, MainColor + "Uleczyles gracza " + ImportantColor + target.getName() + MainColor + "!");
            } else {
                return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_UNKNOWNPLAYER);
            }
        }
        return false;
    }
}
