package pl.chudziudgi.core.feature.blocker;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.feature.customitem.CustomItemStack;

import java.util.Arrays;

public class AnvilController implements Listener {

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack result = event.getResult();
        if (result == null) return;

        if (result.containsEnchantment(Enchantment.FIRE_ASPECT)) {
            setBarrierBlock(event);
            return;
        }

        if (result.getType() == Material.BOW) {
            if (result.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) >= 2) {
                setBarrierBlock(event);
                return;
            }

            if (result.containsEnchantment(Enchantment.ARROW_INFINITE)) {
                setBarrierBlock(event);
                return;
            }
        }

        if (isSword(result.getType())) {
            if (result.getEnchantmentLevel(Enchantment.DAMAGE_ALL) >= 5) {
                setBarrierBlock(event);
                return;
            }
        }

        if (isAxe(result.getType())) {
            if (result.containsEnchantment(Enchantment.DAMAGE_ALL)) {
                setBarrierBlock(event);
                return;
            }
        }
    }

    private void setBarrierBlock(PrepareAnvilEvent event) {
        ItemStack barrier = new ItemBuilder(Material.BARRIER)
                .setTitle("&4⚠ &cUWAGA")
                .addLore(
                        "",
                        "&fℹ &7Sprawdź maksymalne levele enchantów na naszym",
                        "   &8Lub zakazana interackja z przedmiotem",
                        "   &7Discordzie &9&ndiscord.klanmc.pl &b✌",
                        ""
                )
                .build();
        event.setResult(barrier);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.ANVIL && event.getSlotType() == InventoryType.SlotType.RESULT) {
            ItemStack currentItem = event.getCurrentItem();
            if (currentItem != null && currentItem.getType() == Material.BARRIER) {
                event.setCancelled(true);
            }
        }
    }
    public boolean isAxe(Material material) {
        return Arrays.asList(Material.WOODEN_AXE, Material.STONE_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE).contains(material);
    }

    public boolean isSword(Material material) {
        return Arrays.asList(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD).contains(material);
    }
}
