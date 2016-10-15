package com.triinoxys.emptyorganic.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import com.triinoxys.emptyorganic.commands.EmptyOrganic;

public class PlayerInterract implements Listener{
	
    @EventHandler
	public void onInterract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		
		if(EmptyOrganic.players.contains(p)){
	        if (!e.getHand().equals(EquipmentSlot.OFF_HAND)){
	            if (p.getInventory().getItemInMainHand().getType() == Material.STICK){
	                if(e.getAction() == Action.LEFT_CLICK_BLOCK){
	                    if(EmptyOrganic.l1.containsKey(p))
	                        EmptyOrganic.l1.remove(p);
	                    
	                    EmptyOrganic.l1.put(p, e.getClickedBlock().getLocation());
	                    p.sendMessage(EmptyOrganic.prefix + "§aPoint 1 créé");
	                    
	                    e.setCancelled(true);
	                }
	                else if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
	                    if (EmptyOrganic.l2.containsKey(p))
	                        EmptyOrganic.l2.remove(p);
	                    
	                    EmptyOrganic.l2.put(p, e.getClickedBlock().getLocation());
	                    p.sendMessage(EmptyOrganic.prefix + "§aPoint 2 créé");
	                    e.setCancelled(true);
	                }
	            }
	        }
		}
	}

}
