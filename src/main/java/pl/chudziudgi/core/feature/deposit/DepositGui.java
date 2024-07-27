package pl.chudziudgi.core.feature.deposit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;

public class DepositGui {

    public static void open(final Player player) {
        final PluginConfiguration config = new PluginConfiguration();
        final InventoryBuilder inv = new InventoryBuilder("&9Schowek", 9 * 4);
        final User user = UserManager.get(player);
        inv.setItem(10, new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE)
                        .setTitle("&fKoxy")
                        .addLore(
                                "",
                                " &fW schowku&8: &3" + user.dEnchantedGoldenApple,
                                " &fLimit w EQ&8: &3" + config.depositSettings.enchantedGoldenAppleLimit,
                                "",
                                "&7Kliknij &3▜&7▛, aby wypłacić przedmiot"
                        )
                        .build(), event -> {
                    DepositUtil.withdrawItem(player, Material.ENCHANTED_GOLDEN_APPLE, user.dEnchantedGoldenApple);
                    open(player);
                }
        );
        inv.setItem(11, new ItemBuilder(Material.GOLDEN_APPLE)
                        .setTitle("&fRefile")
                        .addLore(
                                "",
                                " &fW schowku&8: &3" + user.dGoldenApple,
                                " &fLimit w EQ&8: &3" + config.depositSettings.goldenAppleLimit,
                                "",
                                "&7Kliknij &3▜&7▛, aby wypłacić przedmiot"
                        )
                        .build(), event -> {
                    DepositUtil.withdrawItem(player, Material.GOLDEN_APPLE, user.dGoldenApple);
                    open(player);
                }
        );
        inv.setItem(12, new ItemBuilder(Material.ENDER_PEARL)
                        .setTitle("&fPerły")
                        .addLore(
                                "",
                                " &fW schowku&8: &3" + user.dEnderPearl,
                                " &fLimit w EQ&8: &3" + config.depositSettings.enderPearlLimit,
                                "",
                                "&7Kliknij &3▜&7▛, aby wypłacić przedmiot"
                        )
                        .build(), event -> {
                    DepositUtil.withdrawItem(player, Material.ENDER_PEARL, user.dEnderPearl);
                    open(player);
                }
        );

        inv.setItem(13, new ItemBuilder(Material.PACKED_ICE)
                        .setTitle("&fLód")
                        .addLore(
                                "",
                                " &fW schowku&8: &3" + user.dIce,
                                " &fLimit w EQ&8: &3" + config.depositSettings.iceLimit,
                                "",
                                "&7Kliknij &3▜&7▛, aby wypłacić przedmiot"
                        )
                        .build(), event -> {
                    DepositUtil.withdrawItem(player, Material.PACKED_ICE, user.dIce);
                    open(player);
                }
        );

        inv.setItem(14, new ItemBuilder(Material.TOTEM_OF_UNDYING)
                        .setTitle("&fTotemy")
                        .addLore(
                                "",
                                " &fW schowku&8: &3" + user.dTotemOfUndying,
                                " &fLimit w EQ&8: &3" + config.depositSettings.totemOfUndyingLimit,
                                "",
                                "&7Kliknij &3▜&7▛, aby wypłacić przedmiot"
                        )
                        .build(), event -> {
                    DepositUtil.withdrawItem(player, Material.TOTEM_OF_UNDYING, user.dTotemOfUndying);
                    open(player);
                }
        );
        inv.setItem(15, new ItemBuilder(Material.ARROW)
                        .setTitle("&fStrzały").addLore("",
                                " &fW schowku&8: &3" + user.dArrow,
                                " &fLimit w EQ&8: &3" + config.depositSettings.arrowLimit,
                                "",
                                "&7Kliknij &3▜&7▛, aby wypłacić przedmiot"
                        )
                        .build(), event -> {
                    DepositUtil.withdrawItem(player, Material.ARROW, user.dArrow);
                    open(player);
                }
        );
        inv.setItem(16, new ItemBuilder(Material.CHORUS_FRUIT)
                        .setTitle("&fChorus")
                        .addLore("",
                                " &fW schowku&8: &3" + user.dChorus,
                                " &fLimit w EQ&8: &3" + config.depositSettings.chorusLimit,
                                "",
                                "&7Kliknij &3▜&7▛, aby wypłacić przedmiot"
                        )
                        .build(), event -> {
                    DepositUtil.withdrawItem(player, Material.CHORUS_FRUIT, user.dChorus);
                    open(player);
                }
        );

        inv.setItem(22, new ItemBuilder(Material.HOPPER).setTitle("&fWypłać wszystko").addLore("", "&7Kliknij &3▜&7▛, aby wypłacić wszystkie przedmioty").build(), event -> {
            DepositUtil.withdrawItem(player, Material.GOLDEN_APPLE, user.dGoldenApple);
            DepositUtil.withdrawItem(player, Material.ENCHANTED_GOLDEN_APPLE, user.dEnchantedGoldenApple);
            DepositUtil.withdrawItem(player, Material.ENDER_PEARL, user.dEnderPearl);
            DepositUtil.withdrawItem(player, Material.TOTEM_OF_UNDYING, user.dTotemOfUndying);
            DepositUtil.withdrawItem(player, Material.ARROW, user.dArrow);
            DepositUtil.withdrawItem(player, Material.CHORUS_FRUIT, user.dChorus);
            DepositUtil.withdrawItem(player, Material.PACKED_ICE, user.dIce);
            player.closeInventory();

        });
        inv.open(player);
    }
}
