package pl.chudziudgi.core.feature.particle;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.database.UserManager;
import pl.chudziudgi.core.util.LocationUtil;

public class ParticleTask extends BukkitRunnable {

    public ParticleTask(final ChCore plugin) {
        runTaskTimer(plugin, 0L, 20L);
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            User user = UserManager.getUser(player);
<<<<<<< HEAD
            if (!user.particleStatus) return;
            if (player.getLocation() == LocationUtil.fromStringToLocation(user.getParticleLocation())) {
=======
            if (!user.isParticleStatus()) return;
            if (player.getLocation() == LocationUtil.deserialize(user.getParticleLocation())) {
>>>>>>> origin/master
                for (int i = 0; i < 5; i++) {
                    player.spawnParticle(Particle.SOUL, player.getLocation().add(0, 1.4, 0), 1, 0.4, 0.4, 0.4, 0);
                }
            }
        });
    }
}
