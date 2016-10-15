package com.triinoxys.emptyorganichh.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import com.triinoxys.emptyorganichh.Undo;
import com.triinoxys.emptyorganichh.commands.EmptyOrganic;

public class PlayerQuit implements Listener{
	
	@EventHandler
	public void quit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		EmptyOrganic.clearSelection(p);
		if(Undo.undos.containsKey(p))
			Undo.undos.remove(p);
	}

}
