package pl.chudziudgi.core.feature.deposit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.config.PluginConfiguration;

public class DepositTask extends BukkitRunnable {

    public DepositTask(final ChCore plugin) {
        this.runTaskTimerAsynchronously(plugin, 20L, 20L * 2L);
    }


    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            final PluginConfiguration config = new PluginConfiguration();
            DepositUtil.processMaterial(player, Material.ENCHANTED_GOLDEN_APPLE, config.depositSettings.enchantedGoldenAppleLimit, 1);
            DepositUtil.processMaterial(player, Material.GOLDEN_APPLE, config.depositSettings.goldenAppleLimit, 2);
            DepositUtil.processMaterial(player, Material.ENDER_PEARL, config.depositSettings.enderPearlLimit, 3);
            DepositUtil.processMaterial(player, Material.ARROW, config.depositSettings.arrowLimit, 4);
            DepositUtil.processMaterial(player, Material.TOTEM_OF_UNDYING, config.depositSettings.totemOfUndyingLimit, 5);
            DepositUtil.processMaterial(player, Material.CHORUS_FRUIT, config.depositSettings.chorusLimit, 6);
            DepositUtil.processMaterial(player, Material.PACKED_ICE, config.depositSettings.iceLimit, 7);
        });
    }
}

