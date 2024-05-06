package pl.chudziudgi.core.feature.drop;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.database.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DropManager {
    private static final List<Drop> drops = new ArrayList<>();

    private static final Random RANDOM = new Random();

    public static List<Drop> getDrops() {
        return drops;
    }


    public static void breakBlock(Player player, Block block, ItemStack itemStack, BlockBreakEvent event) {
        if (player.getWorld().getEnvironment() == World.Environment.NETHER) onNether(player, block, itemStack, event);
        if (player.getWorld().getEnvironment() == World.Environment.NORMAL)
            onOverWorld(player, block, itemStack, event);
    }

    public static double haveRank(final Player player, double chance) {
        if (player.hasPermission("core.drop.iron")) {
            chance = chance * 0.05;
            return chance;
        }
        if (player.hasPermission("core.drop.gold")) {
            chance = chance * 0.10;
            return chance;
        }
        return chance;
    }

    public static void onOverWorld(Player player, Block block, ItemStack itemStack, BlockBreakEvent event) {
        if (!DropUtill.isBreakableMaterialOverWorld(block.getType())) {
            return;
        }
        event.setDropItems(false);

        List<ItemStack> drops = new ArrayList<>();
        User user = UserManager.getUser(player);
        int experience = 3;

        for (Drop drop : user.enabledDrops) {
            ItemStack item = drop.getItemStack();
            int dropExperience = drop.getExp();
            double chance = drop.getChance();
            chance = haveRank(player, chance);

            if (RANDOM.nextDouble() < chance) {
                if (itemStack.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) && drop.isFortune()) {
                    int amount = DropUtill.addFortuneEnchant(drop.getMinAmount() == drop.getMaxAmount() ? drop.getMinAmount() : RANDOM.nextInt(drop.getMinAmount(), drop.getMaxAmount()), itemStack);
                    item.setAmount(amount);
                    dropExperience *= amount;
                }
                drops.add(item);
                player.playSound(player, Sound.ENTITY_AXOLOTL_SPLASH, 0.5f, (float) (Math.random() * 20.0) / 10.0f);
                experience += dropExperience;
            }
        }

        if (user.dropCobbleStone) {
            Material material = itemStack.containsEnchantment(Enchantment.SILK_TOUCH) ? Material.STONE : Material.COBBLESTONE;
            DropUtill.addItemsToPlayer(player, new ItemStack(material), block);
        }

        player.giveExp(experience);
        DropUtill.addItemsToPlayer(player, drops, block);
        DropUtill.isMessage(drops, player, user);
    }

    public static void onNether(Player player, Block block, ItemStack itemStack, BlockBreakEvent event) {
        if (!DropUtill.isBreakableMaterialNether(block.getType())) {
            return;
        }
        event.setDropItems(false);

        List<ItemStack> drops = new ArrayList<>();
        User user = UserManager.getUser(player);
        int experience = 1;

        for (Drop drop : user.enabledNetherDrops) {
            ItemStack item = drop.getItemStack();
            int dropExperience = drop.getExp();
            double chance = drop.getChance();

            haveRank(player, chance);

            if (RANDOM.nextDouble() < chance) {
                if (itemStack.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) && drop.isFortune()) {
                    int amount = DropUtill.addFortuneEnchant(drop.getMinAmount() == drop.getMaxAmount() ? drop.getMinAmount() : RANDOM.nextInt(drop.getMinAmount(), drop.getMaxAmount()), itemStack);
                    item.setAmount(amount);
                    dropExperience *= amount;
                }
                drops.add(item);
                player.playSound(player, Sound.ENTITY_AXOLOTL_SPLASH, 0.5f, (float) (Math.random() * 20.0) / 10.0f);
                experience += dropExperience;
            }
        }

        if (user.dropNetherrack) {
            DropUtill.addItemsToPlayer(player, new ItemStack(Material.NETHERRACK), block);
        }

        player.giveExp(experience);
        DropUtill.addItemsToPlayer(player, drops, block);
        DropUtill.isMessage(drops, player, user);
    }
}