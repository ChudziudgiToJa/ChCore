package pl.chudziudgi.core.feature.nether;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.randomtp.RandomUtil;

public class NetherTeleportTaskT extends BukkitRunnable {
    private final NetherConfig netherConfig;

    public NetherTeleportTaskT(ChCore plugin, NetherConfig netherConfig) {
        this.netherConfig = netherConfig;
        runTaskTimer((Plugin)plugin, 20L, 20L);
    }

    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.getWorld().getEnvironment() == World.Environment.NETHER && !this.netherConfig.isNetherStatus())
                player.teleport(RandomUtil.getRandomCords(0));
        });
    }
}
