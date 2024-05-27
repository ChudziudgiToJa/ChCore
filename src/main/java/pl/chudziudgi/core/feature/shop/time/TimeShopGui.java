package pl.chudziudgi.core.feature.shop.time;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.shop.ShopGui;

public class TimeShopGui {

    public static void open(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Sklep za czas:", 9*3);

        inv.setItem(10, new ItemBuilder(Material.IRON_HELMET)
                        .setTitle("&f&lIRON")
                        .addLore("",
                                "&8Opis",
                                "&7Otrzymasz wybraną range na cały sezon",
                                "",
                                "&7Koszt: &3720 monet",
                                "",
                                (TimeShopManager.canBuy(player ,TimeShopItem.IRON)) ? "&7Kliknij &3▜&7▛, aby kupić" : "&cNie stać cię"
                        )
                        .build(),
                event -> {
            TimeShopManager.buyItem(player, TimeShopItem.IRON);
            player.closeInventory();
                }
        );

        inv.setItem(11, new ItemBuilder(Material.GOLDEN_HELMET)
                        .setTitle("&e&lGOLD")
                        .addLore("",
                                "&8Opis",
                                "&7Otrzymasz wybraną range na cały sezon",
                                "",
                                "&7Koszt: &31440 monet",
                                "",
                                (TimeShopManager.canBuy(player ,TimeShopItem.GOLD)) ? "&7Kliknij &3▜&7▛, aby kupić" : "&cNie stać cię")
                        .build(),
                event -> {
                    TimeShopManager.buyItem(player, TimeShopItem.GOLD);
                    player.closeInventory();
                }
        );

        inv.setItem(13, new ItemBuilder(Material.COPPER_INGOT)
                        .setTitle("&7Twoje saldo: " + UserManager.get(player).timeShop)
                        .build(),
                event -> {
            inv.open(player);
                }
        );

        inv.setItem(16, new ItemBuilder(Material.GRAY_DYE)
                        .setTitle("&cCofnij")
                        .build(),
                event -> {
                    ShopGui.open(player);
                }
        );

        inv.open(player);
    }
}
