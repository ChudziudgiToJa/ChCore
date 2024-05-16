package pl.chudziudgi.core.feature.kit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;

public class KitGui {

    public static void open(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&8Zestawy", InventoryType.HOPPER);
        final User user = UserManager.get(player);

        inv.setItem(1, new ItemBuilder(Material.LEATHER_HELMET)
                        .setTitle("&fZestaw &e&lSTART")
                        .addLore("",
                                "&8Opis",
                                " &7Zawiera startowe przedmioty",
                                "")
                        .build(),
                event -> {

                }
        );

        inv.setItem(2, new ItemBuilder(Material.IRON_HELMET)
                        .setTitle("&fZestaw &f&lIRON")
                        .addLore("",
                                "&8Opis",
                                " &7Zawiera startowe przedmioty",
                                "")
                        .build(),
                event -> {

                }
        );


        inv.setItem(3, new ItemBuilder(Material.GOLDEN_HELMET)
                        .setTitle("&fZestaw &e&lGOLD")
                        .addLore("",
                                "&8Opis",
                                " &7Zawiera startowe przedmioty",
                                "")
                        .build(),
                event -> {

                }
        );

        inv.open(player);
    }
}
