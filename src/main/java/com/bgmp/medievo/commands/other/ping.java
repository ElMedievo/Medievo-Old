package com.bgmp.medievo.commands.other;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ping implements CommandExecutor {

    private final main plugin;

    public ping(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("ping")) {
                Player player = (Player) sender;

                player.sendMessage(ChatColor.YELLOW + "Pong ;)");
            }
        }
        return true;
    }
}
