package com.triinoxys.honeytools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


public class PlayerBreakBlockEvent implements Listener{
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(!e.getPlayer().hasPermission("build")) e.setCancelled(true);
    }
    
}
