package pl.chudziudgi.core.feature.deposit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;

public class DepositTask extends BukkitRunnable {

    public DepositTask(final ChCore plugin) {
        this.runTaskTimerAsynchronously(plugin, 20L, 20L * 2L);
    }


    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            final DepositConfig depositConfig = new DepositConfig();
            DepositUtil.processMaterial(player, Material.ENCHANTED_GOLDEN_APPLE, depositConfig.enchantedGoldenAppleLimit, 1);
            DepositUtil.processMaterial(player, Material.GOLDEN_APPLE, depositConfig.goldenAppleLimit, 2);
            DepositUtil.processMaterial(player, Material.ENDER_PEARL, depositConfig.enderPearlLimit, 3);
            DepositUtil.processMaterial(player, Material.ARROW, depositConfig.arrowLimit, 4);
            DepositUtil.processMaterial(player, Material.TOTEM_OF_UNDYING, depositConfig.totemOfUndyingLimit, 5);
            DepositUtil.processMaterial(player, Material.CHORUS_FRUIT, depositConfig.chorusLimit, 6);
            DepositUtil.processMaterial(player, Material.PACKED_ICE, depositConfig.iceLimit, 7);
        });
    }
}

