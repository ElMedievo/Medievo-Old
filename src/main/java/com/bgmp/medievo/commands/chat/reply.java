package com.bgmp.medievo.commands.chat;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.queues.replyqueue;
import static org.bukkit.Bukkit.getServer;

public class reply implements CommandExecutor {

    private final main plugin;

    public reply(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("reply")) {

                Player replyingPlayer = (Player) sender;

                if (args.length > 0) {

                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < args.length; i++) {
                        builder.append(args[i] + " ");
                    }
                    String message = builder.toString();

                    String uuidReplyingPlayer = replyingPlayer.getUniqueId().toString();

                    if (replyqueue.get(uuidReplyingPlayer) != null) { // if sender previously received a message from someone
                        String uuidRepliedTo = replyqueue.get(uuidReplyingPlayer);
                        for(Player p : getServer().getOnlinePlayers()){
                            if(p.getUniqueId().equals(replyqueue.get(uuidReplyingPlayer))) {
                                Player repliedTo = p;
                                if (repliedTo.isOnline()) {

                                    repliedTo.sendMessage(ChatColor.GRAY + "[" + ChatColor.RESET + "" + ChatColor.AQUA + "PM" + ChatColor.RESET
                                            + "" + ChatColor.GRAY + "] From " + ChatColor.RESET +
                                            replyingPlayer.getDisplayName() + ChatColor.RESET + "" + ChatColor.GRAY + ": " + ChatColor.RESET + "" + ChatColor.WHITE + message);

                                    replyingPlayer.sendMessage(ChatColor.GRAY + "[" + ChatColor.RESET + "" + ChatColor.AQUA + "PM" + ChatColor.RESET
                                            + "" + ChatColor.GRAY + "] To " + ChatColor.RESET +
                                            repliedTo.getDisplayName() + ChatColor.RESET + "" + ChatColor.GRAY + ": " + ChatColor.RESET + "" + ChatColor.WHITE + message);

                                    replyqueue.put(uuidRepliedTo, uuidReplyingPlayer);
                                    // Let the player who is replied to now be able to reply as well
                                } else {
                                    replyingPlayer.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "Nobody to reply to!");
                                }
                            }
                        }
                    } else { // if player has not previously received a message from someone
                        replyingPlayer.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "Nobody to reply to!");
                    }
                } else {
                    replyingPlayer.sendMessage(ChatColor.RED + "Command Syntax: /reply {player} {message}");
                }
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(noconsole);
        }

        return true;
    }
}