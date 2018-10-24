package medievo.medievo.commands.shortcuts;

import medievo.medievo.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static medievo.medievo.util.generic.noconsole;
import static medievo.medievo.util.generic.noperms;

public class gamemode implements CommandExecutor {

    private final main plugin;

    public gamemode(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gamemode") && sender.hasPermission("medievo.gamemode")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                GameMode selfgm = player.getGameMode();
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.GOLD + " » " + ChatColor.YELLOW + "Your current gamemode is: " + selfgm.toString().toUpperCase());
                } else if (args.length == 1) {
                    if (args[0].equals("0")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.YELLOW + "Gamemode set to SURVIVAL");
                    } else if (args[0].equals("1")) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.YELLOW + "Gamemode set to CREATIVE");
                    } else if (args[0].equals("2")) {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.YELLOW + "Gamemode set to ADVENTURE");
                    } else if (args[0].equals("3")) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.YELLOW + "Gamemode set to SPECTATOR");
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + args[0] + " is not a valid gamemode");
                        return true;
                    }
                } else if (args.length == 2) {
                    Player referenced = Bukkit.getServer().getPlayer(args[1]);
                    String offreferenced = args[1];
                    if (referenced != null) {
                        if (args[0].equals("0")) {
                            referenced.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(ChatColor.GOLD + " » " + ChatColor.YELLOW + referenced.getDisplayName() + "'s gamemode set to SURVIVAL");
                        } else if (args[0].equals("1")) {
                            referenced.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(ChatColor.GOLD + " » " + ChatColor.YELLOW + referenced.getDisplayName() + "'s gamemode set to CREATIVE");
                        } else if (args[0].equals("2")) {
                            referenced.setGameMode(GameMode.ADVENTURE);
                            player.sendMessage(ChatColor.GOLD + " » " + ChatColor.YELLOW + referenced.getDisplayName() + "'s gamemode set to ADVENTURE");
                        } else if (args[0].equals("3")) {
                            referenced.setGameMode(GameMode.SPECTATOR);
                            player.sendMessage(ChatColor.GOLD + " » " + ChatColor.YELLOW + referenced.getDisplayName() + "'s gamemode set to SPECTATOR");
                        } else {
                            player.sendMessage(ChatColor.GOLD + " » " + ChatColor.YELLOW + "⚠ " + ChatColor.RED + args[0] + " is not a valid gamemode");
                            return true;
                        }
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.DARK_AQUA + offreferenced + ChatColor.RED + " is currently offline!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Command syntax: /gamemode {gm} {player}");
                }
            } else {
                sender.sendMessage(noconsole);
            }
        } else {
            sender.sendMessage(noperms);
        }
        return true;
    }
}
