package com.triinoxys.emptyorganic;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.triinoxys.Main;
import com.triinoxys.emptyorganic.commands.EmptyOrganic;

public class Set extends Cuboid {
	private Player p;
	private String uuid;
	private ItemStack is;
	private int numberBlocks;
	private HashMap<Location, ItemStack> undo = new HashMap<Location, ItemStack>();

	public Set(Cuboid other, Player p, ItemStack is){
		super(other);
		this.p = p;
		this.is = is;
		this.uuid = p.getUniqueId().toString();
	}
	
	@SuppressWarnings("deprecation")
	public void set(){
		for(Block b : getBlocks()){
			ItemStack old = new ItemStack(b.getType(), 1, b.getData());
			b.setType(is.getType());
			b.setData(is.getData().getData());
			undo.put(b.getLocation(), old);
			numberBlocks++;
		}
		
		new Undo(p, undo);
		
		if(Main.lang.containsKey(uuid) && Main.lang.get(uuid).equalsIgnoreCase("en")){
		    p.sendMessage(EmptyOrganic.prefix + "§a" + numberBlocks + " blocks placed.");
        }
        else{
            Main.lang.put(uuid, "fr");
            
            p.sendMessage(EmptyOrganic.prefix + "§a" + numberBlocks + " blocs placés.");
        }
	}

}
