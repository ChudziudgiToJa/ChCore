package pl.chudziudgi.core.feature.shop.answer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.shop.ShopGui;
import pl.chudziudgi.core.feature.shop.time.TimeShopManager;

public class AnswerGui {
    public static void open(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Sklep menu:", InventoryType.HOPPER);
        User user = UserManager.get(player);

        inv.setItem(1, new ItemBuilder(Material.LIGHT_BLUE_CANDLE)
                        .setTitle("&fMagiczna świeca &e★")
                        .addLore("",
                                "&8Opis",
                                "&7Magiczna świeca to tajemniczy przedmiot,",
                                "&7który po użyciu losowo przemienia się w cenny skarb.",
                                "",
                                "&8Posiadasz do odebrania: " + UserManager.get(player).answerCandle,
                                "",
                                (AnswerManager.isHaveCandle(user) ? "&7Kliknij &3▜&7▛, aby odebrać" : "&cNie posiadasz nic do odebrania")
                        )
                        .build(),
                event -> {
            AnswerManager.giveCandle(user);
            player.closeInventory();
                }
        );

        inv.setItem(1, new ItemBuilder(Material.LIGHT_BLUE_CANDLE)
                        .setTitle("&fRanga &f&lIRON")
                        .addLore("",
                                "&8Opis",
                                "&7Otrzymasz wybraną range na cały sezon",
                                "",
                                (AnswerManager.isHaveCandle(user) ? "&7Kliknij &3▜&7▛, aby odebrać" : "&cNie posiadasz nic do odebrania")
                        )
                        .build(),
                event -> {
                    player.closeInventory();
                }
        );

        inv.setItem(1, new ItemBuilder(Material.LIGHT_BLUE_CANDLE)
                        .setTitle("&fRanga &e&lGOLD")
                        .addLore("",
                                "&8Opis",
                                "&7Otrzymasz wybraną range na cały sezon",
                                "",
                                (AnswerManager.isHaveCandle(user) ? "&7Kliknij &3▜&7▛, aby odebrać" : "&cNie posiadasz nic do odebrania")
                        )
                        .build(),
                event -> {
                    player.closeInventory();
                }
        );

        inv.setItem(4, new ItemBuilder(Material.GRAY_DYE)
                        .setTitle("&cCofnij")
                        .build(),
                event -> {
                    ShopGui.open(player);
                }
        );

        inv.open(player);
    }
}
