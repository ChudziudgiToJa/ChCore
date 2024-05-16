package pl.chudziudgi.core.feature.drop;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DropManager {

    private static final Random RANDOM = new Random();


    public static void breakBlock(Player player, Block block, ItemStack itemStack, BlockBreakEvent event) {
        if (player.getWorld().getEnvironment() == World.Environment.NETHER) onMine(player, block, itemStack, event);
        if (player.getWorld().getEnvironment() == World.Environment.NORMAL) onMine(player, block, itemStack, event);
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

    public static void onMine(Player player, Block block, ItemStack itemStack, BlockBreakEvent event) {
        if (!DropUtil.isBreakableMaterial(block.getType())) {
            return;
        }
        event.setDropItems(false);

        List<ItemStack> drops = new ArrayList<>();
        User user = UserManager.get(player);
        int experience = 1;

        for (Drop drop : user.enabledDrops) {
            World.Environment playerEnvironment = player.getWorld().getEnvironment();

            if ((playerEnvironment == World.Environment.NORMAL && drop.getWorldType() != World.Environment.NORMAL) || (playerEnvironment == World.Environment.NETHER && drop.getWorldType() != World.Environment.NETHER)) {
                continue;
            }


            ItemStack item = drop.getItemStack();
            int dropExperience = drop.getExp();
            double chance = drop.getChance();

            haveRank(player, chance);

            if (RANDOM.nextDouble() < chance) {
                if (itemStack.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) && drop.isFortune()) {
                    int amount = DropUtil.addFortuneEnchant(drop.getMinAmount() == drop.getMaxAmount() ? drop.getMinAmount() : RANDOM.nextInt(drop.getMinAmount(), drop.getMaxAmount()), itemStack);
                    item.setAmount(amount);
                    dropExperience *= amount;
                }
                drops.add(item);
                player.playSound(player, Sound.ENTITY_AXOLOTL_SPLASH, 0.5f, (float) (Math.random() * 20.0) / 10.0f);
                experience += dropExperience;
            }
        }

        if (user.dropOrginalBlock) {
            if (player.getWorld().getEnvironment() == World.Environment.NORMAL) {
                DropUtil.addItemsToPlayer(player, new ItemStack(Material.COBBLESTONE), block);
            } else {
                DropUtil.addItemsToPlayer(player, new ItemStack(Material.NETHERRACK), block);
            }
        }

        player.giveExp(experience);
        DropUtil.addItemsToPlayer(player, drops, block);
        DropUtil.isMessage(drops, player, user);
    }
}