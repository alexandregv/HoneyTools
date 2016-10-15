package com.triinoxys.honeytools.commands;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import com.triinoxys.Main;
import com.triinoxys.honeytools.ConfigManager;
import com.triinoxys.utils.TextUtil;
import com.triinoxys.utils.TitleUtil;


public class LangCmd implements CommandExecutor, TabCompleter{
    
  //TODO: Add tab completer
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                IChatBaseComponent comp = ChatSerializer.a("[\"\",{\"text\":\"[\",\"color\":\"dark_gray\"},{\"text\":\"H\",\"color\":\"dark_red\",\"bold\":true},{\"text\":\"oney\",\"color\":\"gold\"},{\"text\":\"H\",\"color\":\"dark_red\",\"bold\":true},{\"text\":\"ive\",\"color\":\"gold\"},{\"text\":\"] \",\"color\":\"dark_gray\"},{\"text\":\"Select the language you want:\",\"color\":\"gold\"},{\"text\":\"\n - Français\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/lang fr\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Cliquez ici pour mettre en Français.\"}]}},\"color\":\"none\"},{\"text\":\"\n - English\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/lang en\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click here to set language on English.\"}]}},\"color\":\"none\"}]");
                PacketPlayOutChat packet = new PacketPlayOutChat(comp);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
            }
            else{
                if(args[0].equalsIgnoreCase("fr")){
                    Main.lang.put(p.getUniqueId().toString(), "fr");
                    p.sendMessage("La langue est désormais: Français.");
                    TitleUtil.sendHeaderAndFooter(p,   TextUtil.replaceVars(ConfigManager.getConfig().getString("fr.join.tab-header"),    p), TextUtil.replaceVars(ConfigManager.getConfig().getString("fr.join.tab-footer"), p));
                }
                else if(args[0].equalsIgnoreCase("en")){
                    TitleUtil.sendHeaderAndFooter(p,   TextUtil.replaceVars(ConfigManager.getConfig().getString("en.join.tab-header"), p), TextUtil.replaceVars(ConfigManager.getConfig().getString("en.join.tab-footer"), p));
                    Main.lang.put(p.getUniqueId().toString(), "en");
                    p.sendMessage("Language is now: English.");
                }
                else p.sendMessage(ChatColor.RED + "Usage: /lang [fr | en]"); 
            }  
        }
        else sender.sendMessage(ChatColor.DARK_RED + "Vous devez etre un joueur pour exeucter cette commande.");
        return true;
    }
    
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> argsList = new ArrayList<String>();
        argsList.add("fr");
        argsList.add("en");
        
        if(args.length == 0) return argsList;
        else{
            List<String> pArgs = new ArrayList<String>();
            
            for(String arg : argsList)
                if(arg.toLowerCase().startsWith(args[0].toLowerCase()))
                    pArgs.add(arg);
            
            return pArgs;
        }
    }
    
}
