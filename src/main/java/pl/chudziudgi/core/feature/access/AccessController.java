package pl.chudziudgi.core.feature.access;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class AccessController implements Listener {

    private final ChCore plugin;
    private final AccessConfig config;

    public AccessController(ChCore plugin, AccessConfig config) {
        this.plugin = plugin;
        this.config = config;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();
                int playerCount = Bukkit.getOnlinePlayers().size();
                if (!UserManager.isExists(player)) return;
                if (playerCount > config.getMaxPlayers()) {
                     if (player.hasPermission("core.access.admin")) return;
                    player.kickPlayer(ChatUtil.fixColor("&cSerwer jest pełen &4:c"));
                    return;
                }
                if (playerCount > config.getMinimalForGold() && !player.hasPermission("core.access.gold")) {
                    player.kickPlayer(ChatUtil.fixColor("&cAby móc wejść na serwer wymagana jest ranga: &3&lGOLD\n&7Zakup na &3klanmc.pl"));
                    return;
                }
                if (playerCount > config.getMinimalForIron() && !player.hasPermission("core.access.iron")) {
                    player.kickPlayer(ChatUtil.fixColor("&cAby móc wejść na serwer wymagana jest ranga: &f&nIRON\n&7Zakup na &3klanmc.pl"));
                }
            }
        }.runTaskLater(plugin, 1L);
    }
}
