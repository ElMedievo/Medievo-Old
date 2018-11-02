package medievo.medievo.commands.war.chat;

import medievo.medievo.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static medievo.medievo.main.globalIsOn;

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
                    if (globalIsOn.get(player.getName())) {
                        globalIsOn.put(player.getName(), false);
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
