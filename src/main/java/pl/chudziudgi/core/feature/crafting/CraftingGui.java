package pl.chudziudgi.core.feature.crafting;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;

public class CraftingGui {

    public static void chose(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Craftingi:", 9);
        int i = 0;
        if (i > inv.getInventory().getSize()) return;

        inv.setItem(i++, new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE)
                        .addLore(
                                ""
                                , "&7Kliknij &3▜&7▛, aby otworzyć podgląd",
                                ""
                        )
                        .build(),
                event -> {
                    craftingGoldenEnchantedApple(player);
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.ARROW)
                        .addLore(
                                ""
                                , "&7Kliknij &3▜&7▛, aby otworzyć podgląd"
                                , ""
                        )
                        .build(),
                event -> {
            craftingArrow(player);
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.STRING)
                        .addLore(
                                ""
                                , "&7Kliknij &3▜&7▛, aby otworzyć podgląd"
                                , ""
                        )
                        .build(),
                event -> {
            craftingString(player);
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.PACKED_ICE, 8)
                        .addLore(
                                ""
                                , "&7Kliknij &3▜&7▛, aby otworzyć podgląd"
                                , ""
                        )
                        .build(),
                event -> {
            craftingPackedIce(player);
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.GRAVEL)
                        .addLore(
                                ""
                                , "&7Kliknij &3▜&7▛, aby otworzyć podgląd"
                                , ""
                        )
                        .build(),
                event -> {
            craftingGravel(player);
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.PURPLE_GLAZED_TERRACOTTA)
                        .setTitle("&fGenerator obsydianiu &e❀")
                        .addLore(
                                ""
                                , "&7Kliknij &3▜&7▛, aby otworzyć podgląd"
                                , ""
                        )
                        .build(),
                event -> {
            craftingObsydianGenerator(player);
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.GRAY_GLAZED_TERRACOTTA)
                        .setTitle("&fGenerator kamienia &e❀")
                        .addLore(
                                ""
                                , "&7Kliknij &3▜&7▛, aby otworzyć podgląd"
                                , ""
                        )
                        .build(),
                event -> {
            craftingStoneGenerator(player);
                }
        );
        inv.open(player);
    }

    public static void craftingGoldenEnchantedApple(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Crafting na &bkoxa:", InventoryType.DROPPER);


        inv.setItem(1, new ItemBuilder(Material.GOLD_BLOCK)
                        .build(),
                event -> {
                }
        );

        inv.setItem(3, new ItemBuilder(Material.GOLD_BLOCK)
                        .build(),
                event -> {
                }
        );

        inv.setItem(4, new ItemBuilder(Material.APPLE)
                        .build(),
                event -> {
                }
        );

        inv.setItem(5, new ItemBuilder(Material.GOLD_BLOCK)
                        .build(),
                event -> {
                }
        );

        inv.setItem(7, new ItemBuilder(Material.GOLD_BLOCK)
                        .build(),
                event -> {
                }
        );

        inv.open(player);
    }

    public static void craftingArrow(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Crafting na &bstrzałe:", InventoryType.DROPPER);


        inv.setItem(2, new ItemBuilder(Material.IRON_NUGGET)
                        .build(),
                event -> {
                }
        );

        inv.setItem(4, new ItemBuilder(Material.STICK)
                        .build(),
                event -> {
                }
        );

        inv.setItem(6, new ItemBuilder(Material.FLINT)
                        .build(),
                event -> {
                }
        );

        inv.open(player);
    }

    public static void craftingString(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Crafting na &bnici:", InventoryType.DROPPER);


        inv.setItem(3, new ItemBuilder(Material.LEATHER)
                        .build(),
                event -> {
                }
        );

        inv.setItem(4, new ItemBuilder(Material.LEATHER)
                        .build(),
                event -> {
                }
        );

        inv.setItem(5, new ItemBuilder(Material.LEATHER)
                        .build(),
                event -> {
                }
        );

        inv.open(player);
    }

    public static void craftingPackedIce(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Crafting na &blód:", InventoryType.DROPPER);
        int i = 0;

        inv.setItem(i++, new ItemBuilder(Material.LAPIS_LAZULI)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.LAPIS_LAZULI)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.LAPIS_LAZULI)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.LAPIS_LAZULI)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.WATER_BUCKET)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.LAPIS_LAZULI)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.LAPIS_LAZULI)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.LAPIS_LAZULI)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.LAPIS_LAZULI)
                        .build(),
                event -> {
                }
        );

        inv.open(player);
    }

    public static void craftingGravel(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Crafting na &bgravel:", InventoryType.DROPPER);
        int i = 0;

        inv.setItem(i++, new ItemBuilder(Material.COBBLESTONE)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.COBBLESTONE)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.COBBLESTONE)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.SAND)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.SAND)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.SAND)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.COBBLESTONE)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.COBBLESTONE)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.COBBLESTONE)
                        .build(),
                event -> {
                }
        );

        inv.open(player);
    }

    public static void craftingObsydianGenerator(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Crafting na &fGenerator obsydianiu &e❀:", InventoryType.DROPPER);
        int i = 0;

        inv.setItem(i++, new ItemBuilder(Material.COPPER_BLOCK)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.COPPER_BLOCK)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.COPPER_BLOCK)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.COPPER_BLOCK)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.OBSIDIAN)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.COPPER_BLOCK)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.COPPER_BLOCK)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.COPPER_BLOCK)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.COPPER_BLOCK)
                        .build(),
                event -> {
                }
        );

        inv.open(player);
    }

    public static void craftingStoneGenerator(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Crafting na &fGenerator kamienia &e❀:", InventoryType.DROPPER);
        int i = 0;

        inv.setItem(i++, new ItemBuilder(Material.STONE)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.STONE)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.STONE)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.STONE)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.COPPER_BLOCK)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.STONE)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.STONE)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.STONE)
                        .build(),
                event -> {
                }
        );

        inv.setItem(i++, new ItemBuilder(Material.STONE)
                        .build(),
                event -> {
                }
        );

        inv.open(player);
    }

}
