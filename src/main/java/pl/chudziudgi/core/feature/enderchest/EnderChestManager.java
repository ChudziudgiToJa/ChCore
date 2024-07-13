package pl.chudziudgi.core.feature.enderchest;

import org.bukkit.entity.Player;

public class EnderChestManager {

    public int getSize(Player player) {
        if (player.hasPermission("core.enderchest.gold")) return 9*5;
        if (player.hasPermission("core.enderchest.iron")) return 9*4;
        return 9*3;
    }
}
