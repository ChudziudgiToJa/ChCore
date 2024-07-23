package pl.chudziudgi.core.feature.combat;

import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.user.User;
import net.dzikoysk.funnyguilds.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import panda.std.Option;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.protection.ProtectionManager;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.TimeEnum;

import java.sql.Array;
import java.util.Arrays;

public class CombatController implements Listener {

    private final CombatManager combatManager;
    private final CombatConfig config;
    private final ProtectionManager protectionManager;
    private final FunnyGuilds funnyGuilds;

    public CombatController(final ChCore plugin, CombatManager combatManager, CombatConfig config, ProtectionManager protectionManager, FunnyGuilds funnyGuilds) {
        this.combatManager = combatManager;
        this.config = config;
        this.protectionManager = protectionManager;
        this.funnyGuilds = funnyGuilds;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
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
        UserManager userManager = funnyGuilds.getUserManager();

        if (!(entity instanceof Player)) return;

        Object damager = event.getDamager();

        if (damager instanceof Projectile projectile) {
            if (projectile.getShooter() instanceof Player) {
                damager = projectile.getShooter();
            }
        }

        if (damager instanceof Player playerDamager && entity != damager) {
            Option<User> userDamager = userManager.findByPlayer(playerDamager);
            Option<User> user = userManager.findByPlayer((Player) entity);
            Player playerEntity = (Player) entity;

            if (user.isPresent() && userDamager.isPresent() &&
                    user.get().getGuild().isPresent() &&
                    userDamager.get().getGuild().isPresent() &&
                    user.get().getGuild().get().equals(userDamager.get().getGuild().get())) {
                return;
            }

            if (protectionManager.hasProtection(playerEntity)) return;

            if (!playerEntity.hasPermission("core.combat.admin")) {
                combatManager.createCombat(playerEntity, TimeEnum.SECOND, 30);
            }
            if (!playerDamager.hasPermission("core.combat.admin")) {
                combatManager.createCombat(playerDamager, TimeEnum.SECOND, 30);
            }
        }
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deadPlayer = event.getEntity();
        Player killer = deadPlayer.getKiller();
        if (killer != null) {
            ChatUtil.sendTitle(deadPlayer, "", "&7Zabójca: &f" + killer.getDisplayName() + " &7na &f" + String.format("%.1f", killer.getHealth()) + " &4❤", 10, 40, 10);
            return;
        }
        ChatUtil.sendTitle(deadPlayer, "", "&cZginąłeś! ", 10, 40, 10);
    }


    @EventHandler()
    public void onBow(EntityDamageByEntityEvent e) {
        if (e.isCancelled()) return;
        if (!(e.getDamager() instanceof Projectile projectile)) return;
        if (!(projectile.getShooter() instanceof Player shooter)) return;
        if (!(e.getEntity() instanceof Player victim)) return;
        if ((e.getDamager() instanceof Player)) return;

        if (victim.equals(shooter) || protectionManager.hasProtection(victim) || protectionManager.hasProtection(shooter))
            return;

        double victimHealth = victim.getHealth();

        if (victimHealth <= 0 || victim.isDead()) return;


        String message = "&7" + String.format("%.1f", victimHealth) + "&4❤";
        shooter.playSound(shooter, Sound.BLOCK_CANDLE_HIT, 5, 5);
        ChatUtil.sendTitle(shooter, "", message, 5, 20, 5);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof Player player) {
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            if (isAxe(itemInHand.getType())) {
                event.setDamage(event.getDamage() / 2);
            }
        }
    }
    public static boolean isAxe(Material material) {
        return Arrays.asList(Material.DIAMOND_AXE, Material.NETHERITE_AXE, Material.IRON_AXE, Material.GOLDEN_AXE, Material.STONE_AXE, Material.WOODEN_AXE).contains(material);
    }
}

