package medievo.medievo.commands.war.chat;

import medievo.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static medievo.medievo.main.*;

public class onPlayerChat implements Listener {

    private final main plugin;

    public onPlayerChat(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        String playerdisplay = event.getPlayer().getDisplayName();
        String player = event.getPlayer().getName();
        String msg = event.getMessage();

        if (globalIsOn.get(player)) {
            String global = ChatColor.AQUA + "(Global) " + ChatColor.RESET;
            if (es.get("esp").contains(player)) {
                Bukkit.broadcastMessage(global + "[" + ChatColor.GOLD + "España" + ChatColor.RESET + "] " + playerdisplay + ChatColor.RESET + ": " + msg);
            } else if (bu.get("bud").contains(player)) {
                Bukkit.broadcastMessage(global + "[" + ChatColor.BLUE + "Los Budas" + ChatColor.RESET + "] " + playerdisplay + ": " + msg);
            } else if (ma.get("map").contains(player)) {
                Bukkit.broadcastMessage(global + "[ " + ChatColor.GREEN + "Küdaumapun" + ChatColor.RESET + "] " + playerdisplay + ": " + msg);
            } else {
                Bukkit.broadcastMessage(global + "[Neutral] " + player + ": " + msg);
            }
        } else {
            if (es.get("esp").contains(player)) {
                event.getPlayer().sendMessage("[" + ChatColor.GOLD + "España" + ChatColor.RESET + "] " + playerdisplay + ChatColor.RESET + ": " + msg);
                for (Player checked : Bukkit.getServer().getOnlinePlayers()) {
                    if (es.get("esp").contains(checked.getName())) {
                        if (!(checked.getName().equals(player))) {
                            checked.sendMessage("[" + ChatColor.GOLD + "España" + ChatColor.RESET + "] " + playerdisplay + ChatColor.RESET + ": " + msg);
                        }
                    } else {
                        return;
                    }
                }
            } else if (bu.get("bud").contains(player)) {
                event.getPlayer().sendMessage("[" + ChatColor.BLUE + "Los Budas" + ChatColor.RESET + "] " + playerdisplay + ": " + msg);
                for (Player checked : Bukkit.getServer().getOnlinePlayers()) {
                    if (bu.get("bud").contains(checked.getName())) {
                        if (!(checked.getName().equals(player))) {
                            checked.sendMessage("[" + ChatColor.BLUE + "Los Budas" + ChatColor.RESET + "] " + playerdisplay + ": " + msg);
                        }
                    } else {
                        return;
                    }
                }
            } else if (ma.get("map").contains(player)) {
                event.getPlayer().sendMessage("[ " + ChatColor.GREEN + "Küdaumapun" + ChatColor.RESET + "] " + playerdisplay + ": " + msg);
                for (Player checked : Bukkit.getServer().getOnlinePlayers()) {
                    if (ma.get("map").contains(checked.getName())) {
                        if (!(checked.getName().equals(player))) {
                            checked.sendMessage("[ " + ChatColor.GREEN + "Küdaumapun" + ChatColor.RESET + "] " + playerdisplay + ": " + msg);
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
