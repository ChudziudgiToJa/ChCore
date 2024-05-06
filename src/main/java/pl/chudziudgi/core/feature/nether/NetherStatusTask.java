package pl.chudziudgi.core.feature.nether;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;

import java.util.Calendar;
import java.util.TimeZone;

public class NetherStatusTask extends BukkitRunnable {

    private final NetherConfig netherConfig;

    public NetherStatusTask(final ChCore plugin, NetherConfig netherConfig) {
        this.netherConfig = netherConfig;
<<<<<<< HEAD
        runTaskTimer(plugin, 0L, 20L * 30);
=======
        runTaskTimerAsynchronously(plugin, 0L, 20L * 30);
>>>>>>> origin/master
    }

    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Warsaw"));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String currentTime = String.format("%02d:%02d", hour, minute);

        if (currentTime.equals(netherConfig.getNetherStartTime())) {
            netherConfig.setNetherStatus(true);
            return;
        }

        if (currentTime.equals(netherConfig.getNetherEndTime())) {
            netherConfig.setNetherStatus(false);
        }
    }
}