package pl.chudziudgi.core.feature.schowek;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class DepositUtil {
    public static int getAmount(final Player player, final Material material) {
        int amount = 0;
        for (final ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null && itemStack.getType().equals(material)) {
                amount += itemStack.getAmount();
            }
        }
        return amount;
    }

    public static int remove(final Player player, final Material material, final int limit) {
        final PlayerInventory inv = player.getInventory();
        int removed = 0;
        for (final ItemStack slot : inv.getContents()) {
            if (slot != null) {
                inv.remove(slot);
                removed += slot.getAmount();
            }
        }
        inv.addItem(new ItemStack(material, limit));
        player.updateInventory();
        return removed - limit;
    }
    public static void giveItems(final Player player, final ItemStack... items) {
        final HashMap<Integer, ItemStack> notStored = player.getInventory().addItem(items);
        for (final Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
            player.getWorld().dropItemNaturally(player.getLocation(), e.getValue());
        }
    }
}
