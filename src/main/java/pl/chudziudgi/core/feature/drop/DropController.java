package pl.chudziudgi.core.feature.drop;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.combat.CombatManager;

public class DropController implements Listener {
    private final CombatManager combatManager;

    public DropController(CombatManager combatManager) {
        this.combatManager = combatManager;
    }


    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlock();

        if (combatManager.inCombat(player)) return;

        if (player.getGameMode() == GameMode.SURVIVAL) {
            DropManager.onMine(player, block, player.getItemInHand(), event);
        }
    }
}
