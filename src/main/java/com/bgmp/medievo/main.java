package com.bgmp.medievo;

import com.bgmp.medievo.clans.commands.*;
import com.bgmp.medievo.clans.events.chat;
import com.bgmp.medievo.commands.randomtp.randomtp;
import com.bgmp.medievo.events.playerJoin;
import com.bgmp.medievo.util.reloadConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static com.bgmp.medievo.util.queues.loadqueues;

public final class main extends JavaPlugin {

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

        Bukkit.getPluginManager().registerEvents(new chat(this), this);
        Bukkit.getPluginManager().registerEvents(new playerJoin(this), this);
    }

    @Override
    public void onDisable() {

    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}