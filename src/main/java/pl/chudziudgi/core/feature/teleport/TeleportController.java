package pl.chudziudgi.core.feature.teleport;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.chudziudgi.core.ChCore;

public class TeleportController implements Listener {

    private final TeleportManager teleportManager;

    public TeleportController(TeleportManager teleportManager) {
        this.teleportManager = teleportManager;
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        teleportManager.getTeleportTaskHashMap().remove(event.getPlayer().getUniqueId());
    }
}
