package com.bgmp.medievo.commands.chat;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.noconsole;
//import static com.bgmp.medievo.util.queues.replyqueue;

public class message implements CommandExecutor {

    private final main plugin;

    public message(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("message")) {
                Player msgsender = (Player) sender;

                String uuidmsgsender = msgsender.getUniqueId().toString();

                if (args.length > 1) {

                    Boolean isOnline = false;

                    for (Player receiver : Bukkit.getServer().getOnlinePlayers()) {
                        if (receiver.getName().equalsIgnoreCase(args[0])) { // if player is online
                                StringBuilder builder = new StringBuilder();
                                for (int i = 1; i < args.length; i++) {
                                    builder.append(args[i] + " ");
                                }

                                String message = builder.toString();

                                receiver.sendMessage(ChatColor.GRAY + "[" + ChatColor.RESET + "" + ChatColor.AQUA + "PM" + ChatColor.RESET
                                        + "" + ChatColor.GRAY + "] From " + ChatColor.RESET +
                                        msgsender.getDisplayName() + ChatColor.RESET + "" + ChatColor.GRAY + ": " + ChatColor.RESET + "" + ChatColor.WHITE + message);

                                msgsender.sendMessage(ChatColor.GRAY + "[" + ChatColor.RESET + "" + ChatColor.AQUA + "PM" + ChatColor.RESET + "" + ChatColor.GRAY + "] To " + ChatColor.RESET +
                                        receiver.getDisplayName() + ChatColor.RESET + "" + ChatColor.GRAY + ": " + ChatColor.RESET + "" + ChatColor.WHITE + message);

                                isOnline = true;

                                String uuidreceiver = receiver.getUniqueId().toString();

                                // Map receiver to msgsender in the reply queue, so that receiver may send a message to msgsender in the future
                                //replyqueue.put(uuidreceiver, uuidmsgsender); // Map receiver to its msgsender
                        }
                    }

                    if(!isOnline) msgsender.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "Nobody to send a message to!");

                } else msgsender.sendMessage(ChatColor.RED + "Command Syntax: /message {player} {message}");
            }
        } else
            Bukkit.getConsoleSender().sendMessage(noconsole);
        return true;
    }
}