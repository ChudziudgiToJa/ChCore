package pl.chudziudgi.core.feature.backup;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.deposit.DepositUtil;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.ItemStackUtil;

import java.time.Instant;
import java.util.ArrayList;

public class BackupManager {

    public void giveBackup(Player player, Backup backup) {
        for (ItemStack itemStack : backup.getItemStackList()) {
            DepositUtil.giveItems(player, itemStack);
        }
        player.setLevel(backup.getLvl());
        player.setExp(backup.getExp());
        ChatUtil.success(player, "Odebrano zapasowy ekwipunek z " + backup.getInstantFormat());
        UserManager.get(player).backupAnswerList.remove(backup);
    }

    public void createBackup(Player player) {
        if (!UserManager.isExists(player)) return;
        User user = UserManager.get(player);

        if (user.backupList.size() >= 17) {
            user.backupList.remove(0);
        }

        ItemStack[] inventory = player.getInventory().getContents();
        ArrayList<ItemStack> itemList = new ArrayList<>();

        for (ItemStack itemStack : inventory) {
            if (itemStack != null) {
                itemList.add(itemStack);
            }
        }

        if (itemList.isEmpty()) return;

        user.backupList.add(new Backup(
                user,
                ItemStackUtil.write(itemList),
                player.getLevel(),
                player.getExp(),
                Instant.now()
        ));
    }

    public void answerBackup(Player player, Backup backup) {
        User user = UserManager.get(player);
        user.backupAnswerList.add(backup);
        ChatUtil.sendTitle(player, "","&4⚐ &7Otrzymałeś zapasowy ekwipunek &8/backup",20,50, 20);
        player.playSound(player, Sound.BLOCK_PINK_PETALS_PLACE, 10,10);
    }
}
