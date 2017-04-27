package com.triinoxys.honeytools.commands;

import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import com.triinoxys.Main;


public class HHCmd implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player){
            Player p = (Player) sender;
            String uuid = p.getUniqueId().toString();
            
            sender.sendMessage(ChatColor.DARK_GRAY + "-------------[" + ChatColor.DARK_RED + ChatColor.BOLD + "H" + ChatColor.RESET + ChatColor.GOLD + "oney" + ChatColor.DARK_RED + ChatColor.BOLD + "H" + ChatColor.RESET + ChatColor.GOLD + "ive" + ChatColor.DARK_GRAY + "]-------------");
            
            
            IChatBaseComponent ts =      ChatSerializer.a("[\"\",{\"text\":\"Teamspeak: \",\"color\":\"gold\",\"bold\":true},{\"text\":\"ts.honey-hive.fr\",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"ts.honey-hive.fr\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Cliquez pour copier l'adresse\"}]}}}]");
            IChatBaseComponent site =    ChatSerializer.a("[\"\",{\"text\":\"Site: \",\"color\":\"gold\",\"bold\":true},{\"text\":\"honey-hive.fr\",\"color\":\"white\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://www.honey-hive.fr/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Cliquez pour ouvrir le site\"}]}}}]");
            IChatBaseComponent twitter = ChatSerializer.a("[\"\",{\"text\":\"Twitter: \",\"color\":\"gold\",\"bold\":true},{\"text\":\"@HoneyHofficiel\",\"color\":\"white\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://twitter.com/HoneyHofficiel/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Cliquez pour ouvrir Twitter\"}]}}}]");
            IChatBaseComponent pmc =     ChatSerializer.a("[\"\",{\"text\":\"PlanetMinecraft: \",\"color\":\"gold\",\"bold\":true},{\"text\":\"HoneyHive\",\"color\":\"white\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://www.planetminecraft.com/member/honey_hive/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Cliquez pour ouvrir PlanetMinecraft\"}]}}}]");
            
            if(Main.lang.containsKey(uuid) && Main.lang.get(uuid).equalsIgnoreCase("en")){
                ts      = ChatSerializer.a("[\"\",{\"text\":\"Teamspeak: \",\"color\":\"gold\",\"bold\":true},{\"text\":\"ts.honey-hive.fr\",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"ts1.privateheberg.fr:10025\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"ts.honey-hive.fr\"}]}}}]");
                site    = ChatSerializer.a("[\"\",{\"text\":\"Site: \",\"color\":\"gold\",\"bold\":true},{\"text\":\"honey-hive.fr\",\"color\":\"white\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://www.honey-hive.fr/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to open the site\"}]}}}]");
                twitter = ChatSerializer.a("[\"\",{\"text\":\"Twitter: \",\"color\":\"gold\",\"bold\":true},{\"text\":\"@HoneyHofficiel\",\"color\":\"white\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://twitter.com/HoneyHofficiel/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to open Twitter\"}]}}}]");
                pmc     = ChatSerializer.a("[\"\",{\"text\":\"PlanetMinecraft: \",\"color\":\"gold\",\"bold\":true},{\"text\":\"HoneyHive\",\"color\":\"white\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://www.planetminecraft.com/member/honey_hive/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to open PlanetMinecraft\"}]}}}]");
            }
            else
                Main.lang.put(uuid, "fr");
            
            PacketPlayOutChat pTS = new PacketPlayOutChat(ts);
            PacketPlayOutChat pSITE = new PacketPlayOutChat(site);
            PacketPlayOutChat pTWITTER = new PacketPlayOutChat(twitter);
            PacketPlayOutChat pPMC = new PacketPlayOutChat(pmc);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(pTS);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(pSITE);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(pTWITTER);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(pPMC);
            
            sender.sendMessage(ChatColor.DARK_GRAY + "-------------[" + ChatColor.DARK_RED + ChatColor.BOLD + "H" + ChatColor.RESET + ChatColor.GOLD + "oney" + ChatColor.DARK_RED + ChatColor.BOLD + "H" + ChatColor.RESET + ChatColor.GOLD + "ive" + ChatColor.DARK_GRAY + "]-------------");
        }
        else sender.sendMessage(ChatColor.RED + "Vous devez etre un joueur pour executer cette commande.");
        return true;
    }
    
}