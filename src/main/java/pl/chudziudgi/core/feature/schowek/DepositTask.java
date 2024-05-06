package pl.chudziudgi.core.feature.schowek;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.database.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class DepositTask extends BukkitRunnable {


    @Override
    public void run() {
        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            final DepositConfig depositConfig= new DepositConfig();
            final User user = UserManager.getUser(onlinePlayer);
            if (DepositUtil.getAmount(onlinePlayer, Material.ENCHANTED_GOLDEN_APPLE) > depositConfig.enchantedGoldenAppleLimit) {
                final int amount = DepositUtil.remove(onlinePlayer, Material.ENCHANTED_GOLDEN_APPLE, depositConfig.enchantedGoldenAppleLimit);
                user.dEnchantedGoldenApple += amount;
                ChatUtil.info(onlinePlayer, "zabrano nadmiar do depozytu");
            } else if (DepositUtil.getAmount(onlinePlayer, Material.GOLDEN_APPLE) > depositConfig.goldenAppleLimit) {
                final int amount = DepositUtil.remove(onlinePlayer, Material.GOLDEN_APPLE, depositConfig.goldenAppleLimit);
                user.dGoldenApple += amount;
                ChatUtil.info(onlinePlayer, "zabrano nadmiar do depozytu");
            } else if (DepositUtil.getAmount(onlinePlayer, Material.ENDER_PEARL) > depositConfig.enderPearlLimit) {
                final int amount = DepositUtil.remove(onlinePlayer, Material.ENDER_PEARL, depositConfig.enderPearlLimit);
                user.dEnderPearl += amount;
                ChatUtil.info(onlinePlayer, "zabrano nadmiar do depozytu");
            }
        }
    }
}

