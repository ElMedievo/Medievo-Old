package com.bgmp.medievo.commands.chat;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.genericmessages.noperms;

public class adminchat implements CommandExecutor {

    public static ArrayList<String> toggleadminchat = new ArrayList<>();

    private final main plugin;

    public adminchat(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("adminchat")) {
                Player msgsender = (Player) sender;

                String uuidmsgsender = msgsender.getUniqueId().toString();

                if (msgsender.hasPermission("medievo.adminchat")) {
                    if (args.length > 0) {

                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            builder.append(args[i] + " ");
                        }
                        String message = builder.toString();

                        for (Player on : Bukkit.getServer().getOnlinePlayers()) {
                            if (on.hasPermission("medievo.adminchat")) {
                                on.sendMessage(ChatColor.GRAY + "[" + ChatColor.RESET + "" + ChatColor.GOLD + "A" + ChatColor.RESET + "" + ChatColor.GRAY
                                + "] " + ChatColor.RESET + msgsender.getDisplayName() + ": " + ChatColor.WHITE + message);
                            }
                        }
                    } else if (args.length == 0) {
                        if(toggleadminchat.contains(uuidmsgsender)) { // if player already has toggled
                            toggleadminchat.remove(uuidmsgsender);
                            msgsender.sendMessage(ChatColor.YELLOW + "Your default chat is no longer admin chat.");
                        } else {
                            toggleadminchat.add(uuidmsgsender);
                            msgsender.sendMessage(ChatColor.YELLOW + "Your default chat is now admin chat.");
                        }
                    } else {
                        msgsender.sendMessage(ChatColor.RED + "Command Syntax: /adminchat {message}");
                    }
                } else {
                    msgsender.sendMessage(noperms);
                }
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(noconsole);
        }

        return true;
    }
}