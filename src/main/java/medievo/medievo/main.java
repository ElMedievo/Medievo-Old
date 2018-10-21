package medievo.medievo;

import medievo.medievo.commands.broadcast;
import medievo.medievo.commands.coords;
import medievo.medievo.commands.message;
import medievo.medievo.commands.tpa.tpa;
import medievo.medievo.commands.tpa.tpaccept;
import medievo.medievo.commands.tpa.tpcancel;
import medievo.medievo.events.rankstemp;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class main extends JavaPlugin {

    public static HashMap<String, Player> tpaqueue = new HashMap<String, Player>();

    static main instance;

    @Override
    public void onEnable() {
        getCommand("tpa").setExecutor(new tpa(this));
        getCommand("tpaccept").setExecutor(new tpaccept(this));
        getCommand("tpcancel").setExecutor(new tpcancel(this));
        getCommand("coords").setExecutor(new coords(this));
        getCommand("message").setExecutor(new message(this));
        getCommand("broadcast").setExecutor(new broadcast(this));

        Bukkit.getPluginManager().registerEvents(new rankstemp(this), this);
    }

    @Override
    public void onDisable() {

    }
}
