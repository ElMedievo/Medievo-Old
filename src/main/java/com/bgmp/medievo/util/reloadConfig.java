package com.bgmp.medievo.util;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.noperms;
import static com.bgmp.medievo.util.genericmessages.syntaxerror;

public class reloadConfig implements CommandExecutor {

    private final main plugin;

    public reloadConfig(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("reloadConfig") && sender.hasPermission("medievo.reload")) {
            if (args.length == 0) {
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Server config reloaded!");
            } else {
                sender.sendMessage(syntaxerror);
            }
        } else {
            sender.sendMessage(noperms);
        }
        return true;
    }
}
