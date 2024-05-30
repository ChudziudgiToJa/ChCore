package pl.chudziudgi.core.feature.worldborder;

import org.bukkit.Location;
import org.bukkit.Sound;
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
    public void onMove(final PlayerMoveEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        int worldSize = getWorldSize(world);

        if (Math.abs(Objects.requireNonNull(event.getTo()).getBlockX()) > worldSize - 3 || Math.abs(event.getTo().getBlockZ()) > worldSize - 3) {
            ChatUtil.error(player, "Osiągnąłeś granicę świata! &3(" + worldSize + " kratek)");
            knock(player);
            event.setTo(event.getFrom());
            return;
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (findByBorder(event.getBlock().getLocation(), 20)) {
            event.setCancelled(true);
            ChatUtil.error(event.getPlayer(), "Nie możesz kopać bloków przy granicy mapy!");
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (findByBorder(event.getBlock().getLocation(), 20)) {
            event.setCancelled(true);
            ChatUtil.error(event.getPlayer(), "Nie możesz stawiać bloków przy granicy mapy!");
        }
    }

    public void knock(Player p) {
        Location l = p.getLocation().subtract(p.getWorld().getSpawnLocation());
        double distance = p.getLocation().distance(p.getWorld().getSpawnLocation());
        Vector v = l.toVector().add(new Vector(0, 5, 0)).multiply(3 / distance);
        p.setVelocity(v.multiply(1.5));
        p.playSound(p, Sound.BLOCK_ANVIL_HIT, 10, 10);
    }

    public boolean findByBorder(final Location loc, int i) {
        int worldSize = getWorldSize(Objects.requireNonNull(loc.getWorld()));
        return Math.abs(worldSize - loc.getBlockX()) < i || Math.abs(worldSize - loc.getBlockZ()) < i || Math.abs(-worldSize - loc.getBlockX()) < i || Math.abs(-worldSize - loc.getBlockZ()) < i;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerTeleport(final PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        int worldSize = getWorldSize(world);

        if (Objects.requireNonNull(event.getTo()).getX() > worldSize || event.getTo().getX() < -worldSize || event.getTo().getZ() > worldSize || event.getTo().getZ() < -worldSize) {
            ChatUtil.error(event.getPlayer(), "Osiągnąłeś granicę świata! &3(" + getWorldSize(event.getPlayer().getWorld()) + " kratek)");
            event.setCancelled(true);
            return;
        }
    }

    private int getWorldSize(World world) {
        if (world.getEnvironment() == World.Environment.NETHER) {
            return config.getNetherSize();
        } else {
            return config.getWorldSize();
        }
    }
}
