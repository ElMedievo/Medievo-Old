package medievo.medievo.commands.shortcuts;

import medievo.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static medievo.medievo.util.generic.noconsole;
import static medievo.medievo.util.generic.noperms;

public class teleport implements CommandExecutor {

    private final main plugin;

    public teleport(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("teleport") && sender.hasPermission("medievo.teleport")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        Location targetloc = target.getLocation();
                        player.teleport(targetloc);
                        sender.sendMessage(ChatColor.YELLOW + "Teleported to " + args[0]);
                    } else {
                        sender.sendMessage(ChatColor.DARK_AQUA + args[0] + ChatColor.RED + " is currently offline");
                    }
                } else if (args.length == 2) {
                    Player teleported = Bukkit.getPlayer(args[0]);
                    Player target = Bukkit.getPlayer(args[1]);
                    if (teleported != null && target != null) {
                        Location targetloc = target.getLocation();
                        teleported.teleport(targetloc);
                        sender.sendMessage(ChatColor.YELLOW + "Teleported " + args[0] + " to " + args[1]);
                        teleported.sendMessage(ChatColor.YELLOW + "You have been teleported to " + args[1]);
                    } else {
                        sender.sendMessage(ChatColor.RED + "One or both players are currently offline");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Command syntax: /tp {player}");
                }
            } else {
                sender.sendMessage(noconsole);
            }
        } else {
            sender.sendMessage(noperms);
        }
        return true;
    }
}
