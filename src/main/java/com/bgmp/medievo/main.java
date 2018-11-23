package com.bgmp.medievo;

import com.bgmp.medievo.clans.commands.*;
import com.bgmp.medievo.clans.events.chat;
import com.bgmp.medievo.clans.events.playerHit;
import com.bgmp.medievo.commands.admin.broadcast;
import com.bgmp.medievo.commands.admin.heal;
import com.bgmp.medievo.commands.chat.adminchat;
import com.bgmp.medievo.commands.chat.general;
import com.bgmp.medievo.commands.chat.message;
import com.bgmp.medievo.commands.admin.mod;
import com.bgmp.medievo.clans.commands.reply;
import com.bgmp.medievo.commands.home.home;
import com.bgmp.medievo.commands.other.ping;
import com.bgmp.medievo.commands.punishments.*;
import com.bgmp.medievo.commands.randomtp.randomtp;
import com.bgmp.medievo.commands.home.sethome;
import com.bgmp.medievo.commands.tpa.tpa;
import com.bgmp.medievo.commands.tpa.tpaccept;
import com.bgmp.medievo.commands.tpa.tpcancel;
import com.bgmp.medievo.events.*;
import com.bgmp.medievo.commands.admin.gamemode;
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
        getCommand("gamemode").setExecutor(new gamemode(this));
        getCommand("broadcast").setExecutor(new broadcast(this));
        getCommand("mod").setExecutor(new mod(this));
        getCommand("ban").setExecutor(new ban(this));
        getCommand("kick").setExecutor(new kick(this));
        getCommand("pardon").setExecutor(new pardon(this));
        getCommand("report").setExecutor(new reports(this));
        getCommand("message").setExecutor(new message(this));
        getCommand("adminchat").setExecutor(new adminchat(this));
        getCommand("reply").setExecutor(new reply(this));
        getCommand("sethome").setExecutor(new sethome(this));
        getCommand("home").setExecutor((new home(this)));
        getCommand("heal").setExecutor(new heal(this));
        getCommand("tpa").setExecutor(new tpa(this));
        getCommand("tpaccept").setExecutor(new tpaccept(this));
        getCommand("tpcancel").setExecutor(new tpcancel(this));
        getCommand("ping").setExecutor(new ping(this));
        getCommand("clanchat").setExecutor(new clanchat(this));
        getCommand("general").setExecutor(new general(this));

        Bukkit.getPluginManager().registerEvents(new playerLeave(this), this);
        Bukkit.getPluginManager().registerEvents(new playerRespawn(this), this);
        Bukkit.getPluginManager().registerEvents(new chat(this), this);
        Bukkit.getPluginManager().registerEvents(new playerJoin(this), this);
        Bukkit.getPluginManager().registerEvents(new playerKill(this), this);
        Bukkit.getPluginManager().registerEvents(new playerHit(this), this);
    }





    @Override
    public void onDisable() {

    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
