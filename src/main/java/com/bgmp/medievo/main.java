package com.bgmp.medievo;

import com.bgmp.medievo.clans.commands.*;
import com.bgmp.medievo.clans.events.chat;
import com.bgmp.medievo.commands.broadcast;
import com.bgmp.medievo.commands.mod;
import com.bgmp.medievo.commands.punishments.ban;
import com.bgmp.medievo.commands.punishments.kick;
import com.bgmp.medievo.commands.punishments.pardon;
import com.bgmp.medievo.commands.punishments.reports;
import com.bgmp.medievo.commands.randomtp.randomtp;
import com.bgmp.medievo.events.playerJoin;
import com.bgmp.medievo.commands.gamemode;
import com.bgmp.medievo.events.playerKill;
import com.bgmp.medievo.events.playerLeave;
import com.bgmp.medievo.events.playerRespawn;
import com.bgmp.medievo.util.reloadConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static com.bgmp.medievo.util.queues.loadqueues;

public final class main extends JavaPlugin {
//TESTING
    static main instance;

    @Override
    public void onEnable() {
        loadConfig();
        loadqueues();

        getCommand("randomtp").setExecutor(new randomtp(this));
        getCommand("create").setExecutor(new create(this));
        getCommand("leave").setExecutor(new leave(this));
        getCommand("confirm").setExecutor(new confirmleaving(this));
        getCommand("deny").setExecutor(new confirmleaving(this));
        getCommand("invite").setExecutor(new invite(this));
        getCommand("accept").setExecutor(new accept(this));
        getCommand("cancel").setExecutor(new cancel(this));
        getCommand("reloadconfig").setExecutor(new reloadConfig(this));
        getCommand("gamemode").setExecutor(new gamemode(this));
        getCommand("broadcast").setExecutor(new broadcast(this));
        getCommand("mod").setExecutor(new mod(this));
        getCommand("ban").setExecutor(new ban(this));
        getCommand("kick").setExecutor(new kick(this));
        getCommand("pardon").setExecutor(new pardon(this));
        getCommand("report").setExecutor(new reports(this));

        Bukkit.getPluginManager().registerEvents(new playerLeave(this), this);
        Bukkit.getPluginManager().registerEvents(new playerRespawn(this), this);
        Bukkit.getPluginManager().registerEvents(new chat(this), this);
        Bukkit.getPluginManager().registerEvents(new playerJoin(this), this);
        Bukkit.getPluginManager().registerEvents(new playerKill(this), this);
    }

    @Override
    public void onDisable() {

    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
