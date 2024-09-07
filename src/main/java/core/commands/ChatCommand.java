package core.commands;

import core.Main;
import core.interfaces.Colors;
import core.managers.ChatManager;
import core.settings.MessageConfig;
import org.bukkit.command.CommandSender;
import pl.justrpg.api.commands.Command;
import pl.justrpg.api.util.Util;

public class ChatCommand extends Command {

    public ChatCommand() {
        super("chat", "", "/chat <toggle/clear>", "core.cmd.chat", new String[0]);
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if(args.length < 1){
            return Util.sendMsg(commandSender, MessageConfig.MESSAGE_COMMAND_GETUSAGE.replace("{USAGE}", getUsage()));
        }
        if(args[0].equalsIgnoreCase("toggle")){
            Main.getChatManager().toggleChat(commandSender);
        }else if(args[0].equalsIgnoreCase("clear")){
            Main.getChatManager().clearChat(commandSender);
        }
        return false;
    }
}
