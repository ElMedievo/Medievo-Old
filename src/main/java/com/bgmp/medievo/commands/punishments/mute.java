package com.bgmp.medievo.commands.punishments;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static com.bgmp.medievo.util.genericmessages.invalidplayer;
import static com.bgmp.medievo.util.genericmessages.noperms;

public class mute implements CommandExecutor {

    private final main plugin;

    public mute(main instance) {
        plugin = instance;
    }

    public static HashMap<String, Boolean> mutelist = new HashMap<String, Boolean>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("mute") && sender.hasPermission("medievo.mute")) {
            if (args.length == 1) {
                Player issuer = (Player) sender;
                String issueruuid = issuer.getUniqueId().toString();
                Player muted = Bukkit.getServer().getPlayer(args[0]);
                if (!(muted == null)) {
                    if (!(mutelist.containsKey(issueruuid)) || !(mutelist.get(issueruuid))) {
                        mutelist.put(issueruuid, true);
                        muted.sendMessage(ChatColor.RED + "You have been muted!");
                    } else {
                        mutelist.put(issueruuid, false);
                        muted.sendMessage(ChatColor.GREEN + "You have been unmuted!");
                    }
                } else {
                    sender.sendMessage(invalidplayer);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Command syntax: /mute {player}");
            }
        } else {
            sender.sendMessage(noperms);
        }
        return true;
    }
}
