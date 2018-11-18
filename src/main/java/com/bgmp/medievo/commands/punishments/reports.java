package com.bgmp.medievo.commands.punishments;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class reports implements CommandExecutor {

    private final main plugin;

    public reports(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("report")) {
                if (args.length > 0) {
                    Player player = (Player) sender;
                    String arg0 = args[0];
                    Player reported = Bukkit.getServer().getPlayer(arg0);
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i] + " ");
                    }
                    String msg = builder.toString();
                    if (args.length > 1) {
                        if (cmd.getName().equalsIgnoreCase("report")) {
                            if (reported != null) {
                                for (Player on : Bukkit.getServer().getOnlinePlayers()) {
                                    if (on.hasPermission("event.reports")) {
                                        String reportMessage = ("[Rep] " + player.getName() + " ➔ " + reported.getName() + ": " + msg);
                                        World w = on.getWorld();
                                        on.sendMessage("[" + ChatColor.GOLD + "Rep" + ChatColor.RESET + "] " + player.getDisplayName() + " ➔ " + reported.getDisplayName() + ": " + msg);
                                        w.playSound(on.getLocation(), Sound.BLOCK_ANVIL_LAND, 10, 1);
                                    }
                                }
                            } else {
                                sender.sendMessage(ChatColor.RED + "Command Syntax: /report {player} {reason}");
                                return true;
                            }
                            sender.sendMessage(ChatColor.GREEN + "Your report has been sent and will soon be reviewed by the server staff.");
                        } else {
                            sender.sendMessage(ChatColor.RED + "Command Syntax: /report {player} {reason}");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Command Syntax: /report {player} {reason}");
                    }
                }
            } else {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "You must be a player to /report");
            }
        }
        return true;
    }
}
