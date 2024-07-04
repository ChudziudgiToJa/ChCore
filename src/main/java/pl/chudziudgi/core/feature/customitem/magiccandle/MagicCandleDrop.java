package pl.chudziudgi.core.feature.customitem.magiccandle;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.ItemBuilder;

import java.util.Arrays;
import java.util.List;

public class MagicCandleDrop {

    public static final List<ItemStack> dropList = Arrays.asList(

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

            new ItemBuilder(Material.BOW)
                    .setTitle("&7łuk &32/2")
                    .addEnchant(Enchantment.ARROW_DAMAGE, 2)
                    .addEnchant(Enchantment.ARROW_FIRE, 2)
                    .build(),

            new ItemBuilder(Material.ARROW, 16)
                    .build(),

            new ItemBuilder(Material.ARROW, 48)
                    .build(),

            new ItemBuilder(Material.ENDER_PEARL, 4)
                    .build(),

            new ItemBuilder(Material.TOTEM_OF_UNDYING)
                    .build(),

            new ItemBuilder(Material.GOLD_INGOT, 16)
                    .build(),

            new ItemBuilder(Material.GRAY_GLAZED_TERRACOTTA, 16)
                    .setTitle("&fGenerator kamienia &e❀")
                    .addLore(
                            "",
                            "&8Opis",
                            "&7Generator kamienia to przedmiot,",
                            "&7który po użyciu generuje kamienia 1 blok w &bgórę ⬆&7.",
                            "&7Pamiętaj wymagane jest puste pole aby generator &n&fzadziałał",
                            "",
                            "&7Postaw &3▜&7▛&7, aby użyć."
                    )
                    .setGlow(true)
                    .build(),

            new ItemBuilder(Material.PURPLE_GLAZED_TERRACOTTA, 16)
                    .setTitle("&fGenerator obsydianiu &e❀")
                    .addLore(
                            "",
                            "&8Opis",
                            "&7Generator obsydianiu to przedmiot,",
                            "&7który po użyciu generuje obsydian w &bdół ⬇&7.",
                            "&7Pamiętaj wymagane jest puste pole aby generator &n&fzadziałał",
                            "",
                            "&7Postaw &3▜&7▛&7, aby użyć."
                    )
                    .setGlow(true)
                    .build(),

            new ItemBuilder(Material.DIAMOND_BLOCK, 8)
                    .build(),

            new ItemBuilder(Material.COPPER_BLOCK, 32)
                    .build(),

            new ItemBuilder(Material.LIGHT_BLUE_CANDLE, 2)
                    .setTitle("&fMagiczne świeca &e★")
                    .addLore(
                            "",
                            "&8Opis",
                            "&7Magiczna świeca to tajemniczy przedmiot,",
                            "&7który po użyciu losowo przemienia się w cenny skarb.",
                            "",
                            "&7Kliknij &7▜&3▛&7, aby użyć."
                    )
                    .setGlow(true)
                    .build(),

            new ItemBuilder(Material.APPLE, 32)
                    .build(),

            new ItemBuilder(Material.ENDER_PEARL, 8)
                    .build(),

            new ItemBuilder(Material.TOTEM_OF_UNDYING)
                    .build(),

            new ItemBuilder(Material.GOLD_INGOT, 48)
                    .build(),

            new ItemBuilder(Material.GRAY_GLAZED_TERRACOTTA, 32)
                    .setTitle("&fGenerator kamienia &e❀")
                    .addLore(
                            "",
                            "&8Opis",
                            "&7Generator kamienia to przedmiot,",
                            "&7który po użyciu generuje kamienia 1 blok w &bgórę ⬆&7.",
                            "&7Pamiętaj wymagane jest puste pole aby generator &n&fzadziałał",
                            "",
                            "&7Postaw &3▜&7▛&7, aby użyć."
                    )
                    .setGlow(true)
                    .build(),

            new ItemBuilder(Material.PURPLE_GLAZED_TERRACOTTA, 32)
                    .setTitle("&fGenerator obsydianiu &e❀")
                    .addLore(
                            "",
                            "&8Opis",
                            "&7Generator obsydianiu to przedmiot,",
                            "&7który po użyciu generuje obsydian w &bdół ⬇&7.",
                            "&7Pamiętaj wymagane jest puste pole aby generator &n&fzadziałał",
                            "",
                            "&7Postaw &3▜&7▛&7, aby użyć."
                    )
                    .setGlow(true)
                    .build(),

            new ItemBuilder(Material.DIAMOND_BLOCK, 16)
                    .build(),

            new ItemBuilder(Material.EXPERIENCE_BOTTLE, 32)
                    .build(),

            new ItemBuilder(Material.LIGHT_BLUE_CANDLE, 4)
                    .setTitle("&fMagiczne świeca &e★")
                    .addLore(
                            "",
                            "&8Opis",
                            "&7Magiczna świeca to tajemniczy przedmiot,",
                            "&7który po użyciu losowo przemienia się w cenny skarb.",
                            "",
                            "&7Kliknij &7▜&3▛&7, aby użyć."
                    )
                    .setGlow(true)
                    .build(),

            new ItemBuilder(Material.EXPERIENCE_BOTTLE, 64)
                    .build()
            );
}
