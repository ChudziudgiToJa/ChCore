package pl.chudziudgi.core.feature.combat;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.DataUtils;

public class CombatTask extends BukkitRunnable {

    private final CombatManager combatManager;
    private final PluginConfiguration config;

    public CombatTask(final ChCore plugin, CombatManager combatManager, PluginConfiguration config) {
        this.combatManager = combatManager;
        this.config = config;
        runTaskTimerAsynchronously(plugin, 20L, 20L);
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            final Combat combat = combatManager.getCombat(player);
            if (combat != null) {
                if (combatManager.inCombat(player)) {
                    String message = new MessageBuilder().setText(this.config.combatSettings.COMBAT_MESSAGE).addField("{TIME}", DataUtils.durationToString(combat.getLeftTime())).build();
                    ChatUtil.sendActionbar(combat.getPlayer(), message);
                    combat.setLeftTime(combat.getLeftTime() - 20L);
                    if (combat.getLeftTime() < System.currentTimeMillis()) {
                        ChatUtil.sendActionbar(combat.getPlayer(), this.config.combatSettings.COMBAT_END_MESSAGE);
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 10 ,10);
                        combatManager.removeCombat(combat);
                    }
                }
            }
        });
    }
}
