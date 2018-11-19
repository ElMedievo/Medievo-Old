package com.bgmp.medievo.commands.admin;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.genericmessages.noperms;
import static com.bgmp.medievo.util.genericmessages.syntaxerror;

public class broadcast implements CommandExecutor {

    private final main plugin;

    public broadcast(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("broadcast") && sender.hasPermission("medievo.broadcast")) {
            if(sender instanceof Player){
                Player player = (Player) sender;
                if (args.length == 0) {
                    player.sendMessage(syntaxerror);
                } else {
                    String brstr = "";
                    for (int i = 0; i < args.length; i++) {
                        brstr += " " + args[i];
                    }
                    Bukkit.broadcastMessage(ChatColor.RED + "[Broadcast]" + brstr);
                }
            } else {
                sender.sendMessage(noconsole);
            }
        } else {
            sender.sendMessage(noperms);
        }
        return true;
    }
}
