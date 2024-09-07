package core.managers;

import core.interfaces.Colors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.justrpg.api.util.Util;

public class ChatManager implements Colors {

    public boolean status;

    public ChatManager(){
        status = true;
    }

    public void clearChatForPlayer(Player p){
        int i = 0;
        while( i < 101){
            p.sendMessage("");
            i++;
        }
    }

    public void toggleChat(CommandSender who){
        status = !status;
        for(Player player : Bukkit.getOnlinePlayers()){
            clearChatForPlayer(player);
            Util.sendMsg(player, MainColor + "Chat zostal " + (status ? ChatColor.GREEN + "wlaczony" : ChatColor.DARK_RED + "wylaczony") + MainColor + " przez " + ImportantColor + who.getName().replace("CONSOLE", "KONSOLE"));
        }
    }

    public void clearChat(CommandSender who){
        for(Player player : Bukkit.getOnlinePlayers()){
            clearChatForPlayer(player);
            Util.sendMsg(player, MainColor + "Chat zostal wyczyszczony przez " + ImportantColor + who.getName().replace("CONSOLE", "KONSOLE"));
        }
    }
}
