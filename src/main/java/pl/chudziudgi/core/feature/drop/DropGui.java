package pl.chudziudgi.core.feature.drop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;

public class DropGui {

    public static final DropConfig dropConfig = new DropConfig();
    private static final Integer[] slotList = {10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25};

    public static Integer[] getSlotList() {
        return slotList;
    }

    public static void open(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Drop", 9 * 6);
        final User user = UserManager.get(player);
        int i = 0;
        for (Drop drop : dropConfig.getDropList()) {
            inv.setItem(getSlotList()[i++], new ItemBuilder(drop.getItemStack().getType(), drop.getItemStack().getDurability()).setTitle("&f" + drop.getName()).addLore("&7Szansa: &f" + drop.getChance() + "%", "&7Ilosc: &f" + drop.getMinAmount() + "-" + drop.getMaxAmount(), "&7exp: &7" + drop.getExp(), "&7Fortuna: " + (drop.isFortune() ? "&aTAK" : "&cNIE"), "&7Zdobędziesz w świecie " + drop.getWorldType().toString(), "", "&fFortuna zwieksza drop&8:", " &eFortuna I &8(&f+0.5%&8)", " &eFortuna II &8(&f+0.10%&8)", " &eFortuna III &8(&f+0.15%&8)", "", "&7Status: " + (user.enabledDrops.contains(drop) ? "&awlaczony" : "&cwylaczony"), "", "&7Kliknij &3▜&7▛, aby zmienić status").setGlow(user.enabledDrops.contains(drop)).build(), e -> {
                user.changeNetherDropStatus(drop);
                open(player);
            });
        }

        final ItemStack disableItem = new ItemBuilder(Material.LIGHT_BLUE_DYE).setTitle("&cWyłącz caly drop").addLore("", "&7Kliknij &3▜&7▛, aby zmienić status").build();
        inv.setItem(38, disableItem, e -> {
            user.enabledDrops.clear();
            for (final Drop drop : dropConfig.getDropList()) user.setNetherDropStatus(drop, true);
            open(player);
        });

        final ItemStack enableItem = new ItemBuilder(Material.GRAY_DYE).setTitle("&cWyłącz caly drop").addLore("", "&7Kliknij &3▜&7▛, aby zmienić status").build();
        inv.setItem(39, enableItem, e -> {
            user.enabledDrops.clear();
            for (final Drop drop : dropConfig.getDropList()) user.setNetherDropStatus(drop, false);
            open(player);
        });

        final ItemStack info = new ItemBuilder(Material.OAK_HANGING_SIGN).setTitle("&bMnożniki dropu").addLore("", "&7Więcej dropu z rangą &b⬇", "  &f&lIRON &75%", "  &e&lGOLD &710%", "").build();
        inv.setItem(40, info, e -> {
        });

        final ItemStack block = new ItemBuilder(Material.ENDER_CHEST).setTitle("&eDrop orginalnego bloku").addLore("&7Status: " + (user.dropOrginalBlock ? "&aTAK" : "&cNIE"), "", "&7Kliknij &3▜&7▛, aby zmienić status").setGlow(user.dropOrginalBlock).build();
        inv.setItem(41, block, e -> {
            user.dropOrginalBlock = !user.dropOrginalBlock;
            open(player);
        });

        final ItemStack dropMessage = new ItemBuilder(Material.WRITABLE_BOOK).setTitle("&dWiadomości").addLore("&7Status: " + (user.dropMessage ? "&aTAK" : "&cNIE"), "", "&7Kliknij &3▜&7▛, aby zmienić status").setGlow(user.dropMessage).build();
        inv.setItem(42, dropMessage, e -> {
            user.dropMessage = !user.dropMessage;
            open(player);
        });

        inv.open(player);
    }
}

