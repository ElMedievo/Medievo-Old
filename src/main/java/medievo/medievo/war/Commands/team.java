package medievo.medievo.war.Commands;

import medievo.medievo.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static medievo.medievo.main.globalon;

public class team implements CommandExecutor {

    private final main plugin;

    public team(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("team")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    if (globalon.get(player.getName())) {
                        globalon.put(player.getName(), false);
                        player.sendMessage("Tu chat default es ahora el de tu Clan");
                    } else {
                        player.sendMessage("Tu chat default ya es el de tu Clan!");
                    }
                }
            }
        }
        return true;
    }
}
