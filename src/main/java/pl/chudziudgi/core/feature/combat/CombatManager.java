package pl.chudziudgi.core.feature.combat;

import com.google.common.collect.Sets;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.util.TimeEnum;

import java.util.Arrays;
import java.util.Set;

public class CombatManager {

    private final Set<Combat> combatSet;

    public CombatManager() {
        this.combatSet = Sets.newLinkedHashSet();
    }

    public void createCombat(final Player player, TimeEnum timeEnum, final int time) {
        final Combat combat = this.getCombat(player);
        if (combat != null) {
            this.combatSet.remove(combat);
        }
        this.combatSet.add(new Combat(player.getUniqueId(), System.currentTimeMillis() + timeEnum.getTime(time + 1)));
    }

    public boolean inCombat(Player player) {
        final Combat combat = getCombat(player);
        if (combat == null) {
            return false;
        }
        if (combat.getLeftTime() > 0) {
            return true;
        }
        this.removeCombat(combat);
        return false;
    }

    public void removeCombat(Combat combat) {
        if (this.getCombat(combat.getPlayer()) != null) {
            combatSet.remove(combat);
        }
    }

    public Combat getCombat(final Player player) {
        return this.combatSet.stream().filter(combat -> combat.getIdentifier().equals(player.getUniqueId())).findFirst().orElse(null);
    }

    public boolean isBlockedBlock(Material material) {
        return Arrays.asList(Material.TRAPPED_CHEST, Material.CHEST, Material.CRAFTING_TABLE, Material.ENDER_CHEST, Material.FURNACE, Material.SMOKER, Material.BLAST_FURNACE, Material.DISPENSER, Material.DROPPER, Material.BARREL).contains(material);
    }
}
