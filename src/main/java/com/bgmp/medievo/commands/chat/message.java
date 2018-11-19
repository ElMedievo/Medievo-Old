package com.bgmp.medievo.commands.chat;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static com.bgmp.medievo.util.genericmessages.noconsole;

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

                if (args.length > 1) {

                    Player msgreceiver = Bukkit.getServer().getPlayer(args[0]);

                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i] + " ");
                    }
                    String message = builder.toString();

                            msgreceiver.sendMessage(ChatColor.GRAY + "[" + ChatColor.RESET + "" + ChatColor.AQUA + "PM" + ChatColor.RESET
                                    + "" + ChatColor.GRAY + "] From " + ChatColor.RESET +
                                    msgsender.getDisplayName() + ChatColor.RESET + "" + ChatColor.GRAY + ": " + ChatColor.RESET + "" + ChatColor.WHITE + message);

                            msgsender.sendMessage(ChatColor.GRAY + "[" + ChatColor.RESET + "" + ChatColor.AQUA + "PM" + ChatColor.RESET
                                    + "" + ChatColor.GRAY + "] To " + ChatColor.RESET +
                                    msgreceiver.getDisplayName() + ChatColor.RESET + "" + ChatColor.GRAY + ": " + ChatColor.RESET + "" + ChatColor.WHITE + message);
                } else {
                    msgsender.sendMessage(ChatColor.RED + "Command Syntax: /message {player} {message}");
                }
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(noconsole);
        }

        return true;
    }
}