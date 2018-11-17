package com.bgmp.medievo.clans.events;

import com.bgmp.medievo.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class join implements Listener {

    private final main plugin;

    public join(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String uuid = event.getPlayer().getUniqueId().toString();
        plugin.getConfig().set("Players." + uuid + ".clan", "neutral");
    }
}
