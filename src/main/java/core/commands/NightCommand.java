package core.commands;

import core.interfaces.Colors;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import pl.justrpg.api.commands.Command;
import pl.justrpg.api.util.Util;

public class NightCommand extends Command implements Colors {

    public NightCommand() {
        super("night", "", "/night", "core.cmd.night", new String[0]);
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        for(World w : Bukkit.getWorlds()){
            w.setTime(20000);
            w.setThundering(false);
            w.setStorm(false);
        }
        return Util.sendMsg(commandSender, MainColor + "Czas zostal zmieniony na noc");
    }
}
