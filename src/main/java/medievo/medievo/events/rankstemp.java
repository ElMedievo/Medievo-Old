package medievo.medievo.events;

import medievo.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class rankstemp implements Listener {

    private final main plugin;

    public rankstemp(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.getName().equalsIgnoreCase("BGMM")) {
            player.setPlayerListName(ChatColor.GOLD + "❖" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
            player.setDisplayName(ChatColor.GOLD + "❖" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
        } else if (player.getName().equalsIgnoreCase("Ferrada")) {
            player.setPlayerListName(ChatColor.RED + "❖" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
            player.setDisplayName(ChatColor.RED + "❖" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
        } else {
            player.setPlayerListName(ChatColor.YELLOW + player.getName() + ChatColor.RESET);
            player.setDisplayName(ChatColor.YELLOW + player.getName() + ChatColor.RESET);
        }
    }
}
