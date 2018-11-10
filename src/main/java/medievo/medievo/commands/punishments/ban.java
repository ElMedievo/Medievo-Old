package medievo.medievo.commands.punishments;

import medievo.medievo.main;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static medievo.medievo.util.generic.noperms;
import static medievo.medievo.util.generic.syntaxerror;

public class ban implements CommandExecutor {

    private final main plugin;

    public ban(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pb") && sender.hasPermission("medievo.ban")) {
            if (args.length >= 2) {
                Player bannedon = Bukkit.getServer().getPlayer(args[0]);
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    sb.append(args[i]).append(" ");
                }
                String msg = ChatColor.GOLD + sb.toString();
                if (bannedon != null) {
                    if (sender instanceof Player) {
                        Player source = (Player) sender;
                        Bukkit.getBanList(BanList.Type.NAME).addBan(bannedon.getName(), msg, null, source.getDisplayName());
                        bannedon.kickPlayer(ChatColor.RED + "You have been permanently banned!" + "\n" + ChatColor.GOLD + "\n" + "» " + msg + "\n\n" + ChatColor.AQUA + "benja#3518" + "\n" + ChatColor.AQUA + "teloniusferro877@gmail.com");
                        Bukkit.broadcastMessage(source.getDisplayName() + ChatColor.GOLD + " » " + "Permanent Ban" + " » " + ChatColor.RESET + bannedon.getDisplayName() + ChatColor.GOLD + " » " + msg);
                    } else {
                        String console = ChatColor.GOLD + "❖" + ChatColor.DARK_AQUA + "Console";
                        Bukkit.getBanList(BanList.Type.NAME).addBan(bannedon.getName(), msg, null, console);
                        bannedon.kickPlayer(ChatColor.RED + "You have been permanently banned!" + "\n" + ChatColor.GOLD + "\n" + "» " + msg + "\n\n" + ChatColor.AQUA +  "benja#3518" + "\n" + ChatColor.AQUA +"teloniusferro877@gmail.com");
                        Bukkit.broadcastMessage(console + ChatColor.GOLD + " » " + "Permanent Ban" + " » " + ChatColor.RESET + bannedon.getDisplayName() + ChatColor.GOLD + " » " + msg);
                        Bukkit.getServer().getConsoleSender().sendMessage(console + ChatColor.GOLD + " » " + "Permanent Ban" + " » " + ChatColor.RESET + bannedon.getDisplayName() + ChatColor.GOLD + " » " + msg);
                    }
                } else {
                    String bannedoff = args[0];
                    if (sender instanceof Player) {
                        Player source = (Player) sender;
                        Bukkit.getBanList(BanList.Type.NAME).addBan(String.valueOf(bannedoff), msg, null, source.getDisplayName());
                        Bukkit.broadcastMessage(source.getDisplayName() + ChatColor.GOLD + " » " + "Permanent Ban" + " » " + ChatColor.DARK_AQUA + bannedoff + ChatColor.GOLD + " » " + msg);
                    } else {
                        String console = ChatColor.GOLD + "❖" + ChatColor.DARK_AQUA + "Console";
                        Bukkit.getBanList(BanList.Type.NAME).addBan(bannedoff, msg, null, console);
                        Bukkit.broadcastMessage(console + ChatColor.GOLD + " » " + "Permanent Ban" + " » " + ChatColor.DARK_AQUA + bannedoff + ChatColor.GOLD + " » " + msg);
                    }
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
