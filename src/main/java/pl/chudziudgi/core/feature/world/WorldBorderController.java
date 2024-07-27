package pl.chudziudgi.core.feature.world;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.feature.randomtp.RandomTpConfig;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.Objects;

public class WorldBorderController implements Listener {

    private final PluginConfiguration config;

    public WorldBorderController(PluginConfiguration config) {
        this.config = config;
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(final PlayerMoveEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        int worldSize = getWorldSize(world);
        Location to = event.getTo();
        Location from = event.getFrom();

        if (to == null) return;

        if (isOutsideBorder(to, worldSize - 3)) {
            ChatUtil.error(player, "Osiągnąłeś granicę świata! &3(" + worldSize + " kratek)");
            player.playSound(event.getPlayer(), Sound.BLOCK_VINE_HIT, 5, 5);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 5));
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
            Vector pushBack = from.toVector().subtract(to.toVector());
            if (pushBack.lengthSquared() > 0) {
                pushBack = pushBack.normalize().multiply(1.1);
                try {
                    player.setVelocity(pushBack);
                } catch (IllegalArgumentException ignored) {
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerTeleport(final PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        World world = player.getWorld();

        if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
            if (to == null) return;
            if (isNearBorder(to) || isOutsideBorder(to, getWorldSize(world))) {
                ChatUtil.error(player, "Nie możesz używać pereł przy granicy mapy!");
                event.getPlayer().playSound(event.getPlayer(), Sound.BLOCK_VINE_HIT, 5, 5);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (isNearBorder(event.getBlock().getLocation())) {
            event.setCancelled(true);
            ChatUtil.error(event.getPlayer(), "Nie możesz kopać bloków przy granicy mapy!");
            event.getPlayer().playSound(event.getPlayer(), Sound.BLOCK_VINE_HIT, 5, 5);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (isNearBorder(event.getBlock().getLocation())) {
            event.setCancelled(true);
            ChatUtil.error(event.getPlayer(), "Nie możesz stawiać bloków przy granicy mapy!");
            event.getPlayer().playSound(event.getPlayer(), Sound.BLOCK_VINE_HIT, 5, 5);
        }
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        if (event.getItem().getType() == Material.CHORUS_FRUIT && isNearBorder(event.getPlayer().getLocation())) {
            event.setCancelled(true);
            ChatUtil.error(event.getPlayer(), "Nie możesz jeść chorusu przy granicy mapy");
            event.getPlayer().playSound(event.getPlayer(), Sound.BLOCK_VINE_HIT, 5, 5);
        }
    }


    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }

    private boolean isOutsideBorder(final Location loc, int borderSize) {
        return Math.abs(loc.getBlockX()) > borderSize || Math.abs(loc.getBlockZ()) > borderSize;
    }

    private boolean isNearBorder(final Location loc) {
        int worldSize = getWorldSize(Objects.requireNonNull(loc.getWorld()));
        return Math.abs(worldSize - loc.getBlockX()) < 20 || Math.abs(worldSize - loc.getBlockZ()) < 20 || Math.abs(-worldSize - loc.getBlockX()) < 20 || Math.abs(-worldSize - loc.getBlockZ()) < 20;
    }

    private int getWorldSize(World world) {
        return (world.getEnvironment() == World.Environment.NETHER) ? this.config.randomTpSettings.netherWorldSize : this.config.randomTpSettings.overWorldSize;
    }
}
