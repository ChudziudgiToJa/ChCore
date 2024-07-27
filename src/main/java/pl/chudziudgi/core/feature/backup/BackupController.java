package pl.chudziudgi.core.feature.backup;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.ItemStackUtil;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class BackupController implements Listener {
    private final BackupManager backupManager;

    public BackupController(BackupManager backupManager) {
        this.backupManager = backupManager;
    }


    @EventHandler
    public void onDead(PlayerDeathEvent event) {
        Player player = event.getEntity();
        backupManager.createBackup(player);
    }
}