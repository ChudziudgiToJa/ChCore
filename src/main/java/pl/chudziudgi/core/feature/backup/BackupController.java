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

    public BackupController(final ChCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDead(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (!UserManager.isExists(player)) return;
        User user = UserManager.get(player);

        if (user.backupList.size() >= 9) {
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
                Instant.now()
        ));

        ChatUtil.info(player, "Zapisano twój ekwipunek przed śmiercią");
    }
}