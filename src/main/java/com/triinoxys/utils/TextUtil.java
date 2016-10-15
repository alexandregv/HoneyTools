package com.triinoxys.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class TextUtil{
    
    public static String replaceVars(String str, Player p){
        str = ChatColor.translateAlternateColorCodes('&', str)
                .replaceAll("%player%",    p.getName())
                .replaceAll("%world%",     p.getWorld().getName())
                .replaceAll("%online-pl%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replaceAll("%max-pl%",    String.valueOf(Bukkit.getMaxPlayers()))
                ;
        
        return str;
    }
    
    public static void send(Player p, String msg){
        p.sendMessage(replaceVars(msg, p));
    }
    
}
