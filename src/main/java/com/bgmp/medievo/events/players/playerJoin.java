package com.bgmp.medievo.events.players;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class playerJoin implements Listener {

    private final main plugin;

    public playerJoin(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        if (!plugin.getConfig().isConfigurationSection("Players." + uuid)) {
            plugin.getConfig().set("Players." + uuid + ".name", player.getName());
            plugin.getConfig().set("Players." + uuid + ".clan", "neutral");
            plugin.getConfig().set("Players." + uuid + ".rank", "default");
            plugin.getConfig().set("Players." + uuid + ".rtp", true);
            plugin.saveConfig();
            player.setDisplayName(ChatColor.YELLOW + player.getName());
            player.setPlayerListName(ChatColor.YELLOW + player.getName());
            event.setJoinMessage(ChatColor.DARK_PURPLE + "Welcome " + ChatColor.YELLOW + player.getName() + ChatColor.DARK_PURPLE + " to the server!");
        } else {
            String rank = plugin.getConfig().getString("Players." + uuid + ".rank");
            switch (rank) {
                case "jrmod":
                    player.setDisplayName(ChatColor.LIGHT_PURPLE + "❖" + ChatColor.YELLOW + player.getName());
                    player.setPlayerListName(ChatColor.LIGHT_PURPLE + "❖" + ChatColor.YELLOW + player.getName());
                    break;
                case "mod":
                    player.setDisplayName(ChatColor.RED + "❖" + ChatColor.YELLOW + player.getName());
                    player.setPlayerListName(ChatColor.RED + "❖" + ChatColor.YELLOW + player.getName());
                    break;
                case "srmod":
                    player.setDisplayName(ChatColor.DARK_RED + "❖" + ChatColor.YELLOW + player.getName());
                    player.setPlayerListName(ChatColor.DARK_RED + "❖" + ChatColor.YELLOW + player.getName());
                    break;
                case "dev":
                    player.setDisplayName(ChatColor.DARK_PURPLE + "❖" + ChatColor.YELLOW + player.getName());
                    player.setPlayerListName(ChatColor.DARK_PURPLE + "❖" + ChatColor.YELLOW + player.getName());
                    break;
                case "admin":
                    player.setDisplayName(ChatColor.GOLD + "❖" + ChatColor.YELLOW + player.getName());
                    player.setPlayerListName(ChatColor.GOLD + "❖" + ChatColor.YELLOW + player.getName());
                    break;
                case "default":
                    player.setDisplayName(ChatColor.YELLOW + player.getName());
                    player.setPlayerListName(ChatColor.YELLOW + player.getName());
            }
            event.setJoinMessage(ChatColor.GOLD + "» " + ChatColor.RESET + player.getDisplayName() + ChatColor.GOLD + " has joined the game.");
        }
    }
}