package com.triinoxys.emptyorganic;

import java.util.HashMap;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.triinoxys.Main;
import com.triinoxys.emptyorganic.commands.EmptyOrganic;

public class Replace extends Cuboid {
	private Player p;
	private String uuid;
	private List<ItemStack> replace;
	private ItemStack replaceBlock;
	private int numberBlocks;
	private HashMap<Location, ItemStack> undo = new HashMap<Location, ItemStack>();

	public Replace(Cuboid cuboid, Player p, List<ItemStack> replace, ItemStack replaceBlock){
		super(cuboid);
		this.p = p;
		this.replace = replace;
		this.replaceBlock = replaceBlock;
		this.uuid = p.getUniqueId().toString();
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
		
		if(Main.lang.containsKey(uuid) && Main.lang.get(uuid).equalsIgnoreCase("en")){
		    p.sendMessage(EmptyOrganic.prefix + "§a" + numberBlocks +" blocks replaced.");
        }
        else{
            Main.lang.put(uuid, "fr");
            
            p.sendMessage(EmptyOrganic.prefix + "§a" + numberBlocks +" blocs remplacés.");
        }
	}

}
