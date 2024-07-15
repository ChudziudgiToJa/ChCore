package pl.chudziudgi.core.database.user;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.drop.Drop;
import pl.chudziudgi.core.feature.drop.DropConfig;
import pl.chudziudgi.core.util.ChatUtil;

public class UserController implements Listener {

    public UserController(final ChCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
        Player player = event.getPlayer();

        try {
            if (UserManager.isExists(player)) {
                ChatUtil.success(player, "Wczytano twój profil. &3✔");
            } else {
                UserManager.createUser(new User(player.getUniqueId()));
                ChatUtil.success(player, "Utworzono nowy profil. &3✔");
            }
        } catch (Exception e) {
            player.kickPlayer(ChatUtil.fixColor("&7Wystąpił błąd podczas wczytywania twojego profilu.\n&4Proszę spróbuj ponownie później.\n&7Dalej nie działą? wbij na naszego discorda &3discord.klanmc.pl"));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
    }
}
