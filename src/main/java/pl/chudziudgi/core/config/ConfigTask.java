package pl.chudziudgi.core.config;

import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;

public class ConfigTask extends BukkitRunnable {
    private final ConfigLoader configLoader;

    public ConfigTask(final ChCore plugin, ConfigLoader configLoader) {
        this.configLoader = configLoader;
        this.runTaskTimerAsynchronously(plugin, 0, 20L * 300);
    }

    @Override
    public void run() {
        configLoader.getChatConfig().save();
        configLoader.getCombatConfig().save();
        configLoader.getDropConfig().save();
        configLoader.getKitConfig().save();
        configLoader.getDepositConfig().save();
        configLoader.getCustomItemConfig().save();
        configLoader.getRandomTpConfig().save();
        configLoader.getProtectionConfig().save();
        configLoader.getBlockerConfig().save();
        configLoader.getTimeShopConfig().save();
    }
}