package medievo.medievo.war.Commands;

import medievo.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static medievo.medievo.util.generic.noperms;
import static medievo.medievo.util.generic.syntaxerror;

public class reloadTeams implements CommandExecutor {

    private final main plugin;

    public reloadTeams(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("reloadconfig") && sender.hasPermission("medievo.reloadconfig")) {
            if (args.length == 0) {
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Equipos actualizados!");
            } else {
                sender.sendMessage(syntaxerror);
            }
        } else {
            sender.sendMessage(noperms);
        }
        return true;
    }
}
