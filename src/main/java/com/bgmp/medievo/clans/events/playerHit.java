package com.bgmp.medievo.clans.events;

import com.bgmp.medievo.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class playerHit implements Listener {

    private final main plugin;

    public playerHit(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Player damaged = (Player) event.getEntity();
            String damageruuid = damager.getUniqueId().toString();
            String damageduuid = damaged.getUniqueId().toString();
            String clan1 = plugin.getConfig().getString("Players." + damageduuid + ".clan");
            String clan2 = plugin.getConfig().getString("Players." + damageruuid + ".clan");
            if (clan1.equals(clan2)) {
                event.setCancelled(true);
            }
        }
    }
}