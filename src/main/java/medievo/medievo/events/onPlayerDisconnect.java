package medievo.medievo.events;

import medievo.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onPlayerDisconnect implements Listener {

    private final main plugin;

    public onPlayerDisconnect(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.RED + "« " + event.getPlayer().getDisplayName());
    }
}
