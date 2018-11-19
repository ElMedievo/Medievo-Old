package com.bgmp.medievo.commands.admin;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class heal implements CommandExecutor {

    private final main plugin;

    public heal(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("heal") && sender instanceof Player) {

            Player player = (Player) sender;

            if(player.hasPermission("medievo.heal")) {

                // Arg 0 = first word entered after cmd, Arg 1 is 2nd word...
                int length = args.length;

                // if there are no arguments
                if (length == 0) {
                    player.setHealth(20.0);
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.GREEN + "You've been healed!");
                }

                // if there's 1 argument
                else if (length == 1) {

                    boolean playerFound = false;

                    for (Player playerToBeHealed : Bukkit.getServer().getOnlinePlayers()) { // returns a player array
                        if (playerToBeHealed.getName().equalsIgnoreCase(args[0])) {
                            playerToBeHealed.setHealth(20.0); // give full health
                            playerToBeHealed.setFoodLevel(20); // give full food bar
                            playerToBeHealed.sendMessage(ChatColor.GREEN + "You've been healed by " + player.getName() + "!");
                            player.sendMessage(ChatColor.GREEN + playerToBeHealed.getName() + " has been successfully healed!");
                            playerFound = true;
                            break; //stop searching for players in server
                        }
                    }

                    if (!playerFound) player.sendMessage(ChatColor.RED + "Player was not found!");

                }

                // if there are more than one arguments
                else player.sendMessage(ChatColor.RED + "Incorrect arguments!");
            }

        }

        return true;
    }

}
