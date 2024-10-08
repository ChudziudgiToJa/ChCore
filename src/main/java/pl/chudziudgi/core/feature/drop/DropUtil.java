package pl.chudziudgi.core.feature.drop;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.*;

public class DropUtil {
    public static int addFortuneEnchant(final int amount, final ItemStack tool) {
        Random random = new Random();
        int a = amount;
        if (random.nextDouble(0, 100) < 0.5 && tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 1) {
            ++a;
        } else if (random.nextDouble(0, 100) < 1.5 && tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 2) {
            a += 2;
        } else if (random.nextDouble(0, 100) < 2.0 && tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 3) {
            a += 3;
        }
        return a;
    }

    public static int isPermission(final Player player, final int amount) {
        Random random = new Random();
        int a = amount;
        if (random.nextDouble(0, 100) < 2.0 && player.hasPermission("core.drop.gold")) {
            ++a;
            return a;
        }
        if (random.nextDouble(0, 100) < 1.5 && player.hasPermission("core.drop.iron")) {
            a += 2;
            return a;
        }
        return a;
    }

    public static void addItemsToPlayer(final Player player, final List<ItemStack> items, final Block b) {
        final HashMap<Integer, ItemStack> notStored = player.getInventory().addItem(items.toArray(new ItemStack[0]));
        for (final Map.Entry<Integer, ItemStack> en : notStored.entrySet()) {
            b.getWorld().dropItemNaturally(b.getLocation(), en.getValue());
        }
    }

    public static void addItemsToPlayer(final Player player, final ItemStack itemStack, final Block b) {
        final HashMap<Integer, ItemStack> notStored = player.getInventory().addItem(new ItemStack(itemStack));
        for (final Map.Entry<Integer, ItemStack> en : notStored.entrySet()) {
            b.getWorld().dropItemNaturally(b.getLocation(), en.getValue());
        }
    }

    public static boolean isBreakableMaterialOverWorld(Material material) {
        return Arrays.asList(Material.STONE, Material.ANDESITE, Material.GRANITE, Material.DIORITE, Material.DEEPSLATE).contains(material);
    }


    public static void isMessage(List<ItemStack> drops, Player player, User user) {

        if (!user.dropMessage) return;
        if (drops.isEmpty()) return;

        StringJoiner itemNames = new StringJoiner(", ");
        for (ItemStack drop : drops) {
            String itemName = drop.getType().toString();
            itemNames.add(itemName);
        }
        String message = ChatUtil.fixColor(new MessageBuilder().setText("&8[&d&l!&8] &7Udało ci się wydobyć: &e{ITEM}")
                .addField("{ITEM}", itemNames.toString().toLowerCase())
                .build());
        ChatUtil.sendActionbar(player, message);
    }
}