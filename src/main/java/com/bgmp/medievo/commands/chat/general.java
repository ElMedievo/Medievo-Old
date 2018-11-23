package com.bgmp.medievo.commands.chat;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class general implements CommandExecutor {

    private final main plugin;

    public general(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("general")) {
                Player player = (Player) sender;
                String uuid = player.getUniqueId().toString();

                if (args.length > 0) {

                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < args.length; i++) {
                        builder.append(args[i] + " ");
                    }
                    String message = builder.toString();

                    String clan = plugin.getConfig().getString("Players." + uuid + ".clan");
                    Bukkit.broadcastMessage("[" + ChatColor.AQUA + clan + ChatColor.RESET + "] " + ChatColor.RESET + player.getDisplayName() + ChatColor.RESET + ": " + message);

                }

            }
        }
        return true;
    }

}
