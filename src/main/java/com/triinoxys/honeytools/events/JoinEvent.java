package com.triinoxys.honeytools.events;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.triinoxys.Main;
import com.triinoxys.honeytools.ConfigManager;
import com.triinoxys.utils.TextUtil;
import com.triinoxys.utils.TitleUtil;


public class JoinEvent implements Listener{
    
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) throws IOException{
        Player p = e.getPlayer();
        String uuid = p.getUniqueId().toString();
        
        e.setJoinMessage(null);
        Bukkit.getConsoleSender().sendMessage(TextUtil.replaceVars(ConfigManager.getString("fr.join.message"), p));
        
        
        if(Main.lang.containsKey(uuid) && (Main.lang.get(uuid).equalsIgnoreCase("en") || Main.lang.get(uuid).equalsIgnoreCase("fr"))){
            String lp = Main.lang.get(uuid).toLowerCase();
            
            TitleUtil.sendTitle(p, 20, 40, 20, TextUtil.replaceVars(ConfigManager.getString(lp + ".join.title"),      p), TextUtil.replaceVars(ConfigManager.getString(lp + ".join.subtitle"), p));
            TitleUtil.sendHeaderAndFooter(p,   TextUtil.replaceVars(ConfigManager.getString(lp + ".join.tab-header"), p), TextUtil.replaceVars(ConfigManager.getString(lp + ".join.tab-footer"), p));
            TitleUtil.sendActionBar(p,         TextUtil.replaceVars(ConfigManager.getString(lp + ".join.actionbar"),  p));
        }
        else{
            Main.lang.put(uuid, "fr");
            
            //Title en FR
            TitleUtil.sendTitle(p, 20, 40, 20, TextUtil.replaceVars(ConfigManager.getString("fr.join.title"),         p), TextUtil.replaceVars(ConfigManager.getString("fr.join.subtitle"), p));
            TitleUtil.sendHeaderAndFooter(p,   TextUtil.replaceVars(ConfigManager.getString("fr.join.tab-header"),    p), TextUtil.replaceVars(ConfigManager.getString("fr.join.tab-footer"), p));
            TitleUtil.sendActionBar(p,         TextUtil.replaceVars(ConfigManager.getString("fr.join.actionbar"),     p));
            
            //Texte choix langue
            IChatBaseComponent comp   = ChatSerializer.a("[\"\",{\"text\":\"[\",\"color\":\"dark_gray\"},{\"text\":\"H\",\"color\":\"dark_red\",\"bold\":true},{\"text\":\"oney\",\"color\":\"gold\"},{\"text\":\"H\",\"color\":\"dark_red\",\"bold\":true},{\"text\":\"ive\",\"color\":\"gold\"},{\"text\":\"] \",\"color\":\"dark_gray\"},{\"text\":\"Please select a language by clicking the one you want or by typing\",\"color\":\"gold\"},{\"text\":\" /lang\",\"color\":\"gold\",\"bold\":true,\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/hh lang\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to enter the command\"}]}}},{\"text\":\"\n - Français\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/lang fr\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Cliquez ici pour mettre en Français\"}]}},\"color\":\"none\",\"bold\":false},{\"text\":\"\n - English\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/hh lang en\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click here to set language on English.\"}]}}}]");
            PacketPlayOutChat  packet = new PacketPlayOutChat(comp);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        }
        
        for(Player pls : Bukkit.getOnlinePlayers()){
            String pu = pls.getUniqueId().toString();
            if(Main.lang.containsKey(pu) && (Main.lang.get(pu).equalsIgnoreCase("en") || Main.lang.get(pu).equalsIgnoreCase("fr"))){
                String lp = Main.lang.get(pu).toLowerCase();
                
                pls.sendMessage(TextUtil.replaceVars(ConfigManager.getString(lp + ".join.message"), p));
            }
            else{
                Main.lang.put(pu, "fr");
                
                pls.sendMessage(TextUtil.replaceVars(ConfigManager.getString("fr.join.message"), p));
            }
        }
        
        sendMOTD(p);
        
        doScoreboard(p);
    }
    
    
    private static void sendMOTD(Player p) throws IOException{
        String uuid = p.getUniqueId().toString();
        BufferedReader br;
        
        if(Main.lang.containsKey(uuid) && Main.lang.get(uuid).equalsIgnoreCase("en")) br = new BufferedReader(new FileReader(new File(Main.getInstance().getDataFolder(), "motd-EN.txt")));
        else br = new BufferedReader(new FileReader(new File(Main.getInstance().getDataFolder(), "motd-FR.txt")));
        
        try{
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            
            p.sendMessage(TextUtil.replaceVars(sb.toString(), p));
        }finally{
            br.close();
        }
    }
    
    @SuppressWarnings("deprecation")
    private static void doScoreboard(Player p){
        if     (Main.perms.getPrimaryGroup(p).equalsIgnoreCase("Admin"))    Main.admins   .addPlayer(p);
        else if(Main.perms.getPrimaryGroup(p).equalsIgnoreCase("Dev"))      Main.devs     .addPlayer(p);
        else if(Main.perms.getPrimaryGroup(p).equalsIgnoreCase("SysAdmin")) Main.sysadmins.addPlayer(p);
        else if(Main.perms.getPrimaryGroup(p).equalsIgnoreCase("Builder"))  Main.builders .addPlayer(p);
        else if(Main.perms.getPrimaryGroup(p).equalsIgnoreCase("Visiteur")) Main.visiteurs.addPlayer(p);
        else                                                                Main.visiteurs.addPlayer(p);
        
        p.setScoreboard(Main.sb);
    }
    
}