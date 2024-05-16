package pl.chudziudgi.core.feature.drop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;



public class OverWorldDropGui {

    static final Integer[] slotList = {10, 11, 12, 13, 14, 15, 16};

    public static Integer[] getSlotList() {
        return slotList;
    }

    public static void open(final Player player) {
        final DropConfig dropConfig = new DropConfig();
        final InventoryBuilder inv = new InventoryBuilder("&7Drop &a&nziemia", 9 * 5);
        final User user = UserManager.get(player);
        int i = 0;
        for (Drop drop : dropConfig.getOverWorldDropList()) {
            inv.setItem(getSlotList()[i++], new ItemBuilder(drop.getItemStack().getType(), drop.getItemStack().getDurability()).setTitle("&f" + drop.getName()).addLore("&7Szansa: &f" + drop.getChance() + "%", "&7Ilosc: &f" + drop.getMinAmount() + "-" + drop.getMaxAmount(), "&7exp: &7" + drop.getExp(), "&7Fortuna: " + (drop.isFortune() ? "&aTAK" : "&cNIE"), "", "&fFortuna zwieksza drop&8:", " &eFortuna I &8(&f+0.5%&8)", " &eFortuna II &8(&f+0.10%&8)", " &eFortuna III &8(&f+0.15%&8)", "", "&7Status: " + (user.enabledDrops.contains(drop) ? "&awlaczony" : "&cwylaczony"), "","&7Kliknij &3▜&7▛, aby zmienić status").setGlow(user.enabledDrops.contains(drop)).build(), e -> {
                user.changeDropStatus(drop);
                open(player);
            });
        }

        final ItemStack dropCobblestone = new ItemBuilder(Material.COBBLESTONE).setTitle("&8Bruk").addLore("&7Status: " + (user.dropCobbleStone ? "&aTAK" : "&cNIE"), "","&7Kliknij &3▜&7▛, aby zmienić status").setGlow(user.dropCobbleStone).build();
        inv.setItem(32, dropCobblestone, e -> {
            user.dropCobbleStone = !user.dropCobbleStone;
            open(player);
        });

        final ItemStack dropMessage = new ItemBuilder(Material.WRITABLE_BOOK).setTitle("&dWiadomości o dropie").addLore("&7Status: " + (user.dropMessage ? "&aTAK" : "&cNIE"), "", "&7Kliknij &3▜&7▛, aby zmienić status").setGlow(user.dropMessage).build();
        inv.setItem(33, dropMessage, e -> {
            user.dropMessage = !user.dropMessage;
            open(player);
        });

        final ItemStack disableItem = new ItemBuilder(Material.RED_CANDLE).setTitle("&cWylacz caly drop").addLore("","&7Kliknij &3▜&7▛, aby wyłączyć wszystkie przedmioty").build();
        inv.setItem(29, disableItem, e -> {
            user.enabledDrops.clear();
            for (final Drop drop : dropConfig.getOverWorldDropList()) user.setDropStatus(drop, false);
            open(player);
        });

        final ItemStack enableItem = new ItemBuilder(Material.GREEN_CANDLE).setTitle("&aWlacz caly drop").addLore("","&7Kliknij &3▜&7▛, aby włączyć wszystkie przedmioty").build();
        inv.setItem(28, enableItem, e -> {
            user.enabledDrops.clear();
            for (final Drop drop : dropConfig.getOverWorldDropList()) user.setDropStatus(drop, true);
            open(player);
        });

        final ItemStack world = new ItemBuilder(Material.GRASS_BLOCK).setTitle("&6świat &8(&7Ziemia&8)").addLore("", "&aZiemia &8➝ &cPiekło", "&7Kliknij &3▜&7▛, aby zmienić świat").build();
        inv.setItem(34, world, e -> {
            NetherDropGui.open(player);
        });

        final ItemStack info = new ItemBuilder(Material.OAK_HANGING_SIGN).setTitle("&bMnożniki dropu").addLore("","&7Więcej dropu z rangą &b⬇", "  &f&lIRON &75%", "  &e&lGOLD &710%", "").build();
        inv.setItem(30, info, e -> {
        });

        inv.open(player);
    }
}

