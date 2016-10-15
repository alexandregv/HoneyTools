package com.triinoxys.honeytools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import com.triinoxys.Main;

public class BlockUpdateEvent implements Listener{

    public Main plugin;

    public BlockUpdateEvent(Main pl){
        this.plugin = pl;
    }

    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPhysicsEvent(BlockPhysicsEvent event){
        if(this.plugin.disabledWorlds.contains(event.getBlock().getWorld().getUID()))
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockFadeEvent(BlockFadeEvent event){
        if(this.plugin.disabledWorlds.contains(event.getBlock().getWorld().getUID()))
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockFormEvent(BlockFormEvent event){
        if(this.plugin.disabledWorlds.contains(event.getBlock().getWorld().getUID()))
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockGrowEvent(BlockGrowEvent event){
        if(this.plugin.disabledWorlds.contains(event.getBlock().getWorld().getUID()))
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeaves(LeavesDecayEvent event){
        if(this.plugin.disabledWorlds.contains(event.getBlock().getWorld().getUID()))
            event.setCancelled(true);
    }
    
}
