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

        list.add(new ItemBuilder(Material.STONE_PICKAXE)
                .setTitle("&fZestaw &e&lSTART")
                .build());

        list.add(new ItemBuilder(Material.COOKED_BEEF, 16)
                .setTitle("&fZestaw &e&lSTART")
                .build());

        return list;
    }

    public static List<ItemStack> iron() {
        List<ItemStack> list = new ArrayList<>();

        list.add(new ItemBuilder(Material.IRON_HELMET)
                .setTitle("&32p/3u")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_CHESTPLATE)
                .setTitle("&32p/3u")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_LEGGINGS)
                .setTitle("&32p/3u")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_BOOTS)
                .setTitle("&32p/3u")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_SWORD)
                .setTitle("&32s/3u")
                .addEnchant(Enchantment.DAMAGE_ALL, 2)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.NETHERITE_PICKAXE)
                .setTitle("&35e/3u")
                .addEnchant(Enchantment.DIG_SPEED, 5)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.BOW)
                .setTitle("&33p/1f")
                .addEnchant(Enchantment.ARROW_DAMAGE, 3)
                .addEnchant(Enchantment.ARROW_FIRE, 1)
                .build());
        return list;
    }
    public static List<ItemStack> gold() {
        List<ItemStack> list = new ArrayList<>();

        list.add(new ItemBuilder(Material.IRON_HELMET)
                .setTitle("&32p/3u")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_CHESTPLATE)
                .setTitle("&32p/3u")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_LEGGINGS)
                .setTitle("&32p/3u")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_BOOTS)
                .setTitle("&32p/3u")
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.IRON_SWORD)
                .setTitle("&32s/3u")
                .addEnchant(Enchantment.DAMAGE_ALL, 2)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.NETHERITE_PICKAXE)
                .setTitle("&35e/3u")
                .addEnchant(Enchantment.DIG_SPEED, 5)
                .addEnchant(Enchantment.DURABILITY, 3)
                .build());

        list.add(new ItemBuilder(Material.BOW)
                .setTitle("&33p/1f")
                .addEnchant(Enchantment.ARROW_DAMAGE, 3)
                .addEnchant(Enchantment.ARROW_FIRE, 1)
                .build());
        return list;
    }
}
