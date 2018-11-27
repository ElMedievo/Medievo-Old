package com.bgmp.medievo.events.players;

import com.bgmp.medievo.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class playerRespawn implements Listener {

    private final main plugin;

    public playerRespawn(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void playerRespawn(PlayerRespawnEvent event) {
        Player target = event.getPlayer();
        String targetuuid = target.getUniqueId().toString();
        plugin.getConfig().set("Players." + targetuuid + ".rtp", true);
    }
}
