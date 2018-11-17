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

public class cancel implements CommandExecutor {

    private final main plugin;

    public cancel(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("cancel") && sender instanceof Player) {
            Player player = (Player) sender;
            String uuid = player.getUniqueId().toString();
            if (args.length == 0) {
                if (invitequeue.containsKey(uuid)) {
                    String clan = invitequeue.get(uuid);
                    player.sendMessage(ChatColor.RED + "You have cancelled the clan invitation for " + ChatColor.AQUA + clan);
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
