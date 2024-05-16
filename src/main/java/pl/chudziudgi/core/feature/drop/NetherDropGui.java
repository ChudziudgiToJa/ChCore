package pl.chudziudgi.core.feature.drop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;

public class NetherDropGui {

    public static final DropConfig dropConfig = new DropConfig();
    private static final Integer[] slotList = {10, 11, 12, 13, 14, 15, 16};

    public static Integer[] getSlotList() {
        return slotList;
    }

    public static void open(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&7Drop &c&npiekło", 9 * 5);
        final User user = UserManager.get(player);
        int i = 0;
        for (Drop drop : dropConfig.getNetherDropList()) {
            inv.setItem(getSlotList()[i++], new ItemBuilder(drop.getItemStack().getType(), drop.getItemStack().getDurability()).setTitle("&f" + drop.getName()).addLore("&7Szansa: &f" + drop.getChance() + "%", "&7Ilosc: &f" + drop.getMinAmount() + "-" + drop.getMaxAmount(), "&7exp: &7" + drop.getExp(), "&7Fortuna: " + (drop.isFortune() ? "&aTAK" : "&cNIE"), "", "&fFortuna zwieksza drop&8:", " &eFortuna I &8(&f+0.5%&8)", " &eFortuna II &8(&f+0.10%&8)", " &eFortuna III &8(&f+0.15%&8)", "", "&7Status: " + (user.enabledNetherDrops.contains(drop) ? "&awlaczony" : "&cwylaczony"), "","&7Kliknij &3▜&7▛, aby zmienić status").setGlow(user.enabledNetherDrops.contains(drop)).build(), e -> {
                user.changeNetherDropStatus(drop);
                open(player);
            });
        }
        final ItemStack dropNetherrack = new ItemBuilder(Material.WARPED_NYLIUM).setTitle("&8Netherrack").addLore("&7Status: " + (user.dropNetherrack ? "&aTAK" : "&cNIE"), "","&7Kliknij &3▜&7▛, aby zmienić status").setGlow(user.dropNetherrack).build();
        inv.setItem(32, dropNetherrack, e -> {
            user.dropNetherrack = !user.dropNetherrack;
            open(player);
        });

        final ItemStack dropMessage = new ItemBuilder(Material.WRITABLE_BOOK).setTitle("&dWiadomości o dropie").addLore("&7Status: " + (user.dropMessage ? "&aTAK" : "&cNIE"), "","&7Kliknij &3▜&7▛, aby zmienić status").setGlow(user.dropMessage).build();
        inv.setItem(33, dropMessage, e -> {
            user.dropMessage = !user.dropMessage;
            open(player);
        });

        final ItemStack disableItem = new ItemBuilder(Material.RED_CANDLE).setTitle("&cWyłącz caly drop").addLore("","&7Kliknij &3▜&7▛, aby wyłączyć wszystkie przedmioty").build();
        inv.setItem(29, disableItem, e -> {
            user.enabledNetherDrops.clear();
            for (final Drop drop : dropConfig.getNetherDropList()) user.setNetherDropStatus(drop, false);
            open(player);
        });

        final ItemStack enableItem = new ItemBuilder(Material.GREEN_CANDLE).setTitle("&aWłącz caly drop").addLore("","&7Kliknij &3▜&7▛, aby włączyć wszystkie przedmioty").build();
        inv.setItem(28, enableItem, e -> {
            user.enabledNetherDrops.clear();
            for (final Drop drop : dropConfig.getNetherDropList()) user.setNetherDropStatus(drop, true);
            open(player);
        });

        final ItemStack info = new ItemBuilder(Material.OAK_HANGING_SIGN).setTitle("&bMnożniki dropu").addLore("","&7Więcej dropu z rangą &b⬇", "  &f&lIRON &75%", "  &e&lGOLD &710%", "").build();
        inv.setItem(30, info, e -> {
        });

        final ItemStack world = new ItemBuilder(Material.GRASS_BLOCK).setTitle("&6świat &8(&cPiekło&8)").addLore("", "&aZiemia &8➝ &cPiekło", "&7Kliknij &3▜&7▛, aby zmienić świat").build();
        inv.setItem(34, world, e -> {
            OverWorldDropGui.open(player);
        });

        inv.open(player);
    }
}

