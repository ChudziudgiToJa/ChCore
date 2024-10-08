package pl.chudziudgi.core.feature.drop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.customitem.magiccandle.MagicCandleGui;
import pl.chudziudgi.core.util.ChatUtil;

public class DropGui {

    public static final PluginConfiguration config = new PluginConfiguration();
    private static final Integer[] slotList = {10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25};

    public static Integer[] getSlotList() {
        return slotList;
    }

    public static void openOverWorld(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Drop", 9 * 6);
        final User user = UserManager.get(player);
        int i = 0;
        for (final Drop drop : config.dropSettings.dropList) {
            inv.setItem(getSlotList()[i++], new ItemBuilder(drop.getMaterial())
                    .setTitle("&f" + drop.getName())
                    .addLore(
                            "&7Szansa: &f" + drop.getChance() + "%", "&7Ilosc: &f" + drop.getMinAmount() + "-" + drop.getMaxAmount(),
                            "&7exp: &7" + drop.getExp(),
                            "&7Fortuna: " + ChatUtil.booleanString(drop.isFortune()),
                            "",
                            "&7Status: " + ChatUtil.booleanString(user.dropList.contains(drop)),
                            "",
                            "&7Kliknij &3▜&7▛, aby zmienić status"
                    )
                    .setGlow(user.dropList.contains(drop)).build(), e -> {
                user.changeOverWorldDropStatus(drop);
                openOverWorld(player);
            });
        }

        final ItemStack disableItem = new ItemBuilder(Material.LIGHT_BLUE_DYE).setTitle("&3Włącz caly drop").addLore("", "&7Kliknij &3▜&7▛, aby zmienić status").build();
        inv.setItem(37, disableItem, e -> {
            user.dropList.clear();
            for (final Drop drop : config.dropSettings.dropList) user.setOverWorldDropStatus(drop, true);
            openOverWorld(player);
        });

        final ItemStack enableItem = new ItemBuilder(Material.GRAY_DYE).setTitle("&cWyłącz caly drop").addLore("", "&7Kliknij &3▜&7▛, aby zmienić status").build();
        inv.setItem(38, enableItem, e -> {
            user.dropList.clear();
            for (final Drop drop : config.dropSettings.dropList) user.setOverWorldDropStatus(drop, false);
            openOverWorld(player);
        });

        final ItemStack info = new ItemBuilder(Material.WARPED_HANGING_SIGN).setTitle("&fmnożniki&8:").addLore("", " &fⒾ &lIRON &8(&f+1.5%&8)", " &eⒼ &lGOLD &8(&f+2.0%&8)", "" ,"&fFortuna zwieksza mnożni&8:", " &eFortuna I &8(&f+0.5%&8)", " &eFortuna II &8(&f+1.5%&8)", " &eFortuna III &8(&f+2.0%&8)").build();
        inv.setItem(40, info, e -> {
        });

        final ItemStack block = new ItemBuilder(Material.ENDER_CHEST).setTitle("&eDrop orginalnego bloku").addLore("&7Status: " + ChatUtil.booleanString(user.dropOriginalBlock), "", "&7Kliknij &3▜&7▛, aby zmienić status").setGlow(user.dropOriginalBlock).build();
        inv.setItem(41, block, e -> {
            user.dropOriginalBlock = !user.dropOriginalBlock;
            openOverWorld(player);
        });

        final ItemStack dropMessage = new ItemBuilder(Material.WRITABLE_BOOK).setTitle("&dWiadomości").addLore("&7Status: " + ChatUtil.booleanString(user.dropMessage), "", "&7Kliknij &3▜&7▛, aby zmienić status").setGlow(user.dropMessage).build();
        inv.setItem(42, dropMessage, e -> {
            user.dropMessage = !user.dropMessage;
            openOverWorld(player);
        });

        final ItemStack back = new ItemBuilder(Material.STRUCTURE_VOID).setTitle("&cCofnij").addLore("", "&7Kliknij &3▜&7▛, aby cofnąć menu").build();
        inv.setItem(43, back, e -> {
            openChose(player);
        });

        inv.open(player);
    }

    public static void openChose(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&9Wybierz co cię interesuje", InventoryType.HOPPER);

        final ItemStack overWorld = new ItemBuilder(Material.MOSS_BLOCK).setTitle("&3Drop ze kamienia").addLore("", "&7Kliknij &3▜&7▛, aby otworzyć").build();
        inv.setItem(1, overWorld, e -> {
            openOverWorld(player);
        });

        final ItemStack candle = new ItemBuilder(Material.LIGHT_BLUE_CANDLE).setTitle("&3Drop z &fMagicznej świecy &e★").addLore("", "&7Kliknij &3▜&7▛, aby otworzyć").build();
        inv.setItem(3, candle, e -> {
            MagicCandleGui.openkit(player);
        });


        inv.open(player);
    }
}

