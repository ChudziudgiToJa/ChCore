package pl.chudziudgi.core.feature.teleport;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.feature.combat.CombatManager;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.DataUtils;

public class TeleportTask extends BukkitRunnable {

    private final Player player;
    private long remainingSeconds;
    private final Location initialLocation;
    private final Location target;

    public TeleportTask(Player player, Location target, int countdownSeconds) {
        this.player = player;
        this.target = target;
        this.remainingSeconds = countdownSeconds;
        this.initialLocation = player.getLocation();
    }

    @Override
    public void run() {
        if (player.getLocation().distance(initialLocation) > 0.1) {
            TeleportManager.cancelTeleportation(player);
            ChatUtil.sendActionbar(player, "&4⚠ &cTeleportacja przerwana &8(&cNie ruszaj się&8)");
            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 10,10);
            return;
        }

        ChatUtil.sendActionbar(player, "&3⚠ Teleportacja &7za &3" + remainingSeconds + "s");
        player.playSound(player, Sound.UI_BUTTON_CLICK, 10,10);
        remainingSeconds--;

        if (remainingSeconds < 0) {
            TeleportManager.teleportPlayer(player, target);
            ChatUtil.sendActionbar(player, "&3⚠ Teleportacja &budana");
            player.playSound(player, Sound.ENTITY_SHULKER_TELEPORT, 10,10);
            cancel();
        }
    }
}