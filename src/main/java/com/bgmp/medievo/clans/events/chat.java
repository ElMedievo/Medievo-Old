package com.bgmp.medievo.clans.events;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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

        if (plugin.getConfig().getString("Players." +  uuid + ".clan").equals("neutral")) {
            Bukkit.broadcastMessage("[" + ChatColor.GRAY + "Neutral" + ChatColor.RESET + "] " + ChatColor.RESET + player.getDisplayName() + ChatColor.RESET + ": " + msg);
        } else {
            String clan = plugin.getConfig().getString("Players." + uuid + ".clan");
            Bukkit.broadcastMessage("[" + ChatColor.AQUA + clan + ChatColor.RESET + "] " + ChatColor.RESET + player.getDisplayName() + ChatColor.RESET + ": " + msg);
        }
    }
}
