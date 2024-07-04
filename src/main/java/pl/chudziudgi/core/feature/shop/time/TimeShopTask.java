package pl.chudziudgi.core.feature.shop.time;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
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
        this.runTaskTimerAsynchronously(plugin, 0, 300 * 20L);
    }


    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            User user = UserManager.get(player);
            if (!UserManager.isExists(player)) return;

            int amount = 1;
            user.timeCoin = user.timeCoin + amount;

            if (combatManager.inCombat(player)) return;
            if (protectionManager.hasProtection(player)) return;
            if (!user.timeMessage) return;
            ChatUtil.sendActionbar(player, "&3✈ &7Otrzymałeś &3&nonete&7 czasu");
            player.playSound(player, Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 5, 5);
        });
    }
}
