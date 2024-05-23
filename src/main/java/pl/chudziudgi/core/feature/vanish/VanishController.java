package pl.chudziudgi.core.feature.vanish;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;

public class VanishController implements Listener {

    private final VanishManager vanishManager;

    public VanishController(final ChCore plugin, VanishManager vanishManager) {
        this.vanishManager = vanishManager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (UserManager.isExists(event.getPlayer())) {
            vanishManager.onJoin(event.getPlayer());
        }
    }
}
