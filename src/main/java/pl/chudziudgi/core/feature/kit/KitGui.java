package pl.chudziudgi.core.feature.kit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.drop.DropUtil;

import java.util.Objects;

public class KitGui {

    public static void openMain(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Zestawy", InventoryType.HOPPER);
        final User user = UserManager.get(player);

        inv.setItem(1, new ItemBuilder(Material.LEATHER_HELMET)
                        .setTitle("&fZestaw &e&lSTART")
                        .addLore("",
                                "",
                                "&7Kliknij &3▜&7▛, aby otworzyć podgląd",
                                "")
                        .build(),
                event -> {
            openkit(player, KitType.START);

                }
        );

        inv.setItem(2, new ItemBuilder(Material.IRON_HELMET)
                        .setTitle("&fZestaw &f&lIRON")
                        .addLore("",
                                "",
                                "&7Kliknij &3▜&7▛, aby otworzyć podgląd",
                                "")
                        .build(),
                event -> {
                    openkit(player, KitType.IRON);

                }
        );


        inv.setItem(3, new ItemBuilder(Material.GOLDEN_HELMET)
                        .setTitle("&fZestaw &e&lGOLD")
                        .addLore("",
                                "",
                                "&7Kliknij &3▜&7▛, aby otworzyć podgląd",
                                "")
                        .build(),
                event -> {

                }
        );

        inv.open(player);
    }

    public static void openkit(final Player player, KitType kitType) {
        final Integer[] slotList = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};

        final InventoryBuilder inv = new InventoryBuilder("&9Podgląd: " + KitManager.getKitName(kitType), 9 * 5);
        final User user = UserManager.get(player);
        int i = 0;

        for (ItemStack itemStack : Objects.requireNonNull(KitManager.getKitList(kitType))) {

            inv.setItem(slotList[i++], itemStack,
                    event -> {
                        player.closeInventory();
                        openMain(player);
                    }
            );

        }



        inv.setItem(36, new ItemBuilder(Material.GRAY_DYE)
                        .setTitle("&cCofnij")
                        .addLore("",
                                "",
                                "&7Kliknij &3▜&7▛, aby cofnąć strone",
                                "")
                        .build(),
                event -> {
                    player.closeInventory();
                    openMain(player);
                }
        );

        inv.setItem(44, new ItemBuilder(Material.LIGHT_BLUE_DYE)
                        .setTitle("&3Odbierz zestaw")
                        .addLore("",
                                "",
                                "&7Kliknij &3▜&7▛, aby odebrać zestaw",
                                "")
                        .build(),
                event -> {
                    player.closeInventory();
                    KitManager.giveKit(player,kitType);
                }
        );
        inv.open(player);
    }
}
