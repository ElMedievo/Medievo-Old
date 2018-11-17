package com.bgmp.medievo.clans.commands;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.genericmessages.syntaxerror;
import static com.bgmp.medievo.util.queues.invitequeue;

public class accept implements CommandExecutor {

    private final main plugin;

    public accept(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("accept") && sender instanceof Player) {
            Player player = (Player) sender;
            String uuid = player.getUniqueId().toString();
            if (args.length == 0) {
                if (invitequeue.containsKey(uuid)) {
                    String clan = invitequeue.get(uuid);
                    plugin.getConfig().set("Players." + uuid + ".clan", clan);
                    player.sendMessage(ChatColor.GREEN + "You are now a member of " + ChatColor.AQUA + clan + ChatColor.GREEN + "!");
                    plugin.saveConfig();
                } else {
                    player.sendMessage(ChatColor.RED + "You have not been invited to join any clan");
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
