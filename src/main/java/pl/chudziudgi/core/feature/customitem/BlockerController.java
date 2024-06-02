package pl.chudziudgi.core.feature.customitem;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.ItemBuilder;

import java.util.Arrays;

public class BlockerController implements Listener {
    private final CustomItemConfig config;

    private final ItemStack block = new ItemBuilder(Material.BARRIER).setTitle("&cTen przedmioty aktualnie jest &4&lwyłączony").build();

    public BlockerController(final ChCore plugin, CustomItemConfig config) {
        this.config = config;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPrepareItemCraftBlockedItem(PrepareItemCraftEvent event) {
        Recipe recipe = event.getRecipe();
        if (recipe == null) {
            return;
        }

        ItemStack result = recipe.getResult();

        if (result == null) {
            return;
        }

        if (config.isDiamondItem() && isItem(result.getType())) {
            event.getInventory().setResult(block);
        }
    }

    public boolean isItem(Material material) {
        return config.getDisableCrafting().contains(material);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (!(event.getInventory().getType() == InventoryType.CRAFTING)) return;
        ItemStack currentItem = event.getCurrentItem();
        if (currentItem != null && currentItem.getType() == (block.getType())) {
            event.getWhoClicked().closeInventory();
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onPrepareItemCraftAxe(PrepareItemCraftEvent event) {

        Recipe recipe = event.getRecipe();
        if (recipe == null) {
            return;
        }

        ItemStack result = recipe.getResult();

        if (result == null) {
            return;
        }

        if (isAxe(result.getType())) {

            ItemStack axe = new ItemBuilder(
                    event.getInventory().getResult().getType())
                    .addLore(
                            "&7Wszystkie siekiery mają",
                            "   &7obniożony poziom DMG o &c50%"
                    ).build();

            event.getInventory().setResult(axe);
        }
    }

    public boolean isAxe(Material material) {
        return Arrays.asList(Material.WOODEN_AXE, Material.STONE_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE).contains(material);
    }
}