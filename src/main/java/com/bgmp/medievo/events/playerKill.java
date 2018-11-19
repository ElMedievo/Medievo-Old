package com.bgmp.medievo.events;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class playerKill implements Listener {

    private final main plugin;

    public playerKill(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void playerDies(PlayerDeathEvent event) {
        Player deadp = event.getEntity();
        World w = deadp.getWorld();
        w.playSound(deadp.getLocation(), Sound.ENTITY_BLAZE_DEATH, 10, 1);
        Entity killer = event.getEntity().getKiller();
        if (killer != null) {
            killer.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "+1 Kill " + ChatColor.DARK_PURPLE + "| " + ChatColor.GRAY + "killed " + ChatColor.YELLOW + deadp.getDisplayName());
            event.setDeathMessage(ChatColor.YELLOW + deadp.getName() + ChatColor.GRAY + " was killed by " + ChatColor.YELLOW + ((Player) killer).getDisplayName());
        }
    }
}
