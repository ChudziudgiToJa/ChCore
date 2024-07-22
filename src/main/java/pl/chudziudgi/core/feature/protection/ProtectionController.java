package pl.chudziudgi.core.feature.protection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.TimeEnum;

public class ProtectionController implements Listener {
    private final ProtectionManager protectionManager;

    public ProtectionController(final ChCore plugin, ProtectionManager protectionManager) {
        this.protectionManager = protectionManager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        this.protectionManager.giveProtection(event.getPlayer(), TimeEnum.MINUTE, 1);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (protectionManager.hasProtection(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player entityPlayer)) return;
        EntityDamageEvent.DamageCause cause = event.getCause();

        switch (cause) {
            case ENTITY_ATTACK:
            case ENTITY_SWEEP_ATTACK:
            case PROJECTILE:
                if (!(event instanceof EntityDamageByEntityEvent entityDamageByEntityEvent)) return;
                if (!(entityDamageByEntityEvent.getDamager() instanceof Player damagerPlayer)) return;

                if (protectionManager.hasProtection(entityPlayer)) {
                    if (damagerPlayer.hasPermission("core.ochrona.admin")) return;
                    event.setCancelled(true);
                    return;
                }

                if (protectionManager.hasProtection(damagerPlayer)) {
                    if (damagerPlayer.hasPermission("core.ochrona.admin")) return;
                    event.setCancelled(true);
                }
                break;
            case LAVA:
            case FALLING_BLOCK:
            case BLOCK_EXPLOSION:
            case ENTITY_EXPLOSION:
            case POISON:
            case DROWNING:
                if (protectionManager.hasProtection(entityPlayer)) {
                    event.setCancelled(true);
                }
                break;
            default:
                break;
        }
    }
}