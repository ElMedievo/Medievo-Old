package medievo.medievo.events;

import medievo.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static medievo.medievo.main.tpaqueue;

public class onPlayerDisconnect implements Listener {

    private final main plugin;

    public onPlayerDisconnect(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (tpaqueue.containsValue(player)) {
            tpaqueue.values().remove(player);
        }

        event.setQuitMessage(ChatColor.RED + "« " + event.getPlayer().getDisplayName());
    }
}
