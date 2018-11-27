package com.bgmp.medievo.clans.events;

import com.bgmp.medievo.main;
import org.bukkit.block.data.type.Snow;
import org.bukkit.entity.*;
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
        } else if (event.getDamager() instanceof Arrow || event.getDamager() instanceof SpectralArrow || event.getDamager() instanceof TippedArrow || event.getDamager() instanceof SplashPotion && event.getEntity() instanceof Player) {
            Entity projectile = event.getDamager();
            EntityType projectilekind = projectile.getType();
            Player damager;
            Player damaged = (Player) event.getEntity();
            String damageruuid = null;
            String damageduuid = damaged.getUniqueId().toString();
            switch (projectilekind) {
                case ARROW:
                   Arrow arrow = (Arrow) event.getDamager();
                   if (arrow.getShooter() instanceof Player) {
                       damager = (Player) arrow.getShooter();
                       damageruuid = damager.getUniqueId().toString();
                   }
                    break;
                case TIPPED_ARROW:
                    TippedArrow tarrow = (TippedArrow) event.getDamager();
                    if (tarrow.getShooter() instanceof Player) {
                        damager = (Player) tarrow.getShooter();
                        damageruuid = damager.getUniqueId().toString();
                    }
                    break;
                case SPECTRAL_ARROW:
                    SpectralArrow earrow = (SpectralArrow) event.getDamager();
                    if (earrow.getShooter() instanceof Player) {
                        damager = (Player) earrow.getShooter();
                        damageruuid = damager.getUniqueId().toString();
                    }
                    break;
                case SPLASH_POTION:
                    SplashPotion sp = (SplashPotion) event.getDamager();
                    if (sp.getShooter() instanceof Player) {
                        damager = (Player) sp.getShooter();
                        damageruuid = damager.getUniqueId().toString();
                    }
                case SNOWBALL:
                    Snowball snow = (Snowball) event.getDamager();
                    if (snow.getShooter() instanceof Player) {
                        damager = (Player) snow.getShooter();
                        damageduuid = damager.getUniqueId().toString();
                    }
            }
            String clan1 = plugin.getConfig().getString("Players." + damageduuid + ".clan");
            String clan2 = plugin.getConfig().getString("Players." + damageruuid + ".clan");
            if (!(damageruuid.equals(damageduuid)) && clan1.equals(clan2)) {
                event.setCancelled(true);
            }
        }
    }
}