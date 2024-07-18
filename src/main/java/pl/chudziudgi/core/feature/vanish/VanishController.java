package pl.chudziudgi.core.feature.vanish;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;

public class VanishController implements Listener {

    private final VanishManager vanishManager;
    private final ChCore plugin;

    public VanishController(final ChCore plugin,VanishManager vanishManager) {
        this.vanishManager = vanishManager;
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onJoin(PlayerLoginEvent event) {
        if (UserManager.isExists(event.getPlayer())) {
            User user = UserManager.get(event.getPlayer());
            if (user == null) return;
            new BukkitRunnable() {
                @Override
                public void run() {
                    vanishManager.onJoin(event.getPlayer());
                }
            }.runTaskLater(plugin, 1L);
        }
    }
}
