package pl.chudziudgi.core.feature.nether;

import java.util.Calendar;
import java.util.TimeZone;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;

public class NetherStatusTask extends BukkitRunnable {
    private final NetherConfig netherConfig;

    public NetherStatusTask(ChCore plugin, NetherConfig netherConfig) {
        this.netherConfig = netherConfig;
        runTaskTimer(plugin, 0L, 600L);
    }

    public void run() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Warsaw"));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String currentTime = String.format("%02d:%02d", hour, minute);
        if (currentTime.equals(this.netherConfig.getNetherStartTime())) {
            this.netherConfig.setNetherStatus(true);
            return;
        }
        if (currentTime.equals(this.netherConfig.getNetherEndTime()))
            this.netherConfig.setNetherStatus(false);
    }
}
