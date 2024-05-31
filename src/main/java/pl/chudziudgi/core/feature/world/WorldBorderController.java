package pl.chudziudgi.core.feature.world;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.util.Vector;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.randomtp.RandomTpConfig;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.Objects;

public class WorldBorderController implements Listener {

    private final RandomTpConfig config;

    public WorldBorderController(final ChCore plugin, RandomTpConfig config) {
        this.config = config;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerMove(final PlayerMoveEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        int worldSize = getWorldSize(world);

        Location to = event.getTo();
        if (to == null) {
            return;
        }

        if (isOutsideBorder(to, worldSize - 3)) {
            ChatUtil.error(player, "Osiągnąłeś granicę świata! &3(" + worldSize + " kratek)");

            Location from = event.getFrom();
            Vector pushBack = from.toVector().subtract(to.toVector()).normalize().multiply(2);
            player.setVelocity(pushBack);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerTeleport(final PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();

        if (to != null && isNearBorder(to)) {
            ChatUtil.error(player, "Nie możesz używać pereł przy granicy mapy!");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (isNearBorder(event.getBlock().getLocation())) {
            event.setCancelled(true);
            ChatUtil.error(event.getPlayer(), "Nie możesz kopać bloków przy granicy mapy!");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (isNearBorder(event.getBlock().getLocation())) {
            event.setCancelled(true);
            ChatUtil.error(event.getPlayer(), "Nie możesz stawiać bloków przy granicy mapy!");
        }
    }

    private boolean isOutsideBorder(final Location loc, int borderSize) {
        return Math.abs(loc.getBlockX()) > borderSize || Math.abs(loc.getBlockZ()) > borderSize;
    }

    private boolean isNearBorder(final Location loc) {
        int worldSize = getWorldSize(Objects.requireNonNull(loc.getWorld()));
        return Math.abs(worldSize - loc.getBlockX()) < 20 || Math.abs(worldSize - loc.getBlockZ()) < 20 || Math.abs(-worldSize - loc.getBlockX()) < 20 || Math.abs(-worldSize - loc.getBlockZ()) < 20;
    }

    private int getWorldSize(World world) {
        return (world.getEnvironment() == World.Environment.NETHER) ? config.getNetherSize() : config.getWorldSize();
    }
}
