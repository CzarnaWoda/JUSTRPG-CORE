package core.commands;

import core.interfaces.Colors;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.justrpg.api.commands.Command;
import pl.justrpg.api.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command implements Colors {

    public ListCommand() {
        super("list", "", "/list", "core.cmd.list", new String[] {"online"});
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        List<String> playersOnline = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(pl -> {
            playersOnline.add(pl.getName());
        });
        return Util.sendMsg(commandSender, MainColor + "Aktualni gracze online: " + ImportantColor + playersOnline.toString().replace("[", "").replace( "]", ""));
    }
}
