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

public class tphome implements CommandExecutor {

    private final main plugin;

    public tphome(main instance) { plugin = instance; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("tphome")) {
                if (sender.hasPermission("medievo.tphome")) {
                    Player player = (Player) sender;
                    if (args.length == 0) {
                        if (locationqueue.containsKey(player)) {

                            player.teleport(locationqueue.get(player));

                            player.sendMessage(ChatColor.GREEN + "Welcome home!");
                        } else {
                            player.sendMessage(ChatColor.RED + "You haven't set your home yet!");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Command Syntax: /tphome");
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
