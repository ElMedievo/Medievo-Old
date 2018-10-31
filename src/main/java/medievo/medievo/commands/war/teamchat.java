package medievo.medievo.commands.war;

import medievo.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static medievo.medievo.main.clanchatmanager;
import static medievo.medievo.main.globalchatmanager;
import static medievo.medievo.util.generic.noconsole;

public class teamchat implements CommandExecutor {

    private final main plugin;

    public teamchat(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("t")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    if (clanchatmanager.get(player.getName()) == null || !(clanchatmanager.get(player.getName()))) {
                        clanchatmanager.put(player.getName(), true);
                        globalchatmanager.put(player.getName(), false);
                        sender.sendMessage("Tu chat default es ahora el de tu Clan");
                    } else {
                        sender.sendMessage(ChatColor.RED + "El chat del Clan ya es tu chat default!");
                    }
                } else {
                    StringBuilder message = new StringBuilder();
                    for (int i = 0; i < args.length; i++) {
                        message.append(args[i] + " ");
                    }
                    for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                        if (plugin.getConfig().getString("Clans.España").contains(players.getName())) {
                            players.sendMessage("[" + ChatColor.GOLD + "España" + ChatColor.RESET + "] " + ChatColor.RESET + players.getDisplayName() + ChatColor.RESET + ": " + message);
                        } else if (plugin.getConfig().getString("Clans.LosBudas").contains(players.getName())) {
                            players.sendMessage("[" + ChatColor.GRAY + "LosBudas" + ChatColor.RESET + "] " + ChatColor.RESET + players.getDisplayName() + ChatColor.RESET + ": " + ChatColor.RESET + message);
                        } else if (plugin.getConfig().getString("Clans.Küdaulmapun").contains(players.getName())) {
                            players.sendMessage("[" + ChatColor.GREEN + "Küdaulmapun" + ChatColor.RESET + "] " + ChatColor.RESET + players.getDisplayName() + ChatColor.RESET + ": " + ChatColor.RESET + message);
                        } else {
                            players.sendMessage("[" + "NoTeam" + "] " + players.getDisplayName() + ": " + ChatColor.RESET + message);
                        }
                    }
                }
            } else {
                sender.sendMessage(noconsole);
            }
        }
        return true;
    }
}