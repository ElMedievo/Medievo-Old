package com.bgmp.medievo.clans.commands;

import com.bgmp.medievo.main;
import com.bgmp.medievo.util.queues;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.genericmessages.syntaxerror;
import static com.bgmp.medievo.util.queues.invitequeue;

public class invite implements CommandExecutor {

    private final main plugin;

    public invite(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("invite") && sender instanceof Player) {
            if (args.length == 1) {
                Player player = (Player) sender;
                String uuid = player.getUniqueId().toString();
                Player invited = Bukkit.getServer().getPlayer(args[0]);
                if (invited != null) {
                    String inviteduuid = invited.getUniqueId().toString();
                    if (!(plugin.getConfig().getString("Players." + uuid + ".clan").equals("neutral"))) {
                        if (plugin.getConfig().getString("Players." + inviteduuid + ".clan").equals("neutral")) {
                            if (!(invitequeue.containsKey(inviteduuid))) {
                                String clan = plugin.getConfig().getString("Players." + uuid + ".clan");
                                invitequeue.put(inviteduuid, clan);
                                player.sendMessage(ChatColor.GREEN + "Invitation sent to " + ChatColor.RESET + invited.getDisplayName());
                                invited.sendMessage(ChatColor.GREEN + "You have been invited to join " + ChatColor.AQUA + clan + ChatColor.GREEN + "!");
                                invited.sendMessage("You may " + ChatColor.GREEN + "/accept " + ChatColor.RESET + "or " + ChatColor.RED + "/cancel " + ChatColor.RESET + "this request");
                            } else {
                                player.sendMessage(ChatColor.RED + "That player is being invited to another clan");
                            }
                        } else {
                            player.sendMessage(invited.getDisplayName() + ChatColor.RED + " already is in a clan");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You currently have no clan!");
                    }
                } else {
                    player.sendMessage(ChatColor.DARK_AQUA + args[0] + ChatColor.RED + " appears to be an invalid nick or is a player who is currently offline");
                }
            } else {
                sender.sendMessage(syntaxerror);
            }
        } else {
            sender.sendMessage(noconsole);
        }
        return true;
    }
}
