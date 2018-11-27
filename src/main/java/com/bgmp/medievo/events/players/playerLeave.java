package com.bgmp.medievo.events.players;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

//import static com.bgmp.medievo.util.queues.replyqueue;

public class playerLeave implements Listener {

    private final main plugin;

    public playerLeave(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        event.setQuitMessage(ChatColor.RED + "« " + ChatColor.RESET + player.getDisplayName() + ChatColor.GOLD + " left.");

        // Remove the player as a value from the replyqueue
        // Whoever is trying to reply to the player will no longer be able to
        //String keyToRemove = replyqueue.inverse().get(uuid);
        //replyqueue.remove(keyToRemove);
        //replyqueue.remove(uuid);
    }
}
