package pl.chudziudgi.core.database.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.settings.incognito.IncognitoManager;
import pl.chudziudgi.core.feature.vanish.VanishManager;
import pl.chudziudgi.core.util.ChatUtil;

public class UserController implements Listener {

    private final ChCore plugin;

    public UserController(final ChCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
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
            }
        }, 5);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
        Player player = event.getPlayer();
    }
}
