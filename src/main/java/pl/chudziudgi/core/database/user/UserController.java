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
    private final DropConfig dropConfig;
    private final ChCore plugin;

    public UserController(final ChCore plugin, DropConfig dropConfig) {
        this.dropConfig = dropConfig;
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
        Player player = event.getPlayer();

        if (UserManager.isExists(player)) {
            ChatUtil.success(player, "Wczytano twój profil. &3✔");
        } else {
            UserManager.createUser(new User(player.getUniqueId()));
            User user = UserManager.get(player);
            for (Drop drop : dropConfig.getOverWorldDropList()) {
                user.setOverWorldDropStatus(drop, true);
            }
            for (Drop drop : dropConfig.getNetherDropList()) {
                user.setNetherDropStatus(drop, true);
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.kickPlayer(ChatUtil.fixColor("&aTwóje konto zostało stworzone\n\n&b&lDołącz ponownie!"));
                }
            }.runTaskLater(plugin, 20L);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
    }
}
