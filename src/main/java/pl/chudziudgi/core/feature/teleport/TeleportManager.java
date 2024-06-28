package pl.chudziudgi.core.feature.teleport;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.combat.CombatManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportManager {
    public static final Map<UUID, TeleportTask> teleportTaskHashMap = new HashMap<>();

    public Map<UUID, TeleportTask> getTeleportTaskHashMap() {
        return teleportTaskHashMap;
    }


    public static void startTeleportation(Player player, Location targetLocation, final ChCore plugin) {
        if (player.hasPermission("core.teleport.admin")) {
            teleportPlayer(player, targetLocation);
            return;
        }

        UUID playerId = player.getUniqueId();

        int countdownSeconds = 10;

        if (player.hasPermission("core.teleport.5sek")) {
            countdownSeconds = 5;
        }

        if (teleportTaskHashMap.containsKey(playerId)) {
            ChatUtil.error(player, "Jesteś już podczas teleportacji.");
            return;
        }

        TeleportTask task = new TeleportTask(player, targetLocation, countdownSeconds);
        teleportTaskHashMap.put(playerId, task);
        task.runTaskTimer(plugin, 0, 20);
    }

    public static void cancelTeleportation(Player player) {
        UUID playerId = player.getUniqueId();
        TeleportTask task = teleportTaskHashMap.remove(playerId);

        if (task != null) {
            task.cancel();

        }
    }

    public static void teleportPlayer(Player player, Location targetLocation) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 10));
        player.teleport(targetLocation);
        teleportTaskHashMap.remove(player.getUniqueId());
    }
}