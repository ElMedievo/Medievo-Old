package com.bgmp.medievo.commands.tpa;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.syntaxerror;
import static com.bgmp.medievo.util.queues.tpaqueue;

public class tpa implements CommandExecutor {

    private final main plugin;

    public tpa(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tpa")) {
            if (sender instanceof Player) {
                if (args.length == 1) {
                    Player player1 = (Player) sender;
                    Player player2 = Bukkit.getServer().getPlayer(args[0]);
                    if (player2 != null) {
                        String reciever = player2.getUniqueId().toString();
                        if (tpaqueue.get(reciever) == null) {
                            tpaqueue.put(reciever, player1);
                            player1.sendMessage(ChatColor.GOLD + "» " + ChatColor.AQUA + "Teleport request sent to " + ChatColor.YELLOW + player2.getDisplayName());
                            player2.sendMessage(ChatColor.GOLD + "» " + ChatColor.YELLOW + player1.getDisplayName() + ChatColor.AQUA + " Is requesting to teleport to you \n  " + ChatColor.GREEN + "/tpaccept" + ChatColor.AQUA + " or " + ChatColor.RED + "/tpcancel");
                            return true;
                        } else {
                            sender.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "The player already has a pendant teleport request");
                        }
                    } else {
                        sender.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "Player not found");
                    }
                } else {
                    sender.sendMessage(syntaxerror);
                }
            }
        }
        return true;
    }
}
