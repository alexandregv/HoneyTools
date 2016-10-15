package com.triinoxys.honeytools.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import com.triinoxys.Main;


public class BlockUpdatesCmd implements CommandExecutor, TabCompleter{
    
    private String prefix;
    
    public BlockUpdatesCmd(){
        this.prefix = Main.getPrefix();
    }
    
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if((sender instanceof Player)){
            Player p = (Player) sender;
            String uuid = p.getUniqueId().toString();
            UUID world = p.getWorld().getUID();
            
            if(Main.lang.containsKey(uuid)){
                if(Main.lang.get(uuid).equalsIgnoreCase("en")){ //EN
                    if(args.length == 0){
                        if(Main.getInstance().disabledWorlds.contains(world))
                            sender.sendMessage(prefix + "§6Block updates are §odisabled§r§6 in §l" + p.getWorld().getName() + "§r§6 !");
                        else
                            sender.sendMessage(prefix + "§6Block updates are §oenabled§r§6 in §l" + p.getWorld().getName() + "§r§6 !");
                    }
                    else if(args.length >= 1){
                        if(args[0].equalsIgnoreCase("on")){
                            if(Main.getInstance().disabledWorlds.contains(world)){ //world est OFF
                                Main.getInstance().disabledWorlds.remove(world);
                                sender.sendMessage(prefix + "§6You §oenabled§r§6 block updates in §l" + p.getWorld().getName() + "§r§6 !");
                            }
                            else{ //world est deja ON
                                sender.sendMessage(prefix + "§6Block updates are already §oenabled§r§6 in §l" + p.getWorld().getName() + "§r§6 !");
                            }
                        }
                        else if(args[0].equalsIgnoreCase("off")){
                            if(Main.getInstance().disabledWorlds.contains(world)){ //world est deja OFF
                                sender.sendMessage(prefix + "§6Block updates are already §odisabled§r§6 in §l" + p.getWorld().getName() + "§r§6 !");
                            }
                            else{ //world est ON
                                Main.getInstance().disabledWorlds.add(world);
                                sender.sendMessage(prefix + "§6You §odisabled§r§6 block updates in §l" + p.getWorld().getName() + "§r§6 !");
                            }
                        }
                        else sender.sendMessage("§cUsage: /bu [on | off]");
                    }
                }
                else{ //FR
                    if(args.length == 0){
                        if(Main.getInstance().disabledWorlds.contains(world))
                            sender.sendMessage(prefix + "§6Les updates de blocs sont §odésactivées§r§6 dans §l" + p.getWorld().getName() + "§r§6 !");
                        else
                            sender.sendMessage(prefix + "§6Les updates de blocs sont §oactivées§r§6 dans §l" + p.getWorld().getName() + "§r§6 !");
                    }
                    else if(args.length >= 1){
                        if(args[0].equalsIgnoreCase("on")){
                            if(Main.getInstance().disabledWorlds.contains(world)){ //world est OFF
                                Main.getInstance().disabledWorlds.remove(world);
                                sender.sendMessage(prefix + "§6Vous avez §oactivé§r§6 les updates de blocs dans §l" + p.getWorld().getName() + "§r§6 !");
                            }
                            else{ //world est deja ON
                                sender.sendMessage(prefix + "§6Updates de blocs déjà §oactivées§r§6 dans §l" + p.getWorld().getName() + "§r§6 !");
                            }
                        }
                        else if(args[0].equalsIgnoreCase("off")){
                            if(Main.getInstance().disabledWorlds.contains(world)){ //world est deja OFF
                                sender.sendMessage(prefix + "§6Updates de blocs déjà §odésactivées§r§6 dans §l" + p.getWorld().getName() + "§r§6 !");
                            }
                            else{ //world est ON
                                Main.getInstance().disabledWorlds.add(world);
                                sender.sendMessage(prefix + "§6Vous avez §odésactivé§r§6 les updates de blocs dans §l" + p.getWorld().getName() + "§r§6 !");
                            }
                        }
                        else sender.sendMessage("§cUsage: /bu [on | off]");
                    }  
                }
            }
            else{
                Main.lang.put(uuid, "fr");
                
                if(args.length == 0){
                    if(Main.getInstance().disabledWorlds.contains(world))
                        sender.sendMessage(prefix + "§6Les updates de blocs sont §odésactivées§r§6 dans §l" + p.getWorld().getName() + "§r§6 !");
                    else
                        sender.sendMessage(prefix + "§6Les updates de blocs sont §oactivées§r§6 dans §l" + p.getWorld().getName() + "§r§6 !");
                }
                else if(args.length >= 1){
                    if(args[0].equalsIgnoreCase("on")){
                        if(Main.getInstance().disabledWorlds.contains(world)){ //world est OFF
                            Main.getInstance().disabledWorlds.remove(world);
                            sender.sendMessage(prefix + "§6Vous avez §oactivé§r§6 les updates de blocs dans §l" + p.getWorld().getName() + "§r§6 !");
                        }
                        else{ //world est deja ON
                            sender.sendMessage(prefix + "§6Updates de blocs déjà §oactivées§r§6 dans §l" + p.getWorld().getName() + "§r§6 !");
                        }
                    }
                    else if(args[0].equalsIgnoreCase("off")){
                        if(Main.getInstance().disabledWorlds.contains(world)){ //world est deja OFF
                            sender.sendMessage(prefix + "§6Updates de blocs déjà §odésactivées§r§6 dans §l" + p.getWorld().getName() + "§r§6 !");
                        }
                        else{ //world est ON
                            Main.getInstance().disabledWorlds.add(world);
                            sender.sendMessage(prefix + "§6Vous avez §odésactivé§r§6 les updates de blocs dans §l" + p.getWorld().getName() + "§r§6 !");
                        }
                    }
                    else sender.sendMessage("§cUsage: /bu [on | off]");
                } 
            }
        }
        else sender.sendMessage("Vous devez etre un joueur pour executer cette commande.");
        return false;
    }
    
    
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> argsList = new ArrayList<String>();
        argsList.add("on");
        argsList.add("off");
        
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
