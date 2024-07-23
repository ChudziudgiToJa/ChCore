package pl.chudziudgi.core.feature.customitem.magiccandle;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.feature.drop.DropGui;

public class MagicCandleGui {

    public static void openkit(final Player player) {
        MagicCandleConfig magicCandleConfig = new MagicCandleConfig();
        final Integer[] slotList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};

        final InventoryBuilder inv = new InventoryBuilder("&9Przedmioty z &fMagicznej świecy &e★", 9 * 5);
        int i = 0;

        for (ItemStack itemStack : magicCandleConfig.dropList) {
            inv.setItem(slotList[i++], itemStack,
                    event -> {
                    }
            );
        }

        inv.setItem(44, new ItemBuilder(Material.STRUCTURE_VOID)
                        .setTitle("&cCofnij")
                        .addLore("",
                                "&7Kliknij &3▜&7▛, aby cofnąć menu",
                                "")
                        .build(),
                event -> {
                    DropGui.openChose(player);
                }
        );

        inv.open(player);
    }
}
