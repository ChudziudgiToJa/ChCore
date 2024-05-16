package pl.chudziudgi.core.feature.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.settings.incognito.IncognitoManager;
import pl.chudziudgi.core.feature.vanish.VanishManager;
import pl.chudziudgi.core.util.ChatUtil;

public class PlayerJoinQuitListener implements Listener {

    private final IncognitoManager incognitoManager;
    private final VanishManager vanishManager;
    private final ChCore plugin;

    public PlayerJoinQuitListener(final ChCore plugin, IncognitoManager incognitoManager, VanishManager vanishManager) {
        this.incognitoManager = incognitoManager;
        this.vanishManager = vanishManager;
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (UserManager.isExists(player)) {
                ChatUtil.success(player, "Wczytano twój profil. &3✔");
            } else {
                UserManager.createUser(new User(player.getUniqueId()));
                player.kickPlayer(ChatUtil.fixColor("&aTwóje konto zostało stworzone\n\n&b&lDołącz ponownie!"));
                return;
            }
            vanishManager.onJoin(player);
            incognitoManager.isIncognito(player);
        }, 5);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
        Player player = event.getPlayer();
    }
}
