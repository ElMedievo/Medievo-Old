package com.bgmp.medievo.commands.randomtp;

import com.bgmp.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class rtpstatus implements Listener {

    private final main plugin;

    public rtpstatus(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String uuid = event.getPlayer().getUniqueId().toString();
        String name = event.getPlayer().getName();
        if (!(plugin.getConfig().contains(uuid))) {
            plugin.getConfig().set("Players." + uuid + ".rtp", true);
            event.getPlayer().sendMessage(ChatColor.GREEN + "Use your RandomTP token by entering " + ChatColor.AQUA + "/rtp" + ChatColor.GREEN + "!");
        }
        plugin.getConfig().set("Players." + uuid + ".nick", name);
        plugin.saveConfig();
    }
}
