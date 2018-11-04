package medievo.medievo.war;

import medievo.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static medievo.medievo.main.globalon;

public class chat implements Listener {

    private final main plugin;

    public chat(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        Player p = event.getPlayer();
        String msg = event.getMessage();

        String es = "[" + ChatColor.GOLD + "España" + ChatColor.RESET + "] ";
        String budas = "[" + ChatColor.BLUE + "Los Budas" + ChatColor.RESET + "] ";
        String burgos = "[" + ChatColor.DARK_AQUA + "Los Burgos" + ChatColor.RESET + "] ";
        String italia = "[" + ChatColor.DARK_GREEN + "Italia" + ChatColor.RESET + "] ";
        String mapu = "[" + ChatColor.GREEN + "Küdaumapun" + ChatColor.RESET + "] ";
        String neutro = "[" + ChatColor.GRAY + "Neutral" + ChatColor.RESET + "] ";

        if (globalon.get(p.getName())) {
            if (plugin.getConfig().getString("Clans.españa").contains(p.getName())) {
                Bukkit.broadcastMessage(es + p.getDisplayName() + ": " + msg);
            } else if (plugin.getConfig().getString("Clans.budas").contains(p.getName())) {
                Bukkit.broadcastMessage(budas + p.getDisplayName() + ": " + msg);
            } else if (plugin.getConfig().getString("Clans.losburgos").contains(p.getName())) {
                Bukkit.broadcastMessage(burgos + p.getDisplayName() + ": " + msg);
            } else if (plugin.getConfig().getString("Clans.italia").contains(p.getName())) {
                Bukkit.broadcastMessage(italia + p.getDisplayName() + ": " + msg);
            } else if (plugin.getConfig().getString("Clans.mapuches").contains(p.getName())) {
                Bukkit.broadcastMessage(mapu + p.getDisplayName() + ": " + msg);
            } else {
                Bukkit.broadcastMessage(neutro + p.getDisplayName() + ": " + msg);
            }
        } else {
            String clan = "(" + ChatColor.AQUA + "Clan" + ChatColor.RESET + ") ";
            if (plugin.getConfig().getString("Clans.españa").contains(p.getName())) {
                for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                    if (plugin.getConfig().getString("Clans.españa").contains(players.getName())) {
                        players.sendMessage(clan + es + p.getDisplayName() + ": " + msg);
                    }
                }
            } else if (plugin.getConfig().getString("Clans.budas").contains(p.getName())) {
                for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                    if (plugin.getConfig().getString("Clans.budas").contains(players.getName())) {
                        players.sendMessage(clan + budas + p.getDisplayName() + ": " + msg);
                    }
                }
            } else if (plugin.getConfig().getString("Clans.losburgos").contains(p.getName())) {
                for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                    if (plugin.getConfig().getString("Clans.losburgos").contains(players.getName())) {
                        players.sendMessage(clan + burgos + p.getDisplayName() + ": " + msg);
                    }
                }
            } else if (plugin.getConfig().getString("Clans.italia").contains(p.getName())) {
                for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                    if (plugin.getConfig().getString("Clans.italia").contains(players.getName())) {
                        players.sendMessage(clan + italia + p.getDisplayName() + ": " + msg);
                    }
                }
            } else if (plugin.getConfig().getString("Clans.mapuches").contains(p.getName())) {
                for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                    if (plugin.getConfig().getString("Clans.mapuches").contains(players.getName())) {
                        players.sendMessage(clan + mapu + p.getDisplayName() + ": " + msg);
                    }
                }
            } else {
                Bukkit.broadcastMessage(neutro + p.getDisplayName() + ": " + msg);
            }
        }
    }
}
