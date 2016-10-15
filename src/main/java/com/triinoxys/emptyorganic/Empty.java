package com.triinoxys.emptyorganic;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.triinoxys.Main;
import com.triinoxys.emptyorganic.commands.EmptyOrganic;

public class Empty {
	private Player player;
	private String uuid;
	private Cuboid cuboid;
	private ListBlock listBlock;
	
	public Empty(Player player, Cuboid cuboid, ListBlock listBlocks){
		this.player = player;
		this.cuboid = cuboid;
		this.listBlock = listBlocks;
		this.uuid = player.getUniqueId().toString();
	}
	
	private boolean isSlab(Material material){
		switch (material) {
		case STEP:      return true;
		case WOOD_STEP: return true;
		default:        return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void start() {
		for(Block b : cuboid.getBlocks()){
			if (b.getRelative(BlockFace.UP).getType()    == Material.AIR) continue;
			if (b.getRelative(BlockFace.DOWN).getType()  == Material.AIR) continue;
			if (b.getRelative(BlockFace.NORTH).getType() == Material.AIR) continue;
			if (b.getRelative(BlockFace.SOUTH).getType() == Material.AIR) continue;
			if (b.getRelative(BlockFace.EAST).getType()  == Material.AIR) continue;
			if (b.getRelative(BlockFace.WEST).getType()  == Material.AIR) continue;
			
			if (isSlab(b.getRelative(BlockFace.UP).getType()))            continue;
			if (isSlab(b.getRelative(BlockFace.DOWN).getType()))          continue;
			if (isSlab(b.getRelative(BlockFace.NORTH).getType()))         continue;
			if (isSlab(b.getRelative(BlockFace.SOUTH).getType()))         continue;
			if (isSlab(b.getRelative(BlockFace.EAST).getType()))          continue;
			if (isSlab(b.getRelative(BlockFace.WEST).getType()))          continue;
			
			listBlock.blocks.add(b);
		}
		
		HashMap<Location, ItemStack> blocksSave = new HashMap<Location, ItemStack>();
		
		for(Block b : listBlock.blocks){
			blocksSave.put(b.getLocation(), new ItemStack(b.getType(), 1, b.getData()));
			b.setType(Material.AIR);
		}
		
		new Undo(player, blocksSave);
		
		if(Main.lang.containsKey(uuid) && Main.lang.get(uuid).equalsIgnoreCase("en")){
		    player.sendMessage(EmptyOrganic.prefix + "§aOrganic emptied. §7(" + listBlock.blocks.size() + " blocks deleted)");
        }
        else{
            Main.lang.put(uuid, "fr");
            
            player.sendMessage(EmptyOrganic.prefix + "§aOrganic vidé. §7(" + listBlock.blocks.size() + " blocs supprimés)");
        }
		
		listBlock.blocks.clear();
	}

}
