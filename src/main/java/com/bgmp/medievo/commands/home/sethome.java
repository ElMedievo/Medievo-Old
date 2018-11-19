package com.bgmp.medievo.commands.home;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.noperms;
import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.queues.locationqueue;

public class sethome implements CommandExecutor {

    private final main plugin;

    public sethome(main instance) { plugin = instance; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("sethome")) {
                if (sender.hasPermission("medievo.sethome")) {
                    Player player = (Player) sender;
                    if (args.length == 0) {
                            player.setBedSpawnLocation(player.getLocation(), true);

                            locationqueue.put(player, player.getBedSpawnLocation());

                            player.sendMessage(ChatColor.GREEN + "Your home has been set!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Command Syntax: /sethome");
                    }
                } else {
                    sender.sendMessage(noperms);
                }
            }
        } else {
            sender.sendMessage(noconsole);
        }

        return true;
    }


}
