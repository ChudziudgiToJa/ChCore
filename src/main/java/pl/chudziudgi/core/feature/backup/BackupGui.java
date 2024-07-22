package pl.chudziudgi.core.feature.backup;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;

public class BackupGui {
    public static void openBackup(final Player player) {
        final Integer[] slotList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        final InventoryBuilder inv = new InventoryBuilder("&9Lista backup'ów do odebrania:", 9 * 3);
        User user = UserManager.get(player);
        int i = 0;


        for (Backup backup : user.backupAnswerList) {
            ItemStack itemStack = new ItemBuilder(Material.SKELETON_SKULL)
                    .setTitle(backup.getInstantFormat())
                    .addLore("",
                            "&7Kliknij &3▜&7▛, aby otworzyć podgląd ekwipunku",
                            ""
                    )
                    .build();
            inv.setItem(slotList[i++], itemStack, event -> {
                giveBackup(player, backup);
            });

        }
        inv.setItem(26, new ItemBuilder(Material.STRUCTURE_VOID).setTitle("&cZamknij").addLore("", "&7Kliknij &3▜&7▛, aby zamknąć", "").build(), event -> {
            player.closeInventory();
        });

        inv.open(player);
    }

    public static void giveBackup(final Player player, final Backup backup) {
        BackupManager backupManager = new BackupManager();
        final Integer[] slotList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};


        final InventoryBuilder inv = new InventoryBuilder(backup.getInstantFormat(), 9 * 6);
        int i = 0;


        for (ItemStack itemStack : backup.getItemStackList()) {
            inv.setItem(slotList[i++], itemStack, event -> {
                player.closeInventory();
            });
        }

        inv.setItem(45, new ItemBuilder(Material.STRUCTURE_VOID).setTitle("&cCofnij").addLore("", "&7Kliknij &3▜&7▛, aby cofnąć strone", "").build(), event -> {
            player.closeInventory();
            openBackup(player);
        });

        inv.setItem(49, new ItemBuilder(Material.BOOK)
                .setTitle(new MessageBuilder().setText("&7lvl: &3{lvl} &f& &7exp: &3{exp} &f& &7ranking: &3{points}")
                        .addField("{lvl}", Integer.toString(backup.getLvl()))
                        .addField("{exp}", Float.toString(backup.getExp()))
                        .addField("{points}", String.valueOf(backup.getPoints()))
                        .build())
                .build(), event -> {
        });

        inv.setItem(53, new ItemBuilder(Material.LIGHT_BLUE_DYE).setTitle("&3Odbierz przedmioty").addLore("", "&7Kliknij &3▜&7▛, aby odebrać przedmioty", "").build(), event -> {
                    player.closeInventory();
                    backupManager.giveBackup(player, backup);
                }
        );
        inv.open(player);
    }

}
