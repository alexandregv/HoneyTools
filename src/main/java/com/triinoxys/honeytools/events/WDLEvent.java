package com.triinoxys.honeytools.events;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import com.triinoxys.Main;


public class WDLEvent implements PluginMessageListener{
    
    public void onPluginMessageReceived(String channel, Player p, byte[] data){
        if(channel.equals("WDL|INIT")){
            
            if(!p.hasPermission("downloadworld.*") && !p.hasPermission("downloadworld." + p.getWorld().getName())){
                String uuid = p.getUniqueId().toString();
                
                Main.getInstance().getLogger().warning(p.getName() + " s'est connecte avec le mod WorldDownloader.");
                
                if(Main.lang.containsKey(uuid) && Main.lang.get(uuid).equalsIgnoreCase("en"))
                    p.kickPlayer("Downloading our maps isn't allowed.");
                else{
                    Main.lang.put(uuid, "fr");
                    
                    p.kickPlayer("Il est interdit de télécharger nos maps.");
                }
            }
        }
    }
    
}
