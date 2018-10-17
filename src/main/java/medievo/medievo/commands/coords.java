package medievo.medievo.commands;

import medievo.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class coords implements CommandExecutor {

    private final main plugin;

    public coords(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("coords")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    Location loc = player.getLocation();

                    int x = loc.getBlockX();
                    int y = loc.getBlockY();
                    int z = loc.getBlockZ();

                    Bukkit.broadcastMessage(ChatColor.GOLD + "» " + ChatColor.YELLOW + sender.getName() + ":" + "\nX: " + ChatColor.GREEN + x + ChatColor.YELLOW + "\nY: " + ChatColor.GREEN + y + ChatColor.YELLOW + "\nZ: " + ChatColor.GREEN + z);
                }
            }
        }
        return true;
    }
}
