package medievo.medievo.events;

import medievo.medievo.main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static medievo.medievo.main.globalon;

public class rankstemp implements Listener {

    private final main plugin;

    public rankstemp(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String puuid = player.getUniqueId().toString();

        /*BGMM*/ if (puuid.equals("92748c38-b41e-4f17-9d8f-50fd35599f2a")) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + "*" + ChatColor.GOLD + "❖" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
            player.setDisplayName(ChatColor.DARK_PURPLE + "*" + ChatColor.GOLD + "❖" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
        } else if /*Esservil, Ferrada*/ (puuid.equals("a4523451-5088-4192-b43a-0b9b56b740f7") || puuid.equals("5ecbb50f-560f-4a80-aabe-619da29f5e8a")) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + "*" + ChatColor.RED + "❖" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
            player.setDisplayName(ChatColor.DARK_PURPLE + "*" + ChatColor.RED + "❖" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
        } else if /*12x2*/(puuid.equals("9e03cc2e-3d48-4dff-b38c-4dc55e011a1d")) {
            player.setPlayerListName(ChatColor.RED + "❖" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
            player.setDisplayName(ChatColor.RED + "❖" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
        } else if /*Bernardita, Caprileee, Ferrada, drakos_, OneSip*/(puuid.equals("b66a092f-1719-4ed7-8cec-0fb5738ec853") || puuid.equals("fc0c0645-c4a6-407f-b2f0-9cef54b59f27") || puuid.equals("4f89273b-7d68-4173-a3f2-9c4b8451da9b") || puuid.equals("6a239799-5fea-4353-ac51-1ad7046b5c2e")) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + "*" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
            player.setDisplayName(ChatColor.DARK_PURPLE + "*" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
        } else if (puuid.equals("2cef6d05-ec9a-44b3-bedc-53359c68ae65")) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + "❖" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
            player.setDisplayName(ChatColor.DARK_PURPLE + "❖" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
        } else if /*GuatonBairon*/(puuid.equals("3f44b9a7-1ba8-491f-8410-8bb3a6911e23")) {
            player.setPlayerListName(ChatColor.DARK_RED + "*" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
            player.setDisplayName(ChatColor.DARK_RED + "*" + ChatColor.YELLOW + player.getName() + ChatColor.RESET);
        } else {
            player.setPlayerListName(ChatColor.YELLOW + player.getName() + ChatColor.RESET);
            player.setDisplayName(ChatColor.YELLOW + player.getName() + ChatColor.RESET);
        }

        if (!(globalon.containsKey(player.getName()))) {
            globalon.put(player.getName(), true);
        }

        event.setJoinMessage(ChatColor.GREEN + "» " + player.getDisplayName());
    }
}
