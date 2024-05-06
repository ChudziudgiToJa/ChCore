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
        runTaskTimer((Plugin)plugin, 0L, 600L);
    }

    public void run() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Warsaw"));
        int hour = calendar.get(11);
        int minute = calendar.get(12);
        String currentTime = String.format("%02d:%02d", new Object[] { Integer.valueOf(hour), Integer.valueOf(minute) });
        if (currentTime.equals(this.netherConfig.getNetherStartTime())) {
            this.netherConfig.setNetherStatus(true);
            return;
        }
        if (currentTime.equals(this.netherConfig.getNetherEndTime()))
            this.netherConfig.setNetherStatus(false);
    }
}
