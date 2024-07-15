package pl.chudziudgi.core.feature.access;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.util.ChatUtil;

public class AccessController implements Listener {

    public AccessController(ChCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        int playerCount = Bukkit.getOnlinePlayers().size();
        if (player.hasPermission("core.access.admin")) return;
        if (playerCount >= 150 || !player.hasPermission("core.access.gold")) {
            player.kickPlayer(ChatUtil.fixColor("&cAby móc wejść na serwer wymagana jest ranga &3&lGOLD\n&7Zakup na &3klanmc.pl"));
            return;
        }
        if (playerCount >= 100 || !player.hasPermission("core.access.iron")) {
            player.kickPlayer(ChatUtil.fixColor("&cAby móc wejść na serwer wymagana jest ranga &f&nIRON\n&7Zakup na &3klanmc.pl"));
            return;
        }
    }
}
