package pl.chudziudgi.core.feature.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.drop.DropManager;
import pl.chudziudgi.core.feature.settings.incognito.IncognitoManager;
import pl.chudziudgi.core.feature.vanish.VanishManager;
import pl.chudziudgi.core.util.ChatUtil;

public class PlayerJoinQuitListener implements Listener {

    private final IncognitoManager incognitoManager;
    private final VanishManager vanishManager;

    public PlayerJoinQuitListener(final ChCore plugin, IncognitoManager incognitoManager, VanishManager vanishManager) {
        this.incognitoManager = incognitoManager;
        this.vanishManager = vanishManager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
        Player player = event.getPlayer();
        User user = UserManager.getUser(player);
        vanishManager.onJoin(player);

        if (user.incognito) {
            incognitoManager.setIncognito(player);
            ChatUtil.info(player, "Twoje incognito jest nadal &aAktywne");
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
        Player player = event.getPlayer();
    }
}
