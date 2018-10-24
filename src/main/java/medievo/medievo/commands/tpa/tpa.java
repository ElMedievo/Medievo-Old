package medievo.medievo.commands.tpa;

import medievo.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static medievo.medievo.main.tpaqueue;
import static medievo.medievo.util.generic.syntaxerror;

public class tpa implements CommandExecutor {

    private final main plugin;

    public tpa(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tpa")) {
            if (sender instanceof Player) {
                if (args.length == 1) {
                    Player player1 = (Player) sender;
                    Player player2 = Bukkit.getServer().getPlayer(args[0]);
                    if (player2 != null) {
                        Player issuer = player1;
                        String reciever = player2.getUniqueId().toString();
                        if (tpaqueue.get(reciever) == null) {
                            tpaqueue.put(reciever, issuer);
                            player1.sendMessage(ChatColor.GOLD + "» " + ChatColor.AQUA + "Petición de teletransporte enviada a " + ChatColor.YELLOW + player2.getDisplayName());
                            player2.sendMessage(ChatColor.GOLD + "» " + ChatColor.YELLOW + player1.getDisplayName() + ChatColor.AQUA + " Quiere teletransportarse hacia tí\n  " + ChatColor.GREEN + "/tpaccept" + ChatColor.AQUA + " o " + ChatColor.RED + "/tpcancel");
                            return true;
                        } else {
                            sender.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "El destinatario tiene una solicitud pendiente y debe cancelarla para recibir una nueva.");
                        }
                    } else {
                        sender.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "Jugador no encontrado");
                    }
                } else {
                    sender.sendMessage(syntaxerror);
                }
            }
        }
        return true;
    }
}
