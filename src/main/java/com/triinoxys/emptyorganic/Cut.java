package com.triinoxys.emptyorganic;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.triinoxys.emptyorganic.commands.EmptyOrganic;

public class Cut extends Cuboid {
	private Player p;
	private int numberBlocks;
	private HashMap<Location, ItemStack> undo = new HashMap<Location, ItemStack>();

	public Cut(Cuboid cuboid, Player p){
		super(cuboid);
		this.p = p;
	}
	
	@SuppressWarnings("deprecation")
	public void cut(){
		for (Block b : getBlocks()){
			ItemStack is = new ItemStack(b.getType(), 1, b.getData());
			if (b.getType() == Material.AIR) continue;
			b.setType(Material.AIR);
			undo.put(b.getLocation(), is);
			numberBlocks++;
		}
		new Undo(p, undo);
		p.sendMessage(EmptyOrganic.prefix + "§aCut effectué §7(" + numberBlocks + " blocs cut)");
	}

}
