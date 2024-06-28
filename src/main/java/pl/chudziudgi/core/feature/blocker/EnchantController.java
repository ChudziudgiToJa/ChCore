package pl.chudziudgi.core.feature.blocker;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.Arrays;

public class EnchantController implements Listener {

    public EnchantController(ChCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        Player player = event.getEnchanter();
        ItemStack item = event.getItem();

        if (item.getType() == Material.BOW) {
            if (event.getEnchantsToAdd().containsKey(Enchantment.ARROW_DAMAGE) && event.getEnchantsToAdd().get(Enchantment.ARROW_DAMAGE) >= 3) {
                sendRestrictionMessage(player, "&4⚠ &cTwój enchant przekracza maksymalną dozwoloną wielkość.");
                player.closeInventory();
                event.setCancelled(true);
                return;
            }

            if (event.getEnchantsToAdd().containsKey(Enchantment.ARROW_INFINITE)) {
                sendRestrictionMessage(player, "&4⚠ &cZakazano enchantowania łuku zaklęciem &5Infinite.");
                player.closeInventory();
                event.setCancelled(true);
                return;
            }
        }

        if (isSword(item.getType())) {
            if (event.getEnchantsToAdd().containsKey(Enchantment.DAMAGE_ALL) && event.getEnchantsToAdd().get(Enchantment.DAMAGE_ALL) >= 5) {
                sendRestrictionMessage(player, "&4⚠ &cTwój enchant przekracza maksymalną dozwoloną wielkość.");
                player.closeInventory();
                event.setCancelled(true);
                return;
            }

            if (event.getEnchantsToAdd().containsKey(Enchantment.FIRE_ASPECT)) {
                sendRestrictionMessage(player, "&4⚠ &cTwój enchant przekracza maksymalną dozwoloną wielkość.");
                player.closeInventory();
                event.setCancelled(true);
                return;
            }
        }

        if (isAxe(item.getType())) {
            if (event.getEnchantsToAdd().containsKey(Enchantment.DAMAGE_ALL)) {
                sendRestrictionMessage(player, "&4⚠ &cZakazano enchantowania siekier zaklęciem &5Sharpness.");
                player.closeInventory();
                event.setCancelled(true);
                return;
            }
        }
    }

    private void sendRestrictionMessage(Player player, String message) {
        ChatUtil.sendTitle(player, "", message, 5, 40, 5);
        player.playSound(player, Sound.ENTITY_VILLAGER_NO, 10, 10);
    }

    public boolean isAxe(Material material) {
        return Arrays.asList(Material.WOODEN_AXE, Material.STONE_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE).contains(material);
    }

    public boolean isSword(Material material) {
        return Arrays.asList(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD).contains(material);
    }
}