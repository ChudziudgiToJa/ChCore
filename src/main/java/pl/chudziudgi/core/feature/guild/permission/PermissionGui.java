package pl.chudziudgi.core.feature.guild.permission;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class PermissionGui {

    public static void open(final Player player, final Player target) {
        final InventoryBuilder inv = new InventoryBuilder("&9Uprawnienia: " + target.getName(), InventoryType.HOPPER);
        final User user = UserManager.get(target);

        inv.setItem(1, new ItemBuilder(Material.GOLDEN_PICKAXE)
                        .setTitle("&fNiszczenie bloków")
                        .addLore("",
                                "&8Opis:",
                                "&7Zarządzaj możliwością niszczenia bloków.",
                                "",
                                "&7Pozwól graczu na:",
                                " &b• &3Niszczenie bloków",
                                "",
                                "&7Status: " + ChatUtil.booleanString(user.breakBlock),
                                "",
                                "&7Kliknij &3▜&7▛, aby zmienić",
                                ""
                        )
                        .build(),
                event -> {
                    user.breakBlock = !user.breakBlock;
                    open(player, target);
                }
        );

        inv.setItem(2, new ItemBuilder(Material.TARGET)
                        .setTitle("&fStawianie bloków")
                        .addLore("",
                                "&8Opis:",
                                "&7Zarządzaj możliwością niszczenia bloków.",
                                "",
                                "&7Pozwól graczu na:",
                                " &b• &3Stawianie bloków",
                                "",
                                "&7Status: " + ChatUtil.booleanString(user.placeBlock),
                                "",
                                "&7Kliknij &3▜&7▛, aby zmienić",
                                ""
                        )
                        .build(),
                event -> {
                    user.placeBlock = !user.placeBlock;
                    open(player, target);
                }
        );

        inv.setItem(3, new ItemBuilder(Material.CHEST_MINECART)
                        .setTitle("&fInterakcja z blokami")
                        .addLore("",
                                "&8Opis:",
                                "&7Blokuje interakcje z wybranymi blokami,",
                                "&7takimi jak:",
                                " &b• &3Otwarcie",
                                " &b• &3Niszczenie",
                                "",
                                "&8--- Bloki ---",
                                "&3• " + Material.CHEST + " &7(Skrzynia)",
                                "&3• " + Material.TRAPPED_CHEST + " &7(Uzbrojona Skrzynia)",
                                "&3• " + Material.FURNACE + " &7(Piec)",
                                "&3• " + Material.SMOKER + " &7(Wędzarka)",
                                "&3• " + Material.BLAST_FURNACE + " &7(Kompaktowy Piec)",
                                "&3• " + Material.BREWING_STAND + " &7(Statyw Alchemiczny)",
                                "&3• " + Material.HOPPER + " &7(Lejek)",
                                "",
                                "&7status: " + ChatUtil.booleanString(user.useBlock),
                                "",
                                "&7Kliknij &3▜&7▛, aby zmienić",
                                ""
                        )
                        .build(),
                event -> {
                    user.useBlock = !user.useBlock;
                    open(player, target);
                }
        );
        inv.open(player);
    }
}
