package pl.chudziudgi.core.feature.combat;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.DataUtils;

public class CombatTask extends BukkitRunnable {

    private final CombatManager combatManager;

    public CombatTask(final ChCore plugin, CombatManager combatManager) {
        this.combatManager = combatManager;
        runTaskTimerAsynchronously(plugin, 20L, 20L);
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            final Combat combat = combatManager.getCombat(player);
            if (combat != null) {
                if (combatManager.inCombat(player)) {
                    ChatUtil.sendActionbar(combat.getPlayer(), "&6⌚ AntyLogout &7pozostalo &e" + DataUtils.durationToString(combat.getLeftTime()));
                    combat.setLeftTime(combat.getLeftTime() - 20L);
                    if (combat.getLeftTime() < System.currentTimeMillis()) {
                        ChatUtil.sendActionbar(combat.getPlayer(), "&6⌚ AntyLogout &edobiega konca");
                        combatManager.removeCombat(combat);
                    }
                }
            }
        });
    }
}
