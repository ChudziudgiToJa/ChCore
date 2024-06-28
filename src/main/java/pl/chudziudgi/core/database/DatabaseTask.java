package pl.chudziudgi.core.database;

import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;

public class DatabaseTask extends BukkitRunnable {

    public DatabaseTask(final ChCore plugin) {
        this.runTaskTimerAsynchronously(plugin, 0, 600);
    }

    @Override
    public void run() {
        Database.saveDatabase();
    }
}