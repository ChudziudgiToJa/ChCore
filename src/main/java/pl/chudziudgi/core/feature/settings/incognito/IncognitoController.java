package pl.chudziudgi.core.feature.settings.incognito;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.UserManager;

public class IncognitoController implements Listener {

    private final IncognitoManager incognitoManager;

    public IncognitoController(final ChCore plugin, IncognitoManager incognitoManager) {
        this.incognitoManager = incognitoManager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
            incognitoManager.isIncognito(event.getPlayer());
    }
}
