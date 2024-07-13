package pl.chudziudgi.core.feature.enderchest;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.ItemStackUtil;

import java.util.ArrayList;

public class EnderChestGuiListener implements Listener {

    private final ArrayList<ItemStack> itemsList = new ArrayList<>();

    public EnderChestGuiListener(ChCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();
        if (!(event.getView().getTitle().equals(ChatUtil.fixColor("&dEnderchest:")))) return;
        Player target = (Player) event.getInventory().getHolder();
        if (target == null) return;
        User user = UserManager.get(target);

        itemsList.clear();
        for (ItemStack item : inventory.getContents()) {
            if (item != null) {
                itemsList.add(item);
            }
        }
        user.enderChestList = ItemStackUtil.write(itemsList);
    }
}
