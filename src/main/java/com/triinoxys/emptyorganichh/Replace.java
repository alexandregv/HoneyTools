package com.triinoxys.emptyorganichh;

import java.util.HashMap;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.triinoxys.emptyorganichh.commands.EmptyOrganic;

public class Replace extends Cuboid {
	private Player p;
	private List<ItemStack> replace;
	private ItemStack replaceBlock;
	private int numberBlocks;
	private HashMap<Location, ItemStack> undo = new HashMap<Location, ItemStack>();

	public Replace(Cuboid cuboid, Player p, List<ItemStack> replace, ItemStack replaceBlock){
		super(cuboid);
		this.p = p;
		this.replace = replace;
		this.replaceBlock = replaceBlock;
	}
	
	@SuppressWarnings("deprecation")
	public void replace(){
		for(Block b : getBlocks()){
			ItemStack is = new ItemStack(b.getType(), 1, b.getData());
			if (!replace.contains(is)) continue;
			b.setType(replaceBlock.getType());
			b.setData(replaceBlock.getData().getData());
			undo.put(b.getLocation(), is);
			numberBlocks++;
		}
		
		new Undo(p, undo);
		
		p.sendMessage(EmptyOrganic.prefix + "§aRemplacement effectué §7(" + numberBlocks + " blocs remplacé)");
	}

}
