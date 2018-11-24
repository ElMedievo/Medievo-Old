package com.bgmp.medievo.clans.commands;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class clanchat implements CommandExecutor {

    public static ArrayList<String> toggleclanchat = new ArrayList<>();

    private final main plugin;

    public clanchat(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("clanchat") && sender instanceof Player) {
            Player player = (Player) sender;
            String uuid = player.getUniqueId().toString();

            if (args.length > 0) {

                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    builder.append(args[i] + " ");
                }
                String message = builder.toString();

                String playerclan = plugin.getConfig().getString("Players." + uuid + ".clan");

                if (!playerclan.equalsIgnoreCase("neutral")) { // if player is in a clan

                    for (Player on : Bukkit.getServer().getOnlinePlayers()){

                        String onuuid = on.getUniqueId().toString();
                        String onclan = plugin.getConfig().getString("Players." + onuuid + ".clan");

                        if (onclan.equals(playerclan) && !uuid.equals(onuuid)) {
                            on.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "CLAN" + ChatColor.GRAY + "] " + ChatColor.RESET + player.getDisplayName()
                                    + ChatColor.WHITE + ": " + ChatColor.RESET + message);
                        }
                    }

                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "CLAN" + ChatColor.GRAY + "] " + ChatColor.RESET + player.getDisplayName()
                            + ChatColor.WHITE + ": " + ChatColor.RESET + message);

                } else {player.sendMessage(ChatColor.RED + "You are not in a clan yet!");}

            } else if (args.length == 0) {
                if (toggleclanchat.contains(uuid)) { // if player already has toggled
                    toggleclanchat.remove(uuid);
                    player.sendMessage(ChatColor.GREEN + "Your default chat is no longer clan chat.");
                } else {
                    toggleclanchat.add(uuid);
                    player.sendMessage(ChatColor.GREEN + "Your default chat is now clan chat.");
                }
            }

        }
        return true;
    }
}
