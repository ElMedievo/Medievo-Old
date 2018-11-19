package com.bgmp.medievo.events;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class playerLeave implements Listener {

    private final main plugin;

    public playerLeave(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.RED + "« " + ChatColor.RESET + player.getDisplayName() + ChatColor.GOLD + " left.");
    }
}
