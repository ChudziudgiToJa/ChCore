package pl.chudziudgi.core.feature.nether;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.randomtp.RandomUtil;

public class NetherTeleportTaskT extends BukkitRunnable {

    private final NetherConfig netherConfig;

    public NetherTeleportTaskT(final ChCore plugin, NetherConfig netherConfig) {
        this.netherConfig = netherConfig;
<<<<<<< HEAD
        runTaskTimer(plugin, 20L, 20L);
=======
        runTaskTimerAsynchronously(plugin, 20L, 20L);
>>>>>>> origin/master
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
                if (!netherConfig.isNetherStatus()) {
                    player.teleport(RandomUtil.getRandomCords(0));
                }
            }
        });
    }
}