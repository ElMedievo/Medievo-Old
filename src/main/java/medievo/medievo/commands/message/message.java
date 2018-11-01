package medievo.medievo.commands.message;

import medievo.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static medievo.medievo.util.generic.syntaxerror;

public class message implements CommandExecutor {
    private final main plugin;

    public message(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("message")) {
            Player msgsender = (Player) sender;
            if (args.length >= 2) {
                StringBuilder message = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    message.append(args[i] + " ");
                }
                String receiver = args[0];
                Player receptor = Bukkit.getServer().getPlayer(receiver);
                World w = msgsender.getWorld();
                if (!(receptor == null)) {
                    sender.sendMessage(ChatColor.GRAY + "To " + ChatColor.RESET + receptor.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.GREEN + message);
                    receptor.sendMessage(ChatColor.GRAY + "From " + ChatColor.RESET + msgsender.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.GREEN + message);
                    receptor.playSound(receptor.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 2);
                } else {
                    sender.sendMessage(ChatColor.RED + "Player not found");
                }
            } else {
                World w = msgsender.getWorld();
                sender.sendMessage(syntaxerror);
            }
        }
        return true;
    }
}
