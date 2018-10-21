package medievo.medievo.commands;

import medievo.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static medievo.medievo.util.generic.noperms;
import static medievo.medievo.util.generic.syntaxerror;

public class broadcast implements CommandExecutor {

    private final main plugin;

    public broadcast(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("broadcast") && sender.hasPermission("medievo.broadcast")) {
            if (args.length >= 1) {
                StringBuilder message = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    message.append(args[i] + " ");
                }
                Bukkit.broadcastMessage(ChatColor.RED + "[Broadcast] " + message);
            } else {
                sender.sendMessage(syntaxerror);
            }
        } else {
            sender.sendMessage(noperms);
        }
        return true;
    }
}
