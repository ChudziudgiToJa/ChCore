package pl.chudziudgi.core.feature.blocker;

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
import java.util.Objects;

public class CraftingController implements Listener {
    private final BlockerConfig config;

    public CraftingController(final ChCore plugin, BlockerConfig config) {
        this.config = config;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public boolean isItem(Material material) {
        return config.getDisableCrafting().contains(material);
    }

    private void setBarrierBlock(PrepareItemCraftEvent event) {
        ItemStack result = event.getInventory().getResult();
        if (result != null && isItem(result.getType())) {
            ItemStack barrier = new ItemBuilder(Material.BARRIER)
                    .setTitle("&4⚠ &cZakazany item")
                    .addLore(
                            "",
                            "&fℹ &7Sprawdź plan edycji na naszym",
                            "   &7Discordzie &9&ndiscord.klanmc.pl &b✌",
                            ""
                    )
                    .build();
            event.getInventory().setResult(barrier);
        }
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        setBarrierBlock(event);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack currentItem = event.getCurrentItem();
        if (currentItem != null && currentItem.getType() == Material.BARRIER && event.getSlotType() == InventoryType.SlotType.RESULT) {
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

        if (isAxe(result.getType())) {

            ItemStack axe = new ItemBuilder(
                    Objects.requireNonNull(event.getInventory().getResult()).getType())
                    .addLore(
                            "",
                            "&7Wszystkie siekiery mają",
                            "   &7obniożony poziom DMG o &c50%",
                            ""
                    ).build();

            event.getInventory().setResult(axe);
        }
    }

    public boolean isAxe(Material material) {
        return Arrays.asList(Material.WOODEN_AXE, Material.STONE_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE).contains(material);
    }
}