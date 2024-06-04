package pl.chudziudgi.core.feature.kit;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class Kits {

    public static List<ItemStack> start() {
        List<ItemStack> list = new ArrayList<>();

        list.add(new ItemBuilder(Material.COOKED_RABBIT, 16)
                .build());

        list.add(new ItemBuilder(Material.BIRCH_LOG, 4)
                .build());

        return list;
    }

    public static List<ItemStack> iron() {
        List<ItemStack> list = new ArrayList<>();

        list.add(new ItemBuilder(Material.IRON_HELMET)
                .setTitle("&7Hełm &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_CHESTPLATE)
                .setTitle("&7Klata &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_LEGGINGS)
                .setTitle("&7Spodnie &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_BOOTS)
                .setTitle("&7Buty &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_SWORD)
                .setTitle("&7Miecz &34/3")
                .addEnchant(Enchantment.DAMAGE_ALL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.NETHERITE_PICKAXE)
                .setTitle("&7Kilof &35/3/3")
                .addEnchant(Enchantment.DIG_SPEED, 5)
                .addEnchant(Enchantment.DURABILITY, 3)
                .addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3)
                .build());

        list.add(new ItemBuilder(Material.BOW)
                .setTitle("&7łuk &32/2")
                .addEnchant(Enchantment.ARROW_DAMAGE, 2)
                .addEnchant(Enchantment.ARROW_FIRE, 2)
                .build());

        list.add(new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, 2)
                .build());

        list.add(new ItemBuilder(Material.GOLDEN_APPLE, 8*2)
                .build());

        list.add(new ItemBuilder(Material.ENDER_PEARL, 4)
                .build());

        list.add(new ItemBuilder(Material.ARROW, 16*2)
                .build());

        list.add(new ItemBuilder(Material.CHORUS_FRUIT, 5*2)
                .build());

        list.add(new ItemBuilder(Material.TOTEM_OF_UNDYING)
                .build());

        list.add(new ItemBuilder(Material.TOTEM_OF_UNDYING)
                .build());

        list.add(new ItemBuilder(Material.WATER_BUCKET)
                .build());

        list.add(new ItemBuilder(Material.COOKED_RABBIT, 32)
                .build());


        return list;
    }
    public static List<ItemStack> gold() {
        List<ItemStack> list = new ArrayList<>();

        list.add(new ItemBuilder(Material.IRON_HELMET)
                .setTitle("&7Hełm &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_CHESTPLATE)
                .setTitle("&7Klata &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_LEGGINGS)
                .setTitle("&7Spodnie &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_BOOTS)
                .setTitle("&7Buty &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_HELMET)
                .setTitle("&7Hełm &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_CHESTPLATE)
                .setTitle("&7Klata &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_LEGGINGS)
                .setTitle("&7Spodnie &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_BOOTS)
                .setTitle("&7Buty &34/3")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_SWORD)
                .setTitle("&7Miecz &34/3")
                .addEnchant(Enchantment.DAMAGE_ALL, 4)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.NETHERITE_PICKAXE)
                .setTitle("&7Kilof &35/3/3")
                .addEnchant(Enchantment.DIG_SPEED, 5)
                .addEnchant(Enchantment.DURABILITY, 3)
                .addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3)
                .build());

        list.add(new ItemBuilder(Material.BOW)
                .setTitle("&7łuk &32/2")
                .addEnchant(Enchantment.ARROW_DAMAGE, 2)
                .addEnchant(Enchantment.ARROW_FIRE, 2)
                .build());

        list.add(new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, 3)
                .build());

        list.add(new ItemBuilder(Material.GOLDEN_APPLE, 8*3)
                .build());

        list.add(new ItemBuilder(Material.ENDER_PEARL, 2*3)
                .build());

        list.add(new ItemBuilder(Material.ARROW, 16*3)
                .build());

        list.add(new ItemBuilder(Material.CHORUS_FRUIT, 5*3)
                .build());

        list.add(new ItemBuilder(Material.TOTEM_OF_UNDYING)
                .build());

        list.add(new ItemBuilder(Material.TOTEM_OF_UNDYING)
                .build());

        list.add(new ItemBuilder(Material.TOTEM_OF_UNDYING)
                .build());

        list.add(new ItemBuilder(Material.WATER_BUCKET)
                .build());

        list.add(new ItemBuilder(Material.COOKED_RABBIT, 64)
                .build());

        return list;
    }
}
