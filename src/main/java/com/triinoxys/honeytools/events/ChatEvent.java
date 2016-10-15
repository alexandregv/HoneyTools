package com.triinoxys.honeytools.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import com.triinoxys.Main;
import com.triinoxys.honeytools.ConfigManager;
import com.triinoxys.utils.TextUtil;


public class ChatEvent implements Listener{
    
    @EventHandler
    public void onTalk(AsyncPlayerChatEvent e){
        e.setFormat(ChatColor.translateAlternateColorCodes('&', (TextUtil.replaceVars(ConfigManager.getString("chat-format").replaceAll("%group%", Main.chat.getGroupPrefix("world", Main.perms.getPrimaryGroup(e.getPlayer()))).replaceAll("%message%", e.getMessage()), e.getPlayer()))));
    }
    
}
