package medievo.medievo.events;

import medievo.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static medievo.medievo.main.globalchatmanager;

public class onChat implements Listener {

    private final main plugin;

    public onChat(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        String player = p.getName();
        String message = event.getMessage();
        if (globalchatmanager.get(player)) {
            String global = ChatColor.AQUA + "(Global) " + ChatColor.RESET;
            for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                if (plugin.getConfig().getString("Clans.España").contains(players.getName())) {
                    players.sendMessage(global + "[" + ChatColor.GOLD + "España" + ChatColor.RESET + "] " + ChatColor.RESET + players.getDisplayName() + ChatColor.RESET + ": " + message);
                } else if (plugin.getConfig().getString("Clans.LosBudas").contains(players.getName())) {
                    players.sendMessage(global + "[" + ChatColor.GRAY + "LosBudas" + ChatColor.RESET + "] " + ChatColor.RESET + players.getDisplayName() + ChatColor.RESET + ": " + ChatColor.RESET + message);
                } else if (plugin.getConfig().getString("Clans.Küdaulmapun").contains(players.getName())) {
                    players.sendMessage(global + "[" + ChatColor.GREEN + "Küdaulmapun" + ChatColor.RESET + "] " + ChatColor.RESET + players.getDisplayName() + ChatColor.RESET + ": " + ChatColor.RESET + message);
                } else {
                    players.sendMessage(global + "[" + "NoTeam" + "] " + players.getDisplayName() + ": " + ChatColor.RESET + message);
                }
            }
            event.setCancelled(true);
        } else {
            for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                if (plugin.getConfig().getString("Clans.España").contains(players.getName())) {
                    players.sendMessage("[" + ChatColor.GOLD + "España" + ChatColor.RESET + "] " + ChatColor.RESET + players.getDisplayName() + ChatColor.RESET + ": " + message);
                } else if (plugin.getConfig().getString("Clans.LosBudas").contains(players.getName())) {
                    players.sendMessage("[" + ChatColor.GRAY + "LosBudas" + ChatColor.RESET + "] " + ChatColor.RESET + players.getDisplayName() + ChatColor.RESET + ": " + ChatColor.RESET + message);
                } else if (plugin.getConfig().getString("Clans.Küdaulmapun").contains(players.getName())) {
                    players.sendMessage("[" + ChatColor.GREEN + "Küdaulmapun" + ChatColor.RESET + "] " + ChatColor.RESET + players.getDisplayName() + ChatColor.RESET + ": " + ChatColor.RESET + message);
                } else {
                    players.sendMessage("[" + "NoTeam" + "] " + players.getDisplayName() + ": " + ChatColor.RESET + message);
                }
                event.setCancelled(true);
            }
        }
    }
}
