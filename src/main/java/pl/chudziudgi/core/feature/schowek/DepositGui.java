package pl.chudziudgi.core.feature.schowek;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.database.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class DepositGui {

    public static void open(final Player player) {
        DepositConfig depositConfig = new DepositConfig();
        final InventoryBuilder inv = new InventoryBuilder("&8» &aSchowek&6:", 45);
        final User user = UserManager.getUser(player);
        inv.setItem(11,
                new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE)
                        .setTitle("&f&l» &6&lKoxy&8:")
                        .addLore("",
                                "&8» &fW schowku&8: &a" + user.dEnchantedGoldenApple,
                                "&8» &fLimit w EQ&8: &a" + depositConfig.enchantedGoldenAppleLimit,
                                "",
                                "&8» &ePPM &8- &fWyplacasz item do ekwipunku.").build(),
                event -> withdrawEnchantedGoldenApple(player));
        inv.setItem(12,
                new ItemBuilder(Material.GOLDEN_APPLE)
                        .setTitle("&f&l» &e&lRefile&8:")
                        .addLore("",
                                "&8» &fW schowku&8: &a" + user.dGoldenApple,
                                "&8» &fLimit w EQ&8: &a" + depositConfig.getGoldenAppleLimit(),
                                "",
                                "&8» &ePPM &8- &fWyplacasz item do ekwipunku.").build(),
                event -> withdrawGoldenApple(player));
        inv.setItem(13,
                new ItemBuilder(Material.ENDER_PEARL)
                        .setTitle("&f&l» &9&lPerly&8:")
                        .addLore("",
                                "&8» &fW schowku&8: &a" + user.dEnderPearl,
                                "&8» &fLimit w EQ&8: &a" + depositConfig.getEnderPearlLimit(),
                                "",
                                "&8» &ePPM &8- &fWyplacasz item do ekwipunku.").build(),
                event -> withdrawEnderPearls(player));
        inv.setItem(14,
                new ItemBuilder(Material.TOTEM_OF_UNDYING)
                        .setTitle("&f&l» &9&lTotemy&8:")
                        .addLore("",
                                "&8» &fW schowku&8: &a" + user.dTotemOfUndying,
                                "&8» &fLimit w EQ&8: &a" + depositConfig.getTotemOfUndyingLimit(),
                                "",
                                "&8» &ePPM &8- &fWyplacasz item do ekwipunku.").build(),
                event -> withdrawTotem(player));
        inv.setItem(15,
                new ItemBuilder(Material.ARROW)
                        .setTitle("&f&l» &9&lstrzaly&8:")
                        .addLore("",
                                "&8» &fW schowku&8: &a" + user.dArrow,
                                "&8» &fLimit w EQ&8: &a" + depositConfig.getArrowLimit(),
                                "",
                                "&8» &ePPM &8- &fWyplacasz item do ekwipunku.").build(),
                event -> withdrawEnderPearls(player));
        inv.setItem(22,new ItemBuilder(Material.NETHER_STAR).setTitle("&8>&7>&f> &a&lDOBIERZ DO LIMITU &f<&7<&8<").build(), event -> {
            withdrawEnchantedGoldenApple(player);
            withdrawGoldenApple(player);
            withdrawEnderPearls(player);
        });
        inv.open(player);
    }

    public static void withdrawEnchantedGoldenApple(final Player player) {
        final DepositConfig depositConfig = new DepositConfig();
        final User user = UserManager.getUser(player);
        if (user.dEnchantedGoldenApple > 0) {
            if (DepositUtil.getAmount(player, Material.ENCHANTED_GOLDEN_APPLE) < depositConfig.getEnchantedGoldenAppleLimit()) {
                if (user.dEnchantedGoldenApple > depositConfig.getEnchantedGoldenAppleLimit()) {
                    ChatUtil.success(player, "zabrano koxy");
                    DepositUtil.giveItems(player, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, depositConfig.getEnchantedGoldenAppleLimit()));
                    user.dEnchantedGoldenApple -= depositConfig.getEnchantedGoldenAppleLimit();
                } else {
                    ChatUtil.success(player, "Wypłacono  koxy");
                    DepositUtil.giveItems(player, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, user.dEnchantedGoldenApple));
                    user.dEnchantedGoldenApple -= user.dEnchantedGoldenApple;
                }
            }
        }
    }

    public static void withdrawGoldenApple(final Player player) {
        final DepositConfig depositConfig = new DepositConfig();
        final User user = UserManager.getUser(player);
        if (user.dGoldenApple > 0) {
            if (DepositUtil.getAmount(player, Material.GOLDEN_APPLE) < depositConfig.getGoldenAppleLimit()) {
                if (user.dGoldenApple > depositConfig.getGoldenAppleLimit()) {
                    ChatUtil.success(player, "zabrano ref");
                    DepositUtil.giveItems(player, new ItemStack(Material.GOLDEN_APPLE, depositConfig.getGoldenAppleLimit()));
                    user.dGoldenApple -= depositConfig.getGoldenAppleLimit();
                } else {
                    ChatUtil.success(player, "Wypłacono  ref");
                    DepositUtil.giveItems(player, new ItemStack(Material.GOLDEN_APPLE, user.dGoldenApple));
                    user.dGoldenApple -= user.dGoldenApple;
                }
            }
        }
    }

    public static void withdrawEnderPearls(final Player player) {
        final DepositConfig depositConfig = new DepositConfig();
        final User user = UserManager.getUser(player);
        if (user.dEnchantedGoldenApple > 0) {
            if (DepositUtil.getAmount(player, Material.ENDER_PEARL) < depositConfig.getEnderPearlLimit()) {
                if (user.dEnderPearl > depositConfig.getEnderPearlLimit()) {
                    ChatUtil.success(player, "zabrano per");
                    DepositUtil.giveItems(player, new ItemStack(Material.ENDER_PEARL, depositConfig.getEnderPearlLimit()));
                    user.dEnderPearl -= depositConfig.getEnderPearlLimit();
                } else {
                    ChatUtil.success(player, "Wypłacono  per");
                    DepositUtil.giveItems(player, new ItemStack(Material.ENDER_PEARL, user.dEnderPearl));
                    user.dEnderPearl -= user.dEnderPearl;
                }
            }
        }
    }
    public static void withdrawTotem(final Player player) {
        final DepositConfig depositConfig = new DepositConfig();
        final User user = UserManager.getUser(player);
        if (user.dTotemOfUndying > 0) {
            if (DepositUtil.getAmount(player, Material.TOTEM_OF_UNDYING) < depositConfig.getTotemOfUndyingLimit()) {
                if (user.dTotemOfUndying > depositConfig.getTotemOfUndyingLimit()) {
                    ChatUtil.success(player, "zabrano totem");
                    DepositUtil.giveItems(player, new ItemStack(Material.TOTEM_OF_UNDYING, depositConfig.getTotemOfUndyingLimit()));
                    user.dTotemOfUndying -= depositConfig.getTotemOfUndyingLimit();
                } else {
                    ChatUtil.success(player, "Wypłacono  totem");
                    DepositUtil.giveItems(player, new ItemStack(Material.TOTEM_OF_UNDYING, user.dTotemOfUndying));
                    user.dTotemOfUndying -= user.dTotemOfUndying;
                }
            }
        }
    }
    public static void withdrawArrow(final Player player) {
        final DepositConfig depositConfig = new DepositConfig();
        final User user = UserManager.getUser(player);
        if (user.dArrow > 0) {
            if (DepositUtil.getAmount(player, Material.ARROW) < depositConfig.getArrowLimit()) {
                if (user.dArrow > depositConfig.getArrowLimit()) {
                    ChatUtil.success(player, "zabrano totem");
                    DepositUtil.giveItems(player, new ItemStack(Material.TOTEM_OF_UNDYING, depositConfig.getArrowLimit()));
                    user.dArrow -= depositConfig.getArrowLimit();
                } else {
                    ChatUtil.success(player, "Wypłacono  totem");
                    DepositUtil.giveItems(player, new ItemStack(Material.ARROW, user.dArrow));
                    user.dArrow -= user.dArrow;
                }
            }
        }
    }
}
