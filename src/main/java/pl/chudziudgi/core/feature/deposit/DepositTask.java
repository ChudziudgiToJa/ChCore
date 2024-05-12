package pl.chudziudgi.core.feature.deposit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.database.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class DepositTask extends BukkitRunnable {

    public DepositTask(final ChCore plugin) {
        this.runTaskTimerAsynchronously(plugin, 20L, 20L * 2L);
    }

    public String message(final Material material, final int amount) {
        String item = material.toString().toLowerCase();
        return new MessageBuilder().setText("Zabrano nadmiar {ITEM} &8(x{AMOUNT}&8) &7do schowka.")
                .addField("{ITEM}", item)
                .addField("{AMOUNT}", String.valueOf(amount))
                .build();
    }


    @Override
    public void run() {
        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            final DepositConfig depositConfig = new DepositConfig();
            final User user = UserManager.getUser(onlinePlayer);

            if (DepositUtil.getAmount(onlinePlayer, Material.ENCHANTED_GOLDEN_APPLE) > depositConfig.enchantedGoldenAppleLimit) {
                final int amount = DepositUtil.remove(onlinePlayer, Material.ENCHANTED_GOLDEN_APPLE, depositConfig.enchantedGoldenAppleLimit);
                user.dEnchantedGoldenApple += amount;
                ChatUtil.info(onlinePlayer, message(Material.ENCHANTED_GOLDEN_APPLE, amount));
            }
            if (DepositUtil.getAmount(onlinePlayer, Material.GOLDEN_APPLE) > depositConfig.goldenAppleLimit) {
                final int amount = DepositUtil.remove(onlinePlayer, Material.GOLDEN_APPLE, depositConfig.goldenAppleLimit);
                user.dGoldenApple += amount;
                ChatUtil.info(onlinePlayer, message(Material.GOLDEN_APPLE, amount));
            }
            if (DepositUtil.getAmount(onlinePlayer, Material.ENDER_PEARL) > depositConfig.enderPearlLimit) {
                final int amount = DepositUtil.remove(onlinePlayer, Material.ENDER_PEARL, depositConfig.enderPearlLimit);
                user.dEnderPearl += amount;
                ChatUtil.info(onlinePlayer, message(Material.ENDER_PEARL, amount));
            }

            if (DepositUtil.getAmount(onlinePlayer, Material.ARROW) > depositConfig.arrowLimit) {
                final int amount = DepositUtil.remove(onlinePlayer, Material.ARROW, depositConfig.arrowLimit);
                user.dArrow += amount;
                ChatUtil.info(onlinePlayer, message(Material.ARROW, amount));
            }
            if (DepositUtil.getAmount(onlinePlayer, Material.TOTEM_OF_UNDYING) > depositConfig.totemOfUndyingLimit) {
                final int amount = DepositUtil.remove(onlinePlayer, Material.TOTEM_OF_UNDYING, depositConfig.totemOfUndyingLimit);
                user.dTotemOfUndying += amount;
                ChatUtil.info(onlinePlayer, message(Material.TOTEM_OF_UNDYING, amount));
            }
            if (DepositUtil.getAmount(onlinePlayer, Material.CHORUS_FRUIT) > depositConfig.chorusLimit) {
                final int amount = DepositUtil.remove(onlinePlayer, Material.CHORUS_FRUIT, depositConfig.chorusLimit);
                user.dChorus += amount;
                ChatUtil.info(onlinePlayer, message(Material.CHORUS_FRUIT, amount));
            }
            break;
        }
    }
}

