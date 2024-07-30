package pl.chudziudgi.core.database.user;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.util.ChatUtil;

public class UserController implements Listener {

    private final ChCore plugin;

    public UserController(ChCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        try {
            if (UserManager.isExists(player)) {
                ChatUtil.success(player, "Wczytano twój profil. &3✔");
            } else {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        UserManager.createUser(new User(player.getUniqueId()));
                        player.kickPlayer(ChatUtil.fixColor("Utworzono nowy profil. &3✔"));
                    }
                }.runTaskLater(this.plugin, 1L);
            }
        } catch (Exception e) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.kickPlayer(ChatUtil.fixColor("&7Wystąpił błąd podczas wczytywania twojego profilu.\n&4Proszę spróbuj ponownie później.\n&7Dalej nie działą? wbij na naszego discorda &3discord.klanmc.pl"));
                }
            }.runTaskLater(this.plugin, 1L);
        }
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
    }
}
