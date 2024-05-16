package pl.chudziudgi.core.feature.protection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.TimeEnum;

public class ProtectionController implements Listener {
    private final ProtectionManager protectionManager;

    public ProtectionController(final ChCore plugin, ProtectionManager protectionManager) {
        this.protectionManager = protectionManager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!UserManager.isExists(event.getPlayer())) {
            protectionManager.giveProtection(event.getPlayer(), TimeEnum.MINUTE, 2);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        this.protectionManager.giveProtection(event.getPlayer(), TimeEnum.SECOND, 15);
    }

    @EventHandler
    public void onDamager(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player victim)) {
            return;
        }
        Player damager = getDamager(event);
        if (isProtected(victim)) {
            event.setCancelled(true);
            if (damager != null) {
                ChatUtil.error(damager, "Ten gracz posiada ochronę!");
            }
            return;
        }

        if (damager != null && isProtected(damager)) {
            event.setCancelled(true);
            ChatUtil.error(victim, "Posiadasz jeszcze ochronę!");
        }
    }

    private Player getDamager(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            return (Player) event.getDamager();
        }
        return null;
    }


    private boolean isProtected(final Player p) {
        return this.protectionManager.hasProtection(p);
    }
}
