package com.bgmp.medievo.clans.commands;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.genericmessages.syntaxerror;
import static com.bgmp.medievo.util.queues.confirmqueue;

public class leave implements CommandExecutor {

    private final main plugin;

    public leave(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("leave") && sender instanceof Player) {
            Player player = (Player) sender;
            String uuid = player.getUniqueId().toString();
            if (args.length == 0) {
                if (!(plugin.getConfig().getString("Players." + uuid + ".clan").equals("neutral"))) {
                    String clan = plugin.getConfig().getString("Players." + uuid + ".clan");
                    player.sendMessage(ChatColor.RED + "You are about to leave " + ChatColor.AQUA + clan + ChatColor.RED + "!");
                    player.sendMessage(ChatColor.GRAY + "You may still " + ChatColor.GREEN + "/deny" + ChatColor.GRAY + " or " + ChatColor.RED + "/confirm" + ChatColor.GRAY + " this action");
                    confirmqueue.put(uuid, true);
                } else {
                    player.sendMessage(ChatColor.RED + "You are not part of any clan yet!");
                }
            } else {
                player.sendMessage(syntaxerror);
            }
        } else {
            sender.sendMessage(noconsole);
        }
        return true;
    }
}
