package pl.chudziudgi.core.feature.kit;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;

public class KitGui {

    public static void kits(final Player player) {
        KitConfig kitConfig = new KitConfig();

        final InventoryBuilder inv = new InventoryBuilder("&9Zestawy:", InventoryType.HOPPER);
        int i = 1;

        for (Kit kit : kitConfig.getKits()) {
            inv.setItem(i++, new ItemBuilder(kit.getMaterial())
                            .setTitle("&3" + kit.getName())
                            .addLore(
                                    "",
                                    "&7Kliknij &3▜&7▛, aby otworzyć podgląd",
                                    ""
                            )
                            .build(),
                    event -> {
                        preview(player, kit);
                        player.playSound(player, Sound.UI_BUTTON_CLICK, 5, 5);
                    }
            );
        }
        inv.open(player);
    }

    public static void preview(final Player player, Kit kit) {
        KitManager kitManager = new KitManager();
        final InventoryBuilder inv = new InventoryBuilder("&9Podgląd zestawu: &3&l" + kit.getName(), 9 * 6);
        int i = 0;

        for (ItemStack itemStack : kit.getItemStack()) {
            inv.setItem(i++, itemStack,
                    event -> {
                    }
            );

        }


        inv.setItem(45, new ItemBuilder(Material.STRUCTURE_VOID)
                        .setTitle("&cCofnij")
                        .addLore("",
                                "&7Kliknij &3▜&7▛, aby cofnąć strone",
                                "")
                        .build(),
                event -> {
                    kits(player);
                    player.playSound(player, Sound.ENTITY_VILLAGER_NO, 5, 5);
                }
        );

        inv.setItem(53, new ItemBuilder(Material.LIGHT_BLUE_DYE)
                        .setTitle("&3Odbierz")
                        .addLore("",
                                "&7Kliknij &3▜&7▛, aby odebrać zestaw",
                                "")
                        .build(),
                event -> {
                    kitManager.checkCanGet(player, kit);
                }
        );
        inv.open(player);
    }
}
