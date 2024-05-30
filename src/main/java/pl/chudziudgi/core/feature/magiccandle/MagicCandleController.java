package pl.chudziudgi.core.feature.magiccandle;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.ChCore;

public class MagicCandleController implements Listener {

    public MagicCandleController(ChCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType() == Material.LIGHT_BLUE_CANDLE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onRename(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getType() == InventoryType.ANVIL) {
            AnvilInventory anvilInventory = (AnvilInventory) event.getInventory();
            ItemStack item = anvilInventory.getItem(0);
            if (item.getType() == Material.LIGHT_BLUE_CANDLE) {
                event.setCancelled(true);
            }
        }
    }
}