package pl.chudziudgi.core.feature.drop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.database.UserManager;

public class OverWorldDropGui {

    public static final DropConfig dropConfig = new DropConfig();
    private static final Integer[] slotList = {10, 11, 12, 13, 14, 15, 16};
    private static final ItemStack info = new ItemBuilder(Material.OAK_HANGING_SIGN).setTitle("&bMnożniki dropu").addLore("", "  &f&lIRON &70.5%", "  &e&lGOLD &710%", "","&aWięcej dropu z rangą").build();
    private static final ItemStack enableItem = new ItemBuilder(Material.GREEN_CANDLE).setTitle("&aWlacz caly drop").build();
    private static final ItemStack disableItem = new ItemBuilder(Material.RED_CANDLE).setTitle("&cWylacz caly drop").build();
    private static final ItemStack world = new ItemBuilder(Material.GRASS_BLOCK).setTitle("&6świat &8(&7Ziemia&8)").addLore("&7Kliknij aby zmienić drop na..", "&aZiemia &8➝ &cPiekło").build();

    public static Integer[] getSlotList() {
        return slotList;
    }

    public static void open(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&7Drop &a&nziemia", 9 * 5);
        final User user = UserManager.getUser(player);
        int i = 0;
        for (Drop drop : dropConfig.getOverWorldDropList()) {
            inv.setItem(getSlotList()[i++], new ItemBuilder(drop.getItemStack().getType(), drop.getItemStack().getDurability()).setTitle("&f" + drop.getName()).addLore("&7Szansa: &f" + drop.getChance() + "%", "&7Ilosc: &f" + drop.getMinAmount() + "-" + drop.getMaxAmount(), "&7exp: &7" + drop.getExp(), "&7Fortuna: " + (drop.isFortune() ? "&aTAK" : "&cNIE"), "", "&fFortuna zwieksza drop&8:", " &eFortuna I &8(&f+0.5%&8)", " &eFortuna II &8(&f+0.10%&8)", " &eFortuna III &8(&f+0.15%&8)", "", "&7Status: " + (user.isDropEnabled(drop) ? "&awlaczony" : "&cwylaczony")).setGlow(user.isDropEnabled(drop)).build(), e -> {
                user.changeDropStatus(drop);
                open(player);
            });
        }
        final ItemStack dropCobblestone = new ItemBuilder(Material.COBBLESTONE).setTitle("&8Bruk").addLore("&7Status: " + (user.dropCobbleStone ? "&aTAK" : "&cNIE")).setGlow(user.dropCobbleStone).build();
        inv.setItem(32, dropCobblestone, e -> {
            user.dropCobbleStone = !user.dropCobbleStone;
            open(player);
        });

        final ItemStack dropMessage = new ItemBuilder(Material.WRITABLE_BOOK).setTitle("&dWiadomości o dropie").addLore("&7Status: " + (user.dropMessage ? "&aTAK" : "&cNIE")).setGlow(user.dropMessage).build();
        inv.setItem(33, dropMessage, e -> {
            user.dropMessage = !user.dropMessage;
            open(player);
        });

        inv.setItem(29, disableItem, e -> {
            user.enabledDrops.clear();
            for (final Drop drop : dropConfig.getOverWorldDropList()) user.setDropStatus(drop, false);
            open(player);
        });

        inv.setItem(28, enableItem, e -> {
            user.enabledDrops.clear();
            for (final Drop drop : dropConfig.getOverWorldDropList()) user.setDropStatus(drop, true);
            open(player);
        });

        inv.setItem(34, world, e -> {
            NetherDropGui.open(player);
        });

        inv.setItem(30, info, e -> {
        });


        inv.open(player);
    }
}

