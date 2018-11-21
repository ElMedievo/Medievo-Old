package com.bgmp.medievo.clans.commands;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.genericmessages.syntaxerror;

public class create implements CommandExecutor {

    private final main plugin;

    public create(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("create") && sender instanceof Player) {
            Player player = (Player) sender;
            String uuid = player.getUniqueId().toString();
            if (args.length == 1) {
                String clan = args[0];
                if (plugin.getConfig().getString("Players." + uuid + ".clan").equals("neutral")) {
                    if (!plugin.getConfig().isConfigurationSection("Players.Clans." + clan) || plugin.getConfig().getString("Players.Clans." + clan).equals("undefined")) {
                        plugin.getConfig().set("Players." + uuid + ".clan", clan);
                        player.sendMessage(ChatColor.GREEN + "You have successfully created " + ChatColor.AQUA + clan + ChatColor.GREEN + "!");
                        Bukkit.broadcastMessage(ChatColor.YELLOW + "The clan " + ChatColor.AQUA + clan + ChatColor.YELLOW + " has been founded by " + player.getDisplayName());
                        plugin.getConfig().set("Players.Clans." + clan, "defined");
                        plugin.saveConfig();
                    } else {
                        sender.sendMessage(ChatColor.AQUA + clan + ChatColor.RED + " already exists!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You are already a member of the " + ChatColor.AQUA + plugin.getConfig().getString("Players." + uuid + ".clan") + ChatColor.RED +  " clan!");
                    player.sendMessage(ChatColor.RED + "You may leave your actual clan by using the command: " + ChatColor.AQUA + "/leave");
                }
            } else {
                player.sendMessage(syntaxerror);
            }
        } else {
            sender.sendMessage(noconsole);
        }
        return true;
    }
}