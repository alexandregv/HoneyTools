package com.triinoxys.honeytools;

import org.bukkit.configuration.file.FileConfiguration;
import com.triinoxys.Main;


public class ConfigManager{

    private static FileConfiguration config;

    public static void loadConfig(){
        Main.getInstance().reloadConfig();
        config = Main.getInstance().getConfig();
    }

    public static void saveConfig(){
        config = Main.getInstance().getConfig();
        Main.getInstance().saveConfig();
    }
    
    public static FileConfiguration getConfig(){
        config = Main.getInstance().getConfig();
        return config;
    }
    
    public static String getString(String path){
        loadConfig();
        return config.getString(path);
    }
}
