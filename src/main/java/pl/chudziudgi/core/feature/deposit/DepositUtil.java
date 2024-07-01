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

    public static void withdrawItem(final Player player, final Material material, int userField) {
        final User user = UserManager.get(player);
        final int limit = getLimit(material);
        final int userAmount = getUserAmount(user, material);

        if (DepositUtil.getAmount(player, material) == limit) {
            ChatUtil.error(player, maxLimitMessage(material));
            return;
        }

        if (userAmount > 0) {
            int availableSpace = limit - DepositUtil.getAmount(player, material);
            int withdrawAmount = Math.min(userAmount, availableSpace);

            if (withdrawAmount > 0) {
                DepositUtil.giveItems(player, new ItemStack(material, withdrawAmount));
                setUserAmount(user, material, userAmount - withdrawAmount);
                ChatUtil.success(player, withdrawMessage(material, withdrawAmount));
                DepositGui.open(player);
            } else {
                ChatUtil.error(player, maxLimitMessage(material));
            }
        } else {
            ChatUtil.error(player, noItemMessage(material));
        }
    }

    private static int getLimit(Material material) {
        DepositConfig depositConfig = new DepositConfig();
        return switch (material) {
            case ENCHANTED_GOLDEN_APPLE -> depositConfig.getEnchantedGoldenAppleLimit();
            case GOLDEN_APPLE -> depositConfig.getGoldenAppleLimit();
            case ENDER_PEARL -> depositConfig.getEnderPearlLimit();
            case TOTEM_OF_UNDYING -> depositConfig.getTotemOfUndyingLimit();
            case ARROW -> depositConfig.getArrowLimit();
            case CHORUS_FRUIT -> depositConfig.getChorusLimit();
            default -> throw new IllegalArgumentException("Unsupported material: " + material);
        };
    }

    private static int getUserAmount(User user, Material material) {
        return switch (material) {
            case ENCHANTED_GOLDEN_APPLE -> user.dEnchantedGoldenApple;
            case GOLDEN_APPLE -> user.dGoldenApple;
            case ENDER_PEARL -> user.dEnderPearl;
            case TOTEM_OF_UNDYING -> user.dTotemOfUndying;
            case ARROW -> user.dArrow;
            case CHORUS_FRUIT -> user.dChorus;
            default -> throw new IllegalArgumentException("Unsupported material: " + material);
        };
    }

    private static void setUserAmount(User user, Material material, int amount) {
        switch (material) {
            case ENCHANTED_GOLDEN_APPLE:
                user.dEnchantedGoldenApple = amount;
                break;
            case GOLDEN_APPLE:
                user.dGoldenApple = amount;
                break;
            case ENDER_PEARL:
                user.dEnderPearl = amount;
                break;
            case TOTEM_OF_UNDYING:
                user.dTotemOfUndying = amount;
                break;
            case ARROW:
                user.dArrow = amount;
                break;
            case CHORUS_FRUIT:
                user.dChorus = amount;
                break;
            default:
                throw new IllegalArgumentException("Unsupported material: " + material);
        }
    }

    public static String withdrawMessage(final Material material, final int amount) {
        String item = material.toString().toLowerCase();
        return new MessageBuilder().setText("Wypłacono {ITEM} &8(x{AMOUNT})&7 ze schowka.")
                .addField("{ITEM}", item)
                .addField("{AMOUNT}", String.valueOf(amount))
                .build();
    }

    public static String noItemMessage(final Material material) {
        String item = material.toString().toLowerCase();
        return new MessageBuilder().setText("Nie posiadasz przedmiotu {ITEM} w schowku.")
                .addField("{ITEM}", item)
                .build();
    }

    public static String maxLimitMessage(final Material material) {
        String item = material.toString().toLowerCase();
        return new MessageBuilder().setText("Posiadasz już limit {ITEM} w ekwipunku.")
                .addField("{ITEM}", item)
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

    public static String takeMessage(final Material material, final int amount) {
        String item = material.toString().toLowerCase();
        return new MessageBuilder().setText("Zabrano nadmiar {ITEM} &8(x{AMOUNT}&8) &7do schowka.")
                .addField("{ITEM}", item)
                .addField("{AMOUNT}", String.valueOf(amount))
                .build();
    }
}