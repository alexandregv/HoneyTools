package com.triinoxys.honeytools.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import com.triinoxys.Main;
import com.triinoxys.honeytools.ConfigManager;
import com.triinoxys.utils.TextUtil;


public class LeaveEvent implements Listener{
    
    @EventHandler
    public void PlayerLeave(PlayerQuitEvent e){
      Player p = e.getPlayer();
      
      e.setQuitMessage(null);
      Bukkit.getConsoleSender().sendMessage(TextUtil.replaceVars(ConfigManager.getString("fr.leave.message"), p));
      
      for(Player pls : Bukkit.getOnlinePlayers()){
          String pu = pls.getUniqueId().toString();
          if(Main.lang.containsKey(pu) && (Main.lang.get(pu).equalsIgnoreCase("en") || Main.lang.get(pu).equalsIgnoreCase("fr"))){
              String lp = Main.lang.get(pu).toLowerCase();
              
              pls.sendMessage(TextUtil.replaceVars(ConfigManager.getString(lp + ".leave.message"), p));
          }
          else{
              Main.lang.put(pu, "fr");
              
              pls.sendMessage(TextUtil.replaceVars(ConfigManager.getString("fr.leave.message"), p));
          }
      }
    }
    
}