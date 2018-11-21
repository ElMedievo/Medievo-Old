package com.bgmp.medievo.commands.tpa;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.genericmessages.syntaxerror;
import static com.bgmp.medievo.util.queues.tpaqueue;

public class tpaccept implements CommandExecutor {

    private final main plugin;

    public tpaccept(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tpaccept")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    Player player = (Player) sender;
                    String reciever = player.getUniqueId().toString();
                    if (tpaqueue.get(reciever) != null) {
                        Location destiny = player.getLocation();
                        Player issuer = tpaqueue.get(reciever);
                        issuer.teleport(destiny);
                        issuer.sendMessage(ChatColor.GOLD + "» " + ChatColor.GREEN + "You have been teleported!");
                        player.sendMessage(ChatColor.GOLD + "» " + ChatColor.GREEN + "Request accepted!");
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
