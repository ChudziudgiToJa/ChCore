package pl.chudziudgi.core.feature.backup;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class BackupAdminGui {

    public static void openBackup(final Player player, final Player target) {
        final Integer[] slotList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11};


        final InventoryBuilder inv = new InventoryBuilder("&7Zapisane ekwipunki gracza: &f" + target.getName(), 9 * 3);
        User user = UserManager.get(target);
        int i = 0;


        for (Backup backup : user.backupList) {
            ItemStack itemStack = new ItemBuilder(Material.SKELETON_SKULL)
                    .setTitle(backup.getInstantFormat())
                    .addLore("",
                            "&7Kliknij &3▜&7▛, aby otworzyć podgląd ekwipunku",
                            ""
                    )
                    .build();
            inv.setItem(slotList[i++], itemStack, event -> {
                giveBackup(player, target, backup);
            });

        }

        inv.setItem(26, new ItemBuilder(Material.STRUCTURE_VOID).setTitle("&cZamknij").addLore("", "&7Kliknij &3▜&7▛, aby zamknąć", "").build(), event -> {
            player.closeInventory();
        });

        inv.open(player);
    }

    public static void giveBackup(final Player player, final Player target, final Backup backup) {
        BackupManager backupManager = new BackupManager();
        final Integer[] slotList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};


        final InventoryBuilder inv = new InventoryBuilder(target.getName() + "&8| &7" + backup.getInstantFormat(), 9 * 6);
        int i = 0;


        for (ItemStack itemStack : backup.getItemStackList()) {
            inv.setItem(slotList[i++], itemStack, event -> {
                player.closeInventory();
            });
        }

        inv.setItem(45, new ItemBuilder(Material.STRUCTURE_VOID).setTitle("&cCofnij").addLore("", "&7Kliknij &3▜&7▛, aby cofnąć strone", "").build(), event -> {
            player.closeInventory();
            openBackup(player, target);
        });

        inv.setItem(49, new ItemBuilder(Material.EXPERIENCE_BOTTLE)
                .setTitle(new MessageBuilder().setText("&7lvl: &3{lvl} & &7exp: &3{exp}")
                        .addField("{lvl}", Integer.toString(backup.getLvl()))
                        .addField("{exp}", Float.toString(backup.getExp()))
                        .build())
                .build(), event -> {
        });

        inv.setItem(53, new ItemBuilder(Material.LIGHT_BLUE_DYE).setTitle("&3Nadaj ekwipunek").addLore("", "&7Kliknij &3▜&7▛, aby nadać ekwipunek", "").build(), event -> {
                    player.closeInventory();
                    ChatUtil.success(player, "Nadano zapasowy ekwipunek z dnia: " + backup.getInstantFormat() + " dla " + target.getName());
                    backupManager.answerBackup(target, backup);
                }
        );
        inv.open(player);
    }

}


