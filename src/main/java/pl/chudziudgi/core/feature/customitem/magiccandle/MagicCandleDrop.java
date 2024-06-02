package pl.chudziudgi.core.feature.customitem.magiccandle;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.ItemBuilder;

import java.util.Arrays;
import java.util.List;

public class MagicCandleDrop {

    public static final List<ItemStack> dropList = Arrays.asList(

            new ItemBuilder(Material.DIAMOND ,16)
                    .build(),

            new ItemBuilder(Material.GOLD_INGOT ,32)
                    .build(),

            new ItemBuilder(Material.GOLDEN_APPLE ,8)
                    .build(),

            new ItemBuilder(Material.ARROW ,16)
                    .build(),

            new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE ,1)
                    .build(),

            new ItemBuilder(Material.TOTEM_OF_UNDYING)
                    .setTitle("&7Totem")
                    .build(),

            new ItemBuilder(Material.IRON_HELMET)
                    .setTitle("&7Hełm &34/3")
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    .addEnchant(Enchantment.DURABILITY, 3)
                    .build(),

            new ItemBuilder(Material.IRON_CHESTPLATE)
                .setTitle("&7Klata &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build(),

            new ItemBuilder(Material.IRON_LEGGINGS)
                .setTitle("&7Spodnie &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build(),

            new ItemBuilder(Material.IRON_BOOTS)
                .setTitle("&7Buty &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build(),

            new ItemBuilder(Material.IRON_SWORD)
                    .setTitle("&7Miecz &35/3")
                    .addEnchant(Enchantment.DAMAGE_ALL, 5)
                    .addEnchant(Enchantment.DURABILITY, 3)
                    .build(),

            new ItemBuilder(Material.NETHERITE_PICKAXE)
                .setTitle("&7Kilof &35/3/3")
                .addEnchant(Enchantment.DIG_SPEED, 5)
                .addEnchant(Enchantment.DURABILITY, 3)
                    .addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3)
                .build(),

            new ItemBuilder(Material.BOW)
                .setTitle("&7łuk &34/2")
                .addEnchant(Enchantment.ARROW_DAMAGE, 3)
                .addEnchant(Enchantment.ARROW_FIRE, 2)
                .build()
    );
}
