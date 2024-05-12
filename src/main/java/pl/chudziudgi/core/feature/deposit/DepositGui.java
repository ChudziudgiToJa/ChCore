package pl.chudziudgi.core.feature.deposit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.database.UserManager;

public class DepositGui {

    public static void open(final Player player) {
        final DepositConfig depositConfig = new DepositConfig();
        final InventoryBuilder inv = new InventoryBuilder("&8Schowek", 9);
        final User user = UserManager.getUser(player);
        inv.setItem(0, new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE).setTitle("&fKoxy").addLore("", " &fW schowku&8: &a" + user.dEnchantedGoldenApple, " &fLimit w EQ&8: &a" + depositConfig.enchantedGoldenAppleLimit, "", " &ePPM &8- &fWyplacasz item do ekwipunku.").build(), event -> DepositUtil.withdrawEnchantedGoldenApple(player));
        inv.setItem(1, new ItemBuilder(Material.GOLDEN_APPLE).setTitle("&fRefile").addLore("", " &fW schowku&8: &a" + user.dGoldenApple, " &fLimit w EQ&8: &a" + depositConfig.getGoldenAppleLimit(), "", " &ePPM &8- &fWyplacasz item do ekwipunku.").build(), event -> DepositUtil.withdrawGoldenApple(player));
        inv.setItem(2, new ItemBuilder(Material.ENDER_PEARL).setTitle("&fPerły").addLore("", " &fW schowku&8: &a" + user.dEnderPearl, " &fLimit w EQ&8: &a" + depositConfig.getEnderPearlLimit(), "", " &ePPM &8- &fWyplacasz item do ekwipunku.").build(), event -> DepositUtil.withdrawEnderPearls(player));

        inv.setItem(4, new ItemBuilder(Material.RECOVERY_COMPASS).setTitle("&fWypłać wszystko").build(), event -> {
            DepositUtil.withdrawGoldenApple(player);
            DepositUtil.withdrawEnchantedGoldenApple(player);
            DepositUtil.withdrawEnderPearls(player);
            DepositUtil.withdrawTotem(player);
            DepositUtil.withdrawArrow(player);
            DepositUtil.withdrawChorus(player);
        });

        inv.setItem(6, new ItemBuilder(Material.TOTEM_OF_UNDYING).setTitle("&fTotemy").addLore("", " &fW schowku&8: &a" + user.dTotemOfUndying, "&8» &fLimit w EQ&8: &a" + depositConfig.getTotemOfUndyingLimit(), "", " &ePPM &8- &fWyplacasz item do ekwipunku.").build(), event -> DepositUtil.withdrawTotem(player));
        inv.setItem(7, new ItemBuilder(Material.ARROW).setTitle("&fStrzały").addLore("", " &fW schowku&8: &a" + user.dArrow, " &fLimit w EQ&8: &a" + depositConfig.getArrowLimit(), "", " &ePPM &8- &fWyplacasz item do ekwipunku.").build(), event -> DepositUtil.withdrawArrow(player));
        inv.setItem(8, new ItemBuilder(Material.CHORUS_FRUIT).setTitle("&fChorus").addLore("", " &fW schowku&8: &a" + user.dChorus, " &fLimit w EQ&8: &a" + depositConfig.getChorusLimit(), "", " &ePPM &8- &fWyplacasz item do ekwipunku.").build(), event -> DepositUtil.withdrawChorus(player));
        inv.open(player);
    }
}
