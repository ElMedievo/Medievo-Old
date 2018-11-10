package medievo.medievo.commands.punishments;

import medievo.medievo.main;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static medievo.medievo.util.generic.noperms;
import static medievo.medievo.util.generic.syntaxerror;

public class pardon implements CommandExecutor {

    private final main plugin;

    public pardon(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pardon") && sender.hasPermission("medievo.pardon")) {
            if (args.length == 1) {
                OfflinePlayer unbanned = Bukkit.getOfflinePlayer(args[0]);
                Bukkit.getBanList(BanList.Type.NAME).pardon(unbanned.getName());
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.GOLD + "» " + ChatColor.DARK_AQUA + unbanned.getName() + ChatColor.GOLD + " has been unbanned!");
                } else {
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + " » " + ChatColor.DARK_AQUA + unbanned.getName() + ChatColor.GOLD + " has been unbanned!");
                }
            } else {
                sender.sendMessage(syntaxerror);
            }
        } else {
            sender.sendMessage(noperms);
        }
        return true;
    }
}
