package com.triinoxys.emptyorganichh;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.triinoxys.emptyorganichh.commands.EmptyOrganic;

public class Set extends Cuboid {
	private Player p;
	private ItemStack is;
	private int numberBlocks;
	private HashMap<Location, ItemStack> undo = new HashMap<Location, ItemStack>();

	public Set(Cuboid other, Player p, ItemStack is){
		super(other);
		this.p = p;
		this.is = is;
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
		
		p.sendMessage(EmptyOrganic.prefix + "§aSet effectué §7(" + numberBlocks + " blocs placés)");
	}

}
