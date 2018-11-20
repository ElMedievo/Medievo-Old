package com.bgmp.medievo.commands.home;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmp.medievo.util.genericmessages.noperms;
import static com.bgmp.medievo.util.genericmessages.noconsole;

public class sethome implements CommandExecutor {

    private final main plugin;

    public sethome(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("sethome")) {
                Player player = (Player) sender;
                String uuid = player.getUniqueId().toString();
                if (args.length == 0) {
                    String w = player.getWorld().getName();
                    Location origin = player.getLocation();
                    double x = origin.getX();
                    double y = origin.getY();
                    double z = origin.getZ();
                    plugin.getConfig().set("Players." + uuid + ".home.world", w);
                    plugin.getConfig().set("Players." + uuid + ".home.x", x);
                    plugin.getConfig().set("Players." + uuid + ".home.y", y);
                    plugin.getConfig().set("Players." + uuid + ".home.z", z);
                    player.setBedSpawnLocation(player.getLocation(), true);
                    player.sendMessage(ChatColor.GREEN + "Your home has been set!");
                } else {
                    player.sendMessage(ChatColor.RED + "Command Syntax: /sethome");
                }
            }
        } else {
            sender.sendMessage(noconsole);
        }
        return true;
    }
}
