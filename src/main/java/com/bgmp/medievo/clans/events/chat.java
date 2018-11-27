package com.bgmp.medievo.clans.events;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static com.bgmp.medievo.commands.chat.adminchat.toggleadminchat;
import static com.bgmp.medievo.clans.commands.clanchat.toggleclanchat;
import static com.bgmp.medievo.commands.punishments.mute.mutelist;

public class chat implements Listener {

    private final main plugin;

    public chat(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        String msg = event.getMessage();
        String clan = plugin.getConfig().getString("Players." + uuid + ".clan");

        if (mutelist.get(uuid)) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "Your message was not sent! You are muted!");
            player.sendMessage(ChatColor.GOLD + "✖" + ChatColor.RESET + "[" + ChatColor.AQUA + clan + ChatColor.RESET + "] " + ChatColor.RESET + player.getDisplayName() + ChatColor.RESET + ": " + msg);
            return;
        }

        if(event.getMessage().startsWith(ChatColor.GRAY + "[[")) { event.setCancelled(true); }

        if(!toggleadminchat.contains(uuid)) {

            if(!toggleclanchat.contains(uuid)) {

                // send general message
                if (plugin.getConfig().getString("Players." + uuid + ".clan").equals("neutral")) {
                    Bukkit.broadcastMessage("[" + ChatColor.GRAY + "Neutral" + ChatColor.RESET + "] " + ChatColor.RESET + player.getDisplayName() + ChatColor.RESET + ": " + msg);
                } else {
                    Bukkit.broadcastMessage("[" + ChatColor.AQUA + clan + ChatColor.RESET + "] " + ChatColor.RESET + player.getDisplayName() + ChatColor.RESET + ": " + msg);
                }

            } else { // send clan chat message
                String playerclan = plugin.getConfig().getString("Players." + uuid + ".clan");
                    if (!playerclan.equalsIgnoreCase("neutral")) { // if player is in a clan

                        for(Player on : Bukkit.getServer().getOnlinePlayers()){

                            String onuuid = on.getUniqueId().toString();
                            String onclan = plugin.getConfig().getString("Players." + onuuid + ".clan");

                            if(onclan.equals(playerclan) && !uuid.equals(onuuid)) {
                                on.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "CLAN" + ChatColor.GRAY + "] " + ChatColor.RESET + player.getDisplayName()
                                        + ChatColor.WHITE + ": " + ChatColor.RESET + msg);
                            }
                        }

                        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "CLAN" + ChatColor.GRAY + "] " + ChatColor.RESET + player.getDisplayName()
                                + ChatColor.WHITE + ": " + ChatColor.RESET + msg);

                    } else {player.sendMessage(ChatColor.RED + "You are not in a clan yet!");}
            }

        } else {
            for(Player on : Bukkit.getServer().getOnlinePlayers()) {
                if(on.hasPermission("medievo.adminchat")) {
                    on.sendMessage(ChatColor.GRAY + "[" + ChatColor.RESET + "" + ChatColor.GOLD + "A" + ChatColor.RESET + "" + ChatColor.GRAY
                            + "] " + ChatColor.RESET + player.getDisplayName() + ChatColor.WHITE + ": " + ChatColor.RESET + msg);
                }
            }
        }
    }
}
