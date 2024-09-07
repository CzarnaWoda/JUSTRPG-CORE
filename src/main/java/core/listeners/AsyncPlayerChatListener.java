package core.listeners;

import core.Main;
import core.interfaces.Colors;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.justrpg.api.util.Util;

public class AsyncPlayerChatListener implements Listener, Colors {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(!Main.getChatManager().status){
            if(!e.getPlayer().hasPermission("core.chatting")){
                e.setCancelled(true);
                Util.sendMsg(e.getPlayer(), ChatColor.RED + "Nie mozesz pisac na wylaczonym chacie!");
                return;
            }
        }
    }
}
