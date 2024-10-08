package pl.chudziudgi.core.feature.customitem.obsydiangenerator;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.combat.CombatManager;
import pl.chudziudgi.core.feature.customitem.CustomItemStack;
import pl.chudziudgi.core.util.ChatUtil;

public class ObsydianGeneratorController implements Listener {

    private final ChCore plugin;
    private final CombatManager combatManager;

    public ObsydianGeneratorController(ChCore plugin, CombatManager combatManager) {
        this.plugin = plugin;
        this.combatManager = combatManager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack itemInHand = event.getItemInHand();

        if (!itemInHand.isSimilar(CustomItemStack.obsydianGenerator())) {
            return;
        }

        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();


        if (!this.plugin.getFunnyGuilds().getRegionManager().isInRegion(block.getLocation())) {
            ChatUtil.error(player, "Nie możesz stawiać tego bloku poza terenem klanu");
            event.setCancelled(true);
            return;
        }

        if (y > 85) {
            ChatUtil.error(player, "Generator obsydianu możesz stawiać od 85 poziomu wysokości");
            event.setCancelled(true);
            return;
        }

        if (combatManager.inCombat(player)) {
            ChatUtil.error(player, "Nie można używać boyfarmera podczas walki");
            event.setCancelled(true);
            return;
        }
        block.setType(Material.OBSIDIAN);


        new BukkitRunnable() {
            int currentY = y;

            @Override
            public void run() {
                if (currentY <= -63) {
                    cancel();
                    return;
                }
                Block currentBlock = block.getWorld().getBlockAt(x, currentY - 1, z);
                Material blockType = currentBlock.getType();

                if (blockType == Material.AIR || blockType == Material.WATER || blockType == Material.LAVA) {
                    currentBlock.setType(Material.OBSIDIAN);
                } else {
                    cancel();
                    return;
                }
                currentY--;
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
}
