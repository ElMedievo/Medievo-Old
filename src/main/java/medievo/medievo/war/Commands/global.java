package medievo.medievo.war.Commands;

import medievo.medievo.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static medievo.medievo.main.globalon;
import static medievo.medievo.util.generic.noconsole;
import static medievo.medievo.util.generic.syntaxerror;

public class global implements CommandExecutor {

    private final main plugin;

    public global(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("global")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    if (!(globalon.get(player.getName()))) {
                        globalon.put(player.getName(), true);
                        sender.sendMessage("Tu chat default es ahora el chat Global");
                    } else {
                        sender.sendMessage("Tu chat default ya es el chat Global!");
                    }
                } else {
                    sender.sendMessage(syntaxerror);
                }
            } else {
                sender.sendMessage(noconsole);
            }
        }
        return true;
    }
}

