package pl.chudziudgi.core.feature.shop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.feature.shop.time.TimeShopGui;
import pl.chudziudgi.core.util.ChatUtil;

public class ShopGui {

    public static void open(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Sklep menu:", InventoryType.FURNACE);

        inv.setItem(0, new ItemBuilder(Material.BRUSH)
                        .setTitle("&fItemShop")
                        .addLore("",
                                "&8Opis",
                                "&7Tutaj zakupisz przedmioty &b&lpremium",
                                "",
                                "&7Kliknij &3▜&7▛, aby otworzyć"
                        )
                        .build(),
                event -> {
                    ChatUtil.info(player, "");
                    ChatUtil.info(player, "&3Kliknij &7aby otworzyć stronę &b✌➲");
                    ChatUtil.info(player, "    &3&nhttps://KlanMC.pl/");
                    ChatUtil.info(player, "");
                    player.closeInventory();
                }
        );

        inv.setItem(1, new ItemBuilder(Material.CLOCK)
                        .setTitle("&fSklep za czas")
                        .addLore("",
                                "&8Opis",
                                "&7Tutaj wydasz monety zarobione za",
                                "&7przegrany czas na serwerze.",
                                "",
                                "&7Kliknij &3▜&7▛, aby otworzyć"
                        )
                        .build(),
                event -> {
                    TimeShopGui.open(player);
                }
        );

        inv.setItem(2, new ItemBuilder(Material.ENDER_CHEST)
                        .setTitle("&fOdbierz zakupione przedmioty")
                        .addLore("",
                                "&8Opis",
                                "&7Tutaj odbierzesz zakupone przedmioty",
                                "&7z sklepu premium.",
                                "",
                                "&7Kliknij &3▜&7▛, aby otworzyć"
                        )
                        .build(),
                event -> {
                }
        );

        inv.open(player);
    }
}
