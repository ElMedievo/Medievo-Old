package com.bgmp.medievo.commands.randomtp;
import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

import static com.bgmp.medievo.util.genericmessages.noconsole;
import static com.bgmp.medievo.util.genericmessages.syntaxerror;


public class randomtp implements CommandExecutor {

    private final main plugin;

    public randomtp(main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("randomtp") && args.length == 0) {
                if (plugin.getConfig().getBoolean("Players." + player.getUniqueId().toString() + ".rtp", true)) {
                    plugin.getConfig().set("Players." + player.getUniqueId().toString() + ".rtp", false);
                    plugin.saveConfig();
                    World w = player.getWorld();
                    Random random = new Random();
                    while (true) {
                        int randomX = random.nextInt(2250 - -2250 + 1) - 2250;
                        int randomZ = random.nextInt(2250 - -2250 + 1) - 2250;
                        int randomY = random.nextInt(80 - 60 + 1) + 60;
                        Location randomLoc = new Location(player.getWorld(), randomX, randomY + 1, randomZ);
                        Block reference = randomLoc.getBlock();
                        Block referenceUp = w.getBlockAt(randomX, randomY + 1, randomZ);

                        if (reference.getType() == Material.AIR && referenceUp.getType() == Material.AIR || reference.getType() == Material.TALL_GRASS) {
                            player.teleport(randomLoc);
                            player.sendMessage(ChatColor.DARK_PURPLE + "Teleported to:");
                            player.sendMessage(ChatColor.RED + "X: " + randomX);
                            player.sendMessage(ChatColor.RED + "Y: " + randomY);
                            player.sendMessage(ChatColor.RED + "Z: " + randomZ);
                            Inventory playerinv = player.getInventory();
                            ItemStack boat = new ItemStack(Material.OAK_BOAT);
                            ItemStack food = new ItemStack(Material.COOKED_BEEF, 16);
                            ItemStack wand = new ItemStack(Material.GOLDEN_SHOVEL);
                            playerinv.addItem(boat);
                            playerinv.addItem(food);
                            break;
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You have already used your /rtp token!");
                }
            } else {
                sender.sendMessage(syntaxerror);
            }
        } else {
            sender.sendMessage(noconsole);
        }
        return true;
    }
}

