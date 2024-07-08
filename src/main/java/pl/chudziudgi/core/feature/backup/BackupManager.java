package pl.chudziudgi.core.feature.backup;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.deposit.DepositUtil;
import pl.chudziudgi.core.util.ChatUtil;

public class BackupManager {

    public void giveBackup(Player player, Backup backup) {
        for (ItemStack itemStack : backup.getItemStackList()) {
            DepositUtil.giveItems(player, itemStack);
        }
        player.setLevel(backup.getLvl());
        ChatUtil.sendTitle(player,"","&7Otrzymałeś zapasowy ekwipunek.",20,20, 20);
        UserManager.get(player).backupList.remove(backup);
    }

}
