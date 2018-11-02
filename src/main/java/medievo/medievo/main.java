package medievo.medievo;

import medievo.medievo.commands.broadcast;
import medievo.medievo.commands.coords;
import medievo.medievo.commands.message.message;
import medievo.medievo.commands.shortcuts.gamemode;
import medievo.medievo.commands.shortcuts.teleport;
import medievo.medievo.commands.tpa.tpa;
import medievo.medievo.commands.tpa.tpaccept;
import medievo.medievo.commands.tpa.tpcancel;
import medievo.medievo.commands.war.chat.global;
import medievo.medievo.commands.war.chat.onPlayerChat;
import medievo.medievo.commands.war.chat.team;
import medievo.medievo.events.onPlayerDisconnect;
import medievo.medievo.events.rankstemp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

import static medievo.medievo.commands.war.teams.loadTeams;

public final class main extends JavaPlugin {

    public static HashMap<String, Player> tpaqueue = new HashMap<String, Player>();
    public static HashMap<String, Boolean> globalIsOn = new HashMap<String, Boolean>();
    public static HashMap<String, Boolean> teamIsOn = new HashMap<String, Boolean>();

    public static HashMap<String, List<String>> es = new HashMap<String, List<String>>();
    public static HashMap<String, List<String>> bu = new HashMap<String, List<String>>();
    public static HashMap<String, List<String>> ma = new HashMap<String, List<String>>();

    static main instance;

    @Override
    public void onEnable() {
        getCommand("tpa").setExecutor(new tpa(this));
        getCommand("tpaccept").setExecutor(new tpaccept(this));
        getCommand("tpcancel").setExecutor(new tpcancel(this));
        getCommand("coords").setExecutor(new coords(this));
        getCommand("message").setExecutor(new message(this));
        getCommand("broadcast").setExecutor(new broadcast(this));
        getCommand("gamemode").setExecutor(new gamemode(this));
        getCommand("teleport").setExecutor(new teleport(this));
        getCommand("global").setExecutor(new global(this));
        getCommand("team").setExecutor(new team(this));

        Bukkit.getPluginManager().registerEvents(new onPlayerDisconnect(this), this);
        Bukkit.getPluginManager().registerEvents(new rankstemp(this), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerChat(this), this);

        for (Player players : Bukkit.getServer().getOnlinePlayers()) {
            globalIsOn.put(players.getName(), true);
            players.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "Tu chat default es ahora el Global por reinicio del servidor");
        }

        loadTeams();
        loadConfig();
    }

    @Override
    public void onDisable() {

    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
