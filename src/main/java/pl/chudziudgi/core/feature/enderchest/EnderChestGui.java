package pl.chudziudgi.core.feature.enderchest;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.ItemStackUtil;

public class EnderChestGui {



    public static void openkit(final Player player, final Player target) {
        User user = UserManager.get(target);
        final EnderChestManager enderChestManager = new EnderChestManager();

        final Inventory inv = Bukkit.createInventory(target, enderChestManager.getSize(target), ChatUtil.fixColor("&dEnderchest:"));
        int i = 0;

        if (!user.enderChestList.isEmpty()) {
            for (ItemStack itemStack : ItemStackUtil.read(user.enderChestList)) {
                inv.setItem(i++, itemStack);
            }
        }
        player.openInventory(inv);
    }
}
