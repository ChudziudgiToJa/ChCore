package pl.chudziudgi.core.feature.randomtp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.combat.CombatManager;
import pl.chudziudgi.core.util.ChatUtil;

public class RandomTpController implements Listener {

    private final ChCore plugin;

    public RandomTpController(final ChCore plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDead(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.spigot().respawn();
            player.teleport(RandomUtil.getRandomCords(0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 2 * 20, 10));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 5 * 20, 1));
        }, 1);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Location location = RandomUtil.getRandomCords(0);
        if (!UserManager.isExists(event.getPlayer())) {
            event.getPlayer().teleport(location);
        }
    }
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.setCancelled(true);
        }
    }
}