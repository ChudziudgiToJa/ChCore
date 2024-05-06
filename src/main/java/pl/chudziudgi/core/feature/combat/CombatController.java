package pl.chudziudgi.core.feature.combat;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.feature.ochrona.ProtectionManager;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.TimeEnum;

import java.util.List;
import java.util.Random;

public class CombatController implements Listener {

    private final CombatManager combatManager;
    private final CombatConfig config;
    private final ProtectionManager protectionManager;

    public CombatController(final ChCore plugin, CombatManager combatManager, CombatConfig config, ProtectionManager protectionManager) {
        this.combatManager = combatManager;
        this.config = config;
        this.protectionManager = protectionManager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static Player getDamager(final EntityDamageByEntityEvent e) {
        final Entity damager = e.getDamager();
        if (damager instanceof Player) {
            return (Player) damager;
        }
        if (damager instanceof Projectile p) {
            if (p.getShooter() instanceof Player) {
                return (Player) p.getShooter();
            }
        }
        return null;
    }

    @EventHandler
    public void onRespawn(final PlayerRespawnEvent event) {
        if (this.combatManager.inCombat(event.getPlayer())) {
            this.combatManager.removeCombat(this.combatManager.getCombat(event.getPlayer()));
        }
    }

    private void leave(final Player player) {
        if (combatManager.inCombat(player)) {
            player.setHealth(0.0D);
            Bukkit.getOnlinePlayers().forEach(all -> ChatUtil.info(all, config.getLogautMessage().replace("{PLAYER}", player.getName())));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        leave(event.getPlayer());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (!combatManager.inCombat(player)) return;

        if (block != null && combatManager.isBlockedBlock(block.getType())) {
            event.setCancelled(true);
            ChatUtil.error(player, "Nie możesz tego zrobić podczas walki!");
        }
    }

    @EventHandler
    public void onCmd(final PlayerCommandPreprocessEvent event) {
        final String command = event.getMessage().split(" ")[0].toLowerCase();
        final Player player = event.getPlayer();

        if (player.hasPermission("core.combat.admin")) return;

        if (combatManager.inCombat(player)) {
            if (!this.config.getCommandsList().contains(command)) {
                event.setCancelled(true);
                ChatUtil.error(player, config.getCommandBlockMessage());
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamage(final EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof Player)) return;

        Object damager = event.getDamager();

        if (damager instanceof Projectile projectile) {
            if (projectile.getShooter() instanceof Player) {
                damager = projectile.getShooter();
            }
        }
        if (damager instanceof Player playerDamager && entity != damager) {
            Player playerEntity = (Player) entity;


            combatManager.createCombat(playerEntity, TimeEnum.SECOND, 30);
            combatManager.createCombat(playerDamager, TimeEnum.SECOND, 30);
        }
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deadPlayer = event.getEntity();
        Player killer = deadPlayer.getKiller();

        if (killer != null) {
            ChatUtil.sendTitle(deadPlayer, "", "&7Zabójca: &f" + killer.getName() + " &7na &f" + String.format("%.1f", killer.getHealth()) + " &4❤", 10, 15, 10);
        } else {
            List<String> commandsList = config.getTitleList();
            if (!commandsList.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(commandsList.size());
                String randomCommand = commandsList.get(randomIndex);
                ChatUtil.sendTitle(deadPlayer, "&cZginąłeś!", randomCommand, 10, 15, 10);
                return;
            }
            ChatUtil.sendTitle(deadPlayer, "", "&cZginąłeś! ", 10, 15, 10);
        }
    }

    @EventHandler
    public void onBow(EntityDamageByEntityEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            if (!(e.getDamager() instanceof Arrow arrow) || !(e.getEntity() instanceof Player target)) return;
            if (!(arrow.getShooter() instanceof Player shooter)) return;
            if (e.getEntity() == shooter || protectionManager.hasProtection((OfflinePlayer) shooter)) return;

            double newHealth = target.getHealth() - e.getFinalDamage();
            if (newHealth <= 0) return;

            String message = new MessageBuilder()
                    .setText("&7{player} &6{health}&4❤")
                    .addField("{player}", target.getName())
                    .addField("{health}", String.format("%.1f", newHealth))
                    .build();

            ChatUtil.sendTitle(shooter, "", message, 5, 10, 5);
        }
    }
}

