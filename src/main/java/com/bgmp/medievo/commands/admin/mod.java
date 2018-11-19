package com.bgmp.medievo.commands.admin;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.genericmessages.noperms;

public class mod implements CommandExecutor {

    private final main plugin;

    public mod(main instance) {
        plugin = instance;
    }

    private static HashMap<String, Boolean> modmode = new HashMap<String, Boolean>();
    private static HashMap<String, Location> modorigin = new HashMap<String, Location>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mod") && args.length == 0) {
            if (sender instanceof Player) {
                if (sender.hasPermission("medievo.mod")) {
                    Player player = (Player) sender;
                    String uuid = player.getUniqueId().toString();
                    if (modmode.get(uuid) == null || !modmode.get(uuid)) {
                        Location origin = player.getLocation();
                        modmode.put(uuid, true);
                        modorigin.put(uuid, origin);
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(ChatColor.GREEN + "You are now spectating the game!");
                        player.sendMessage(ChatColor.AQUA + "Be careful not to sort your inventory");
                        return true;
                    }
                    player.setGameMode(GameMode.SURVIVAL);
                    player.teleport(modorigin.get(uuid));
                    modmode.put(uuid, false);
                    player.sendMessage(ChatColor.GREEN + "You are no longer spectating the game!");
                } else {
                    sender.sendMessage(noperms);
                }
            } else {
                sender.sendMessage(noconsole);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Command syntax: /mod");
        }
        return true;
    }
}
