package com.bgmp.medievo.commands.home;

import com.bgmp.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.noperms;
import static com.bgmp.medievo.util.genericmessages.noconsole;

public class home implements CommandExecutor {

    private final main plugin;

    public home(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("home")) {
                Player player = (Player) sender;
                String uuid = player.getUniqueId().toString();
                if (args.length == 0) {
                    if (plugin.getConfig().isConfigurationSection("Players." + uuid + ".home")) {
                        String w = plugin.getConfig().getString("Players." + uuid + ".home.world");
                        double x = plugin.getConfig().getDouble("Players." + uuid + ".home.x");
                        double y = plugin.getConfig().getDouble("Players." + uuid + ".home.y");
                        double z = plugin.getConfig().getDouble("Players." + uuid + ".home.z");
                        Location home = new Location(Bukkit.getWorld(w), x, y, z);
                        player.teleport(home);
                        player.sendMessage(ChatColor.GREEN + "Welcome home!");
                    } else {
                        player.sendMessage(ChatColor.RED + "You haven't set your home yet!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Command Syntax: /home");
                }
            }
        } else {
            sender.sendMessage(noconsole);
        }
        return true;
    }
}
