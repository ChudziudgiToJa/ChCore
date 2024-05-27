package pl.chudziudgi.core.feature.shop.time;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.combat.CombatManager;
import pl.chudziudgi.core.feature.protection.ProtectionManager;
import pl.chudziudgi.core.util.ChatUtil;

public class TimeShopTask extends BukkitRunnable {

    private final CombatManager combatManager;
    private final ProtectionManager protectionManager;

    public TimeShopTask(final ChCore plugin, CombatManager combatManager, ProtectionManager protectionManager) {
        this.combatManager = combatManager;
        this.protectionManager = protectionManager;
        this.runTaskTimerAsynchronously(plugin, 0, 500 * 20L);
    }


    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            User user = UserManager.get(player);

            if (UserManager.isExists(player)) {
                int amount = 10;
                user.timeShop = user.timeShop + amount;

                if (combatManager.inCombat(player)) return;
                if (protectionManager.hasProtection(player)) return;
                ChatUtil.sendActionbar(player, "&3✈ &7Otrzymałeś &3" + amount + " &7monet czasu");
            }
        });
    }
}
