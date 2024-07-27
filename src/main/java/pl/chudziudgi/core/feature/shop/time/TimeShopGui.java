package pl.chudziudgi.core.feature.shop.time;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.shop.ShopGui;

import static pl.chudziudgi.core.feature.drop.DropGui.getSlotList;

public class TimeShopGui {

    private static final Integer[] slotList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
    private static final PluginConfiguration config = new PluginConfiguration();

    public static void open(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Sklep za czas: &3" + UserManager.get(player).timeCoin + " monet.", 9 * 4);

        int i = 0;
        for (TimeShop timeShop : config.timeShopSettings.timeShopList) {
            ItemStack itemStack = new ItemBuilder(timeShop.getMaterial())
                    .setTitle(timeShop.getName())
                    .addLore(
                            "",
                            "&8Cena:",
                            " &3" + timeShop.getPrice() + " &7monet czasu",
                            "",
                            "&7Kliknij &3▜&7▛, aby kupić"
                    )
                    .build();

            inv.setItem(getSlotList()[i++], itemStack,
                    event -> {
                        TimeShopManager.buyItem(player, timeShop);
                        player.closeInventory();
                    }
            );
        }


        inv.setItem(35, new ItemBuilder(Material.STRUCTURE_VOID)
                        .setTitle("&cCofnij")
                        .build(),
                event -> {
                    ShopGui.open(player);
                }
        );

        inv.open(player);
    }
}
