package com.triinoxys.honeytools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class ClearchatCmd implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        for(int i=0; i < 150; i++) Bukkit.broadcastMessage("");
        return true;
    }
    
}
