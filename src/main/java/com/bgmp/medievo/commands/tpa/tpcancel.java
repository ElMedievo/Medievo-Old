package com.bgmp.medievo.commands.tpa;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.genericmessages.syntaxerror;
import static com.bgmp.medievo.util.queues.tpaqueue;

public class tpcancel implements CommandExecutor {

    private final main plugin;

    public tpcancel(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tpcancel")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    Player player = (Player) sender;
                    String reciever = player.getUniqueId().toString();
                    if (tpaqueue.get(reciever) != null) {
                        String negated = tpaqueue.get(reciever).getPlayer().getName();
                        Player negatedp = Bukkit.getServer().getPlayer(negated);
                        negatedp.sendMessage(ChatColor.GOLD + "» " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.RED + " ha rechazado tu solicitud");
                        sender.sendMessage(ChatColor.GOLD + "» " + ChatColor.RED + "Teleport request from " + ChatColor.YELLOW + negatedp.getDisplayName() + ChatColor.RED + " has been rejected!");
                        tpaqueue.remove(reciever);
                    } else {
                        sender.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "You don't have any pendant requests");
                    }
                } else {
                    sender.sendMessage(syntaxerror);
                }
            } else {
                sender.sendMessage(noconsole);
            }
        }
        return true;
    }




}
