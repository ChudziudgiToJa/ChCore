package pl.chudziudgi.core.feature.deposit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.HashMap;
import java.util.Map;

public class DepositUtil {
    public static int getAmount(final Player player, final Material material) {
        int amount = 0;
        for (final ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null && itemStack.getType().equals(material)) {
                amount += itemStack.getAmount();
            }
        }
        return amount;
    }

    public static int remove(final Player player, final Material material, final int limit) {
        final PlayerInventory inv = player.getInventory();
        int removed = 0;

        ItemStack offHandItem = inv.getItemInOffHand();
        if (offHandItem.getType() == material) {
            removed += offHandItem.getAmount();
            offHandItem.setAmount(0);
        }
        for (final ItemStack slot : inv.getContents()) {
            if (slot != null && slot.getType() == material) {
                inv.remove(slot);
                removed += slot.getAmount();
            }
        }
        inv.addItem(new ItemStack(material, limit));
        player.updateInventory();
        return removed - limit;
    }


    public static void giveItems(final Player player, final ItemStack... items) {
        final HashMap<Integer, ItemStack> notStored = player.getInventory().addItem(items);
        for (final Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
            player.getWorld().dropItemNaturally(player.getLocation(), e.getValue());
        }
    }

    public static void withdrawEnchantedGoldenApple(final Player player) {
        final DepositConfig depositConfig = new DepositConfig();
        final User user = UserManager.get(player);
        if (user.dEnchantedGoldenApple > 0) {
            if (DepositUtil.getAmount(player, Material.ENCHANTED_GOLDEN_APPLE) < depositConfig.getEnchantedGoldenAppleLimit()) {
                if (user.dEnchantedGoldenApple > depositConfig.getEnchantedGoldenAppleLimit()) {
                    DepositUtil.giveItems(player, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, depositConfig.getEnchantedGoldenAppleLimit()));
                    user.dEnchantedGoldenApple -= depositConfig.getEnchantedGoldenAppleLimit();
                } else {
                    DepositUtil.giveItems(player, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, user.dEnchantedGoldenApple));
                    user.dEnchantedGoldenApple -= user.dEnchantedGoldenApple;
                }
                ChatUtil.success(player, withdrawMessage(Material.ENCHANTED_GOLDEN_APPLE, depositConfig.enchantedGoldenAppleLimit));
            } else {
                ChatUtil.error(player, maxLimitMessage(Material.ENCHANTED_GOLDEN_APPLE));
            }
        }else ChatUtil.error(player,noItemMessage(Material.ENCHANTED_GOLDEN_APPLE));
    }

    public static void withdrawGoldenApple(final Player player) {
        final DepositConfig depositConfig = new DepositConfig();
        final User user = UserManager.get(player);
        if (user.dGoldenApple > 0) {
            if (DepositUtil.getAmount(player, Material.GOLDEN_APPLE) < depositConfig.getGoldenAppleLimit()) {
                if (user.dGoldenApple > depositConfig.getGoldenAppleLimit()) {
                    DepositUtil.giveItems(player, new ItemStack(Material.GOLDEN_APPLE, depositConfig.getGoldenAppleLimit()));
                    user.dGoldenApple -= depositConfig.getGoldenAppleLimit();
                } else {
                    DepositUtil.giveItems(player, new ItemStack(Material.GOLDEN_APPLE, user.dGoldenApple));
                    user.dGoldenApple -= user.dGoldenApple;
                }
                ChatUtil.success(player, withdrawMessage(Material.GOLDEN_APPLE, depositConfig.getGoldenAppleLimit()));
            } else {
                ChatUtil.error(player, maxLimitMessage(Material.GOLDEN_APPLE));
            }
        }else ChatUtil.error(player,noItemMessage(Material.GOLDEN_APPLE));
    }

    public static void withdrawEnderPearls(final Player player) {
        final DepositConfig depositConfig = new DepositConfig();
        final User user = UserManager.get(player);
        if (user.dEnchantedGoldenApple > 0) {
            if (DepositUtil.getAmount(player, Material.ENDER_PEARL) < depositConfig.getEnderPearlLimit()) {
                if (user.dEnderPearl > depositConfig.getEnderPearlLimit()) {
                    DepositUtil.giveItems(player, new ItemStack(Material.ENDER_PEARL, depositConfig.getEnderPearlLimit()));
                    user.dEnderPearl -= depositConfig.getEnderPearlLimit();
                } else {
                    DepositUtil.giveItems(player, new ItemStack(Material.ENDER_PEARL, user.dEnderPearl));
                    user.dEnderPearl -= user.dEnderPearl;
                }
                ChatUtil.success(player, withdrawMessage(Material.ENDER_PEARL, depositConfig.getEnderPearlLimit()));
            } else {
                ChatUtil.error(player, maxLimitMessage(Material.ENDER_PEARL));
            }
        }else noItemMessage(Material.ENDER_PEARL);
    }

    public static void withdrawTotem(final Player player) {
        final DepositConfig depositConfig = new DepositConfig();
        final User user = UserManager.get(player);
        if (user.dTotemOfUndying > 0) {
            if (DepositUtil.getAmount(player, Material.TOTEM_OF_UNDYING) < depositConfig.getTotemOfUndyingLimit()) {
                if (user.dTotemOfUndying > depositConfig.getTotemOfUndyingLimit()) {
                    DepositUtil.giveItems(player, new ItemStack(Material.TOTEM_OF_UNDYING, depositConfig.getTotemOfUndyingLimit()));
                    user.dTotemOfUndying -= depositConfig.getTotemOfUndyingLimit();
                } else {
                    DepositUtil.giveItems(player, new ItemStack(Material.TOTEM_OF_UNDYING, user.dTotemOfUndying));
                    user.dTotemOfUndying -= user.dTotemOfUndying;
                }
                ChatUtil.success(player, withdrawMessage(Material.TOTEM_OF_UNDYING, depositConfig.getTotemOfUndyingLimit()));
            } else {
                ChatUtil.error(player, maxLimitMessage(Material.TOTEM_OF_UNDYING));
            }
        }else ChatUtil.error(player,noItemMessage(Material.TOTEM_OF_UNDYING));
    }

    public static void withdrawArrow(final Player player) {
        final DepositConfig depositConfig = new DepositConfig();
        final User user = UserManager.get(player);
        if (user.dArrow > 0) {
            if (DepositUtil.getAmount(player, Material.ARROW) < depositConfig.getArrowLimit()) {
                if (user.dArrow > depositConfig.getArrowLimit()) {
                    DepositUtil.giveItems(player, new ItemStack(Material.ARROW, depositConfig.getArrowLimit()));
                    user.dArrow -= depositConfig.getArrowLimit();
                } else {
                    DepositUtil.giveItems(player, new ItemStack(Material.ARROW, user.dArrow));
                    user.dArrow -= user.dArrow;
                }
                ChatUtil.error(player, withdrawMessage(Material.ARROW, depositConfig.arrowLimit));
            } else {
                ChatUtil.success(player, withdrawMessage(Material.ARROW, depositConfig.arrowLimit));
            }
        }else ChatUtil.error(player,noItemMessage(Material.ARROW));
    }

    public static void withdrawChorus(final Player player) {
        final DepositConfig depositConfig = new DepositConfig();
        final User user = UserManager.get(player);
        if (DepositUtil.getAmount(player, Material.CHORUS_FRUIT) == depositConfig.getEnchantedGoldenAppleLimit()) {
            ChatUtil.error(player, maxLimitMessage(Material.CHORUS_FRUIT));
            return;
        }
        if (user.dChorus > 0) {
            if (DepositUtil.getAmount(player, Material.CHORUS_FRUIT) < depositConfig.getChorusLimit()) {
                if (user.dChorus > depositConfig.getChorusLimit()) {
                    DepositUtil.giveItems(player, new ItemStack(Material.CHORUS_FRUIT, depositConfig.getChorusLimit()));
                    user.dChorus -= depositConfig.getChorusLimit();
                } else {
                    DepositUtil.giveItems(player, new ItemStack(Material.CHORUS_FRUIT, user.dChorus));
                    user.dChorus -= user.dChorus;
                }
                ChatUtil.success(player, withdrawMessage(Material.CHORUS_FRUIT, depositConfig.chorusLimit));

            } else ChatUtil.error(player, maxLimitMessage(Material.CHORUS_FRUIT));
        }else ChatUtil.error(player,noItemMessage(Material.CHORUS_FRUIT));
    }

    public static String withdrawMessage(final Material material, final int limit) {
        String item = material.toString().toLowerCase();
        return new MessageBuilder().setText("Wypłacono {ITEM} &8(x{AMOUNT})&7 ze schowka.")
                .addField("{ITEM}", item)
                .addField("{AMOUNT}", String.valueOf(limit))
                .build();
    }

    public static String noItemMessage(final Material material) {
        String item = material.toString().toLowerCase();
        return new MessageBuilder().setText("Nie posiadasz przedmiotu {ITEM} w schowku.").addField("{ITEM}", item).build();
    }

    public static String maxLimitMessage(final Material material) {
        String item = material.toString().toLowerCase();
        return new MessageBuilder().setText("Posiadasz już limit {ITEM} w ekwipunku.").addField("{ITEM}", item).build();
    }

    public static String takeMessage(final Material material, final int amount) {
        String item = material.toString().toLowerCase();
        return new MessageBuilder().setText("Zabrano nadmiar {ITEM} &8(x{AMOUNT}&8) &7do schowka.")
                .addField("{ITEM}", item)
                .addField("{AMOUNT}", String.valueOf(amount))
                .build();
    }

    public static void processMaterial(Player onlinePlayer, Material material, int limit, int userField) {
        if (DepositUtil.getAmount(onlinePlayer, material) > limit) {
            final int amount = DepositUtil.remove(onlinePlayer, material, limit);
            User user = UserManager.get(onlinePlayer);
            switch (userField) {
                case 1:
                    user.dEnchantedGoldenApple += amount;
                    break;
                case 2:
                    user.dGoldenApple += amount;
                    break;
                case 3:
                    user.dEnderPearl += amount;
                    break;
                case 4:
                    user.dArrow += amount;
                    break;
                case 5:
                    user.dTotemOfUndying += amount;
                    break;
                case 6:
                    user.dChorus += amount;
                    break;
            }
            ChatUtil.info(onlinePlayer, takeMessage(material, amount));
        }
    }
}
