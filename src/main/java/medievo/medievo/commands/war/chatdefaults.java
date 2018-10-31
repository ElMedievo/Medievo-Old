package medievo.medievo.commands.war;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static medievo.medievo.main.clanchatmanager;
import static medievo.medievo.main.globalchatmanager;

public class chatdefaults {

    public static void resetChats() {
        for (Player players : Bukkit.getServer().getOnlinePlayers()) {
            globalchatmanager.put(players.getName(), true);
            clanchatmanager.put(players.getName(), false);
            players.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "Chat cambiado a Global por reinicio del servidor");
        }
    }
}
