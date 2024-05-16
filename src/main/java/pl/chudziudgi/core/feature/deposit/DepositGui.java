package pl.chudziudgi.core.feature.deposit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;

public class DepositGui {

    public static void open(final Player player) {
        final DepositConfig depositConfig = new DepositConfig();
        final InventoryBuilder inv = new InventoryBuilder("&9Schowek", 9);
        final User user = UserManager.get(player);
        inv.setItem(0, new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE)
                .setTitle("&fKoxy")
                .addLore(
                        "",
                        " &fW schowku&8: &3" + user.dEnchantedGoldenApple,
                        " &fLimit w EQ&8: &3" + depositConfig.enchantedGoldenAppleLimit,
                        "",
                        "&7Kliknij &3▜&7▛, aby wypłacić przedmiot"
                )
                .build(), event ->
                DepositUtil.withdrawEnchantedGoldenApple(player)
        );
        inv.setItem(1, new ItemBuilder(Material.GOLDEN_APPLE)
                .setTitle("&fRefile")
                .addLore(
                        "",
                        " &fW schowku&8: &3" + user.dGoldenApple,
                        " &fLimit w EQ&8: &3" + depositConfig.getGoldenAppleLimit(),
                        "",
                        "&7Kliknij &3▜&7▛, aby wypłacić przedmiot"
                )
                .build(), event ->
                DepositUtil.withdrawGoldenApple(player)
        );
        inv.setItem(2, new ItemBuilder(Material.ENDER_PEARL)
                .setTitle("&fPerły")
                .addLore(
                        "",
                        " &fW schowku&8: &3" + user.dEnderPearl,
                        " &fLimit w EQ&8: &3" + depositConfig.getEnderPearlLimit(),
                        "",
                        "&7Kliknij &3▜&7▛, aby wypłacić przedmiot"
                )
                .build(), event ->
                DepositUtil.withdrawEnderPearls(player)
        );

        inv.setItem(4, new ItemBuilder(Material.RECOVERY_COMPASS).setTitle("&fWypłać wszystko").addLore("","&7Kliknij &3▜&7▛, aby wypłacić wszystkie przedmioty").build(), event -> {
            DepositUtil.withdrawGoldenApple(player);
            DepositUtil.withdrawEnchantedGoldenApple(player);
            DepositUtil.withdrawEnderPearls(player);
            DepositUtil.withdrawTotem(player);
            DepositUtil.withdrawArrow(player);
            DepositUtil.withdrawChorus(player);
        });

        inv.setItem(6, new ItemBuilder(Material.TOTEM_OF_UNDYING)
                .setTitle("&fTotemy")
                .addLore(
                        "",
                        " &fW schowku&8: &3" + user.dTotemOfUndying,
                        "&8» &fLimit w EQ&8: &3" + depositConfig.getTotemOfUndyingLimit(),
                        "",
                        "&7Kliknij &3▜&7▛, aby wypłacić przedmiot"
                )
                .build(), event ->
                DepositUtil.withdrawTotem(player)
        );
        inv.setItem(7, new ItemBuilder(Material.ARROW)
                .setTitle("&fStrzały").addLore("",
                        " &fW schowku&8: &3" + user.dArrow,
                        " &fLimit w EQ&8: &3" + depositConfig.getArrowLimit(),
                        "",
                        "&7Kliknij &3▜&7▛, aby wypłacić przedmiot"
                )
                .build(), event ->
                DepositUtil.withdrawArrow(player)
        );
        inv.setItem(8, new ItemBuilder(Material.CHORUS_FRUIT)
                .setTitle("&fChorus")
                .addLore("",
                        " &fW schowku&8: &3" + user.dChorus,
                        " &fLimit w EQ&8: &3" + depositConfig.getChorusLimit(),
                        "",
                        "&7Kliknij &3▜&7▛, aby wypłacić przedmiot"
                )
                .build(), event ->
                DepositUtil.withdrawChorus(player)
        );
        inv.open(player);
    }
}
