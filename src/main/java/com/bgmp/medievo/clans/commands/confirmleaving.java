package com.bgmp.medievo.clans.commands;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.queues.confirmqueue;

public class confirmleaving implements CommandExecutor {

    private final main plugin;

    public confirmleaving(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String uuid = player.getUniqueId().toString();
            String clan = plugin.getConfig().getString("Players." + uuid + ".clan");
            if (confirmqueue.get(uuid)) {
                if (command.getName().equalsIgnoreCase("confirm")) {
                    plugin.getConfig().set("Players." + uuid + ".clan", "neutral");
                    player.sendMessage(ChatColor.RED + "You have successfully left " + ChatColor.AQUA + clan + ChatColor.RED);
                    plugin.getConfig().set("Players.Clans." + clan, "undefined");
                    plugin.saveConfig();
                } else if (command.getName().equalsIgnoreCase("deny")) {
                    player.sendMessage(ChatColor.RED + "You have not left " + ChatColor.AQUA + clan);
                }
                confirmqueue.remove(uuid);
            } else {
                player.sendMessage(ChatColor.RED + "You have not requested to leave any clan");
            }
        } else {
            sender.sendMessage(noconsole);
        }
        return true;
    }
}
