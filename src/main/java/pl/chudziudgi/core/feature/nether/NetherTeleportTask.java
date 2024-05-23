package pl.chudziudgi.core.feature.nether;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.randomtp.RandomUtil;

public class NetherTeleportTask extends BukkitRunnable {
    private final NetherConfig netherConfig;

    public NetherTeleportTask(ChCore plugin, NetherConfig netherConfig) {
        this.netherConfig = netherConfig;
        runTaskTimer(plugin, 20L, 20L);
    }

    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.getWorld().getEnvironment() == World.Environment.NETHER && !this.netherConfig.isNetherStatus())
                player.teleport(RandomUtil.getRandomCords(0));
        });
    }
}
