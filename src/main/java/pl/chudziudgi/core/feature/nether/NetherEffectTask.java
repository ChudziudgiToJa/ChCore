package pl.chudziudgi.core.feature.nether;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.ItemBuilder;

public class NetherEffectTask extends BukkitRunnable {

    public NetherEffectTask(final ChCore plugin) {
        runTaskTimer(plugin, 0, 20*2);
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
                Material mainHandItem = player.getInventory().getItemInMainHand().getType();
                if (isPickaxe(mainHandItem)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20 * 4, 0));
                }
            }
        });
    }
    private boolean isPickaxe(Material material) {
        return material == Material.WOODEN_PICKAXE ||
                material == Material.STONE_PICKAXE ||
                material == Material.IRON_PICKAXE ||
                material == Material.GOLDEN_PICKAXE ||
                material == Material.DIAMOND_PICKAXE ||
                material == Material.NETHERITE_PICKAXE;
    }
}