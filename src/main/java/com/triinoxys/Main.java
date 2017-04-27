package com.triinoxys;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;
import com.triinoxys.emptyorganic.commands.EmptyOrganic;
import com.triinoxys.emptyorganic.listeners.PlayerInterract;
import com.triinoxys.emptyorganic.listeners.PlayerQuit;
import com.triinoxys.honeytools.ConfigManager;
import com.triinoxys.honeytools.commands.BlockUpdatesCmd;
import com.triinoxys.honeytools.commands.ClearchatCmd;
import com.triinoxys.honeytools.commands.HHCmd;
import com.triinoxys.honeytools.commands.LangCmd;
import com.triinoxys.honeytools.events.BlockUpdateEvent;
import com.triinoxys.honeytools.events.ChatEvent;
import com.triinoxys.honeytools.events.JoinEvent;
import com.triinoxys.honeytools.events.LeaveEvent;
import com.triinoxys.honeytools.events.PlayerBreakBlockEvent;
import com.triinoxys.honeytools.events.WDLEvent;



public class Main extends JavaPlugin{
    
    private static Main plugin;
    public static Permission perms = null;
    public static Chat chat = null;
    
    private static FileConfiguration usersConfig;
    private File usersFile;
    
    private static String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "H" + ChatColor.RESET + ChatColor.GOLD + "oney" + ChatColor.DARK_RED + ChatColor.BOLD + "T" + ChatColor.RESET + ChatColor.GOLD + "ools" + ChatColor.DARK_GRAY + "] ";
    
    public ArrayList<UUID> disabledWorlds = new ArrayList<UUID>();
    public static HashMap<String, String> lang = new HashMap<String, String>();
    
    public static Scoreboard sb;
    public static Team admins, devs, sysadmins, builders, commmans, amis, visiteurs;
    
    
    @Override
    public void onEnable(){
        plugin = this;
        
        setupPermissions();
        setupChat();
        
      //--------------------[EmptyOrganic loading message]--------------------
        getServer().getPluginManager().registerEvents(new PlayerInterract(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getLogger().info("=============== EmptyOrganic ===============");
        getLogger().info("Original author: MrTheo95");
        getLogger().info("Forked by: TriiNoxYs");
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("============================================");
      //--------------------[EmptyOrganic loading message]--------------------
        
        //Config
        try{
            if(!getDataFolder().exists())
                getDataFolder().mkdir();
            
            usersFile = new File(getDataFolder(), "users.yml");
            if(!usersFile.exists()){
                getLogger().info("users.yml not found, creating it!");
                usersFile.createNewFile();
            }
            else getLogger().info("users.yml found, loading it!");
            
            usersConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "users.yml"));
            
            for(String user : usersConfig.getKeys(false)){
                String langStr = usersConfig.getString(user);
                lang.put(user, langStr);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        saveDefaultConfig(); 
        ConfigManager.loadConfig();
        
        
        saveResource("motd-FR.txt", false);
        saveResource("motd-EN.txt", false);
        
        //AntiWDL events
        getServer().getMessenger().registerIncomingPluginChannel(this, "WDL|INIT", new WDLEvent());
        getServer().getMessenger().registerOutgoingPluginChannel(this, "WDL|CONTROL");
        
        //HoneyTools events
        getServer().getPluginManager().registerEvents(new BlockUpdateEvent(this), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerBreakBlockEvent(), this);
        
        
        //HoneyTools commands
        getCommand("blockupdates").setExecutor(new BlockUpdatesCmd());
        getCommand("blockupdates").setTabCompleter(new BlockUpdatesCmd());
        
        getCommand("honeytools").setExecutor(new HHCmd());
        
        getCommand("clearchat").setExecutor(new ClearchatCmd());
        
        getCommand("lang").setExecutor(new LangCmd());
        getCommand("lang").setTabCompleter(new LangCmd());
        
        //EmptyOrganic commands
        getCommand("emptyorganic").setExecutor(new EmptyOrganic());
        getCommand("emptyorganic").setTabCompleter(new EmptyOrganic());
        
        //EmptyOrganic events
        getServer().getPluginManager().registerEvents(new PlayerInterract(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        
        
        //Scoreboard
        sb = Bukkit.getScoreboardManager().getNewScoreboard();
        
        admins = sb.registerNewTeam("A_admins");
        admins.setPrefix(ChatColor.translateAlternateColorCodes('&',    ConfigManager.getString("prefixes.admin")));
        admins.setSuffix("§f");
        admins.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
        
        devs = sb.registerNewTeam("B_devs");
        devs.setPrefix(ChatColor.translateAlternateColorCodes('&',      ConfigManager.getString("prefixes.dev")));
        devs.setSuffix("§f");
        devs.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
        
        sysadmins = sb.registerNewTeam("C_sysadmins");
        sysadmins.setPrefix(ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("prefixes.sysadmin")));
        sysadmins.setSuffix("§f");
        sysadmins.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
        
        commmans = sb.registerNewTeam("D_commmans");
        commmans.setPrefix(ChatColor.translateAlternateColorCodes('&',  ConfigManager.getString("prefixes.community-manager")));
        commmans.setSuffix("§f");
        commmans.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
        
        builders = sb.registerNewTeam("E_builders");
        builders.setPrefix(ChatColor.translateAlternateColorCodes('&',  ConfigManager.getString("prefixes.builder")));
        builders.setSuffix("§f");
        builders.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
        
        amis = sb.registerNewTeam("F_amis");
        amis.setPrefix(ChatColor.translateAlternateColorCodes('&',      ConfigManager.getString("prefixes.ami")));
        amis.setSuffix("§f");
        amis.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
        
        visiteurs = sb.registerNewTeam("Z_visiteurs");
        visiteurs.setPrefix(ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("prefixes.visiteur")));
        visiteurs.setSuffix("§f");
        visiteurs.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
        
        for(Player pls : Bukkit.getOnlinePlayers()){
            doScoreboard(pls);
        }
    }
    
    @Override
    public void onDisable(){
        for(String langStr : lang.keySet())
            usersConfig.set(langStr, lang.get(langStr));
        
        try{
            usersConfig.save(usersFile);
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    private boolean setupPermissions(){
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
    
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
    
    public static Main getInstance(){
        return plugin;
    }
    
    public static String getPrefix(){
        return prefix;
    }
    
    public static FileConfiguration getUsersConfig(){
        return usersConfig;
    }
    
    @SuppressWarnings("deprecation")
    private static void doScoreboard(Player p){
        if     (Main.perms.getPrimaryGroup(p).equalsIgnoreCase("Admin"))            Main.admins   .addPlayer(p);
        else if(Main.perms.getPrimaryGroup(p).equalsIgnoreCase("Dev"))              Main.devs     .addPlayer(p);
        else if(Main.perms.getPrimaryGroup(p).equalsIgnoreCase("SysAdmin"))         Main.sysadmins.addPlayer(p);
        else if(Main.perms.getPrimaryGroup(p).equalsIgnoreCase("Builder"))          Main.builders .addPlayer(p);
        else if(Main.perms.getPrimaryGroup(p).equalsIgnoreCase("Visiteur"))         Main.visiteurs.addPlayer(p);
        else if(Main.perms.getPrimaryGroup(p).equalsIgnoreCase("CommunityManager")) Main.commmans .addPlayer(p);
        else if(Main.perms.getPrimaryGroup(p).equalsIgnoreCase("Ami"))              Main.amis     .addPlayer(p);
        else p.setScoreboard(Main.sb);
    }
    
}
