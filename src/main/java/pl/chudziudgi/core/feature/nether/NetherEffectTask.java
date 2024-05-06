package pl.chudziudgi.core.feature.nether;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;

public class NetherEffectTask extends BukkitRunnable {

    public NetherEffectTask(final ChCore plugin) {
        runTaskTimer(plugin, 0, 20*4);
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20 * 4, 0));
            }
        });
    }
}