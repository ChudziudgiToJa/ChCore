package pl.chudziudgi.core.config;

import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;

public class PluginConfigurationTask extends BukkitRunnable {
    private final ConfigurationLoader configurationLoader;

    public PluginConfigurationTask(final ChCore plugin, ConfigurationLoader configurationLoader) {
        this.configurationLoader = configurationLoader;
        this.runTaskTimerAsynchronously(plugin, 0, 20L * 1200);
    }

    @Override
    public void run() {
        configurationLoader.save();
    }
}