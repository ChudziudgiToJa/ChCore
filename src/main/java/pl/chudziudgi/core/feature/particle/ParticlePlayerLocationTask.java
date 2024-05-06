package pl.chudziudgi.core.feature.particle;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.database.UserManager;
import pl.chudziudgi.core.util.LocationUtil;

public class ParticlePlayerLocationTask extends BukkitRunnable {

    public ParticlePlayerLocationTask(final ChCore plugin) {
        runTaskTimer(plugin, 0L, 20 * 10L);
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            User user = UserManager.getUser(player);

<<<<<<< HEAD
            if (LocationUtil.fromStringToLocation(user.particleLocation) != null) {
                user.particleLocation = LocationUtil.fromLocationToString(player.getLocation());
            }

            if (!player.getLocation().equals(LocationUtil.fromStringToLocation(user.getParticleLocation()))) {
                user.setParticleLocation(LocationUtil.fromLocationToString(player.getLocation()));
            }
        });
    }
=======
            if (!player.getLocation().equals(LocationUtil.deserialize(user.getParticleLocation()))) {
                user.setParticleLocation(LocationUtil.serialize(player.getLocation()));

            }
        });
    }

>>>>>>> origin/master
}

