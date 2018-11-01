package medievo.medievo.events;

import medievo.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static medievo.medievo.main.globalchatmanager;
import static medievo.medievo.main.teams;

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
                if (plugin.getConfig().getString("Clans.España").contains(p.getName())) {
                    players.sendMessage(global + "[" + ChatColor.GOLD + "España" + ChatColor.RESET + "] " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET + ": " + message);
                } else if (plugin.getConfig().getString("Clans.LosBudas").contains(players.getName())) {
                    players.sendMessage(global + "[" + ChatColor.GRAY + "LosBudas" + ChatColor.RESET + "] " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET + ": " + ChatColor.RESET + message);
                } else if (plugin.getConfig().getString("Clans.Küdaulmapun").contains(players.getName())) {
                    players.sendMessage(global + "[" + ChatColor.GREEN + "Küdaulmapun" + ChatColor.RESET + "] " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET + ": " + ChatColor.RESET + message);
                } else {
                    players.sendMessage(global + "[Pacifista] " + p.getDisplayName() + ": " + ChatColor.RESET + message);
                }
            }
            event.setCancelled(true);
        } else {
            if (plugin.getConfig().getString("Clans.España").contains(p.getName())) {
                for (Player es : Bukkit.getServer().getOnlinePlayers()) {
                    String checked = es.getName();
                    if (teams.get("españa").contains(checked)) {
                        es.sendMessage("[" + ChatColor.GOLD + "España" + ChatColor.RESET + "] " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET + ": " + message);
                    }
                }
            } else if (plugin.getConfig().getString("Clans.LosBudas").contains(p.getName())) {
                for (Player budas : Bukkit.getServer().getOnlinePlayers()) {
                    String checked = budas.getName();
                    if (teams.get("losbudas").contains(checked)) {
                        budas.sendMessage("[" + ChatColor.GRAY + "LosBudas" + ChatColor.RESET + "] " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET + ": " + ChatColor.RESET + message);
                    }
                }
            } else if (plugin.getConfig().getString("Clans.Küdaulmapun").contains(p.getName())) {
                for (Player mapu : Bukkit.getServer().getOnlinePlayers()) {
                    String checked = mapu.getName();
                    if (teams.get("mapuches").contains(checked)) {
                        mapu.sendMessage("[" + ChatColor.GREEN + "Küdaulmapun" + ChatColor.RESET + "] " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET + ": " + ChatColor.RESET + message);
                    }
                }
            } else {
                Bukkit.broadcastMessage("[Pacifista] " + p.getDisplayName() + ": " + ChatColor.RESET + message);
            }
            event.setCancelled(true);
        }
    }
}
