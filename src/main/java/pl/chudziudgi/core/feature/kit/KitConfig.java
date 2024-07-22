package pl.chudziudgi.core.feature.kit;

import eu.okaeri.configs.OkaeriConfig;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import pl.chudziudgi.core.api.ItemBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KitConfig extends OkaeriConfig {

    private boolean kitStatus = true;
    private final List<Kit> kits = Arrays.asList(

            new Kit(
                    "start",
                    "core.kit.start",
                    1,
                    Material.LEATHER_HELMET,
                    new ArrayList<>(Arrays.asList(
                            new ItemBuilder(Material.OAK_LOG, 4)
                                    .build(),
                            new ItemBuilder(Material.COOKED_RABBIT, 8)
                                    .build(),
                            new ItemBuilder(Material.STONE_PICKAXE)
                                    .setTitle("&7Kilof &3startowy")
                                    .addEnchant(Enchantment.DIG_SPEED, 4)
                                    .build()
                    ))
            ),
            new Kit(
                    "iron",
                    "core.kit.iron",
                    24,
                    Material.IRON_HELMET,
                    new ArrayList<>(Arrays.asList(
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
                                    .setTitle("&7Miecz &34/3")
                                    .addEnchant(Enchantment.DAMAGE_ALL, 4)
                                    .addEnchant(Enchantment.DURABILITY, 3)
                                    .build(),

                            new ItemBuilder(Material.IRON_PICKAXE)
                                    .setTitle("&7Kilof &35/3/3")
                                    .addEnchant(Enchantment.DIG_SPEED, 5)
                                    .addEnchant(Enchantment.DURABILITY, 3)
                                    .addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3)
                                    .build(),

                            new ItemBuilder(Material.BOW)
                                    .setTitle("&7łuk &32/2")
                                    .addEnchant(Enchantment.ARROW_DAMAGE, 2)
                                    .addEnchant(Enchantment.ARROW_FIRE, 2)
                                    .build(),

                            new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, 1)
                                    .build(),

                            new ItemBuilder(Material.GOLDEN_APPLE, 8)
                                    .build(),

                            new ItemBuilder(Material.ENDER_PEARL, 2)
                                    .build(),

                            new ItemBuilder(Material.ARROW, 16)
                                    .build(),

                            new ItemBuilder(Material.CHORUS_FRUIT, 5)
                                    .build(),

                            new ItemBuilder(Material.PACKED_ICE, 16)
                                    .build(),

                            new ItemBuilder(Material.TOTEM_OF_UNDYING)
                                    .build(),

                            new ItemBuilder(Material.TOTEM_OF_UNDYING)
                                    .build(),

                            new ItemBuilder(Material.WATER_BUCKET)
                                    .build(),

                            new ItemBuilder(Material.COOKED_RABBIT, 32)
                                    .build()
                    ))

            ),
            new Kit(
                    "gold",
                    "core.kit.gold",
                    48,
                    Material.GOLDEN_HELMET,
                    new ArrayList<>(Arrays.asList(
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
                                    .setTitle("&7Miecz &34/3")
                                    .addEnchant(Enchantment.DAMAGE_ALL, 4)
                                    .addEnchant(Enchantment.DURABILITY, 3)
                                    .build(),

                            new ItemBuilder(Material.IRON_PICKAXE)
                                    .setTitle("&7Kilof &35/3/3")
                                    .addEnchant(Enchantment.DIG_SPEED, 5)
                                    .addEnchant(Enchantment.DURABILITY, 3)
                                    .addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3)
                                    .build(),

                            new ItemBuilder(Material.BOW)
                                    .setTitle("&7łuk &32/2")
                                    .addEnchant(Enchantment.ARROW_DAMAGE, 2)
                                    .addEnchant(Enchantment.ARROW_FIRE, 2)
                                    .build(),

                            new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, 2)
                                    .build(),

                            new ItemBuilder(Material.GOLDEN_APPLE, 8 * 2)
                                    .build(),

                            new ItemBuilder(Material.ENDER_PEARL, 2 * 2)
                                    .build(),

                            new ItemBuilder(Material.ARROW, 16 * 2)
                                    .build(),

                            new ItemBuilder(Material.CHORUS_FRUIT, 5 * 2)
                                    .build(),

                            new ItemBuilder(Material.PACKED_ICE, 16 * 2)
                                    .build(),

                            new ItemBuilder(Material.TOTEM_OF_UNDYING)
                                    .build(),

                            new ItemBuilder(Material.TOTEM_OF_UNDYING)
                                    .build(),

                            new ItemBuilder(Material.TOTEM_OF_UNDYING)
                                    .build(),

                            new ItemBuilder(Material.WATER_BUCKET)
                                    .build(),

                            new ItemBuilder(Material.WATER_BUCKET)
                                    .build(),

                            new ItemBuilder(Material.COOKED_RABBIT, 64)
                                    .build()
                    ))
            )
    );

    public boolean isKitStatus() {
        return kitStatus;
    }

    public void setKitStatus(boolean kitStatus) {
        this.kitStatus = kitStatus;
    }

    public List<Kit> getKits() {
        return kits;
    }
}
