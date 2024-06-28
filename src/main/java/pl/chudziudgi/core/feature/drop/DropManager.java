package pl.chudziudgi.core.feature.drop;

import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DropManager {

    private static final Random RANDOM = new Random();

    public static void onMine(Player player, Block block, ItemStack itemStack, BlockBreakEvent event) {

        if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
            if (!DropUtil.isBreakableMaterialNether(block.getType())) return;
        }

        if (player.getWorld().getEnvironment() == World.Environment.NORMAL) {
            if (!DropUtil.isBreakableMaterialOverWorld(block.getType())) return;
        }

        List<ItemStack> drops = new ArrayList<>();
        User user = UserManager.get(player);
        user.minedStone ++;

        for (Drop drop : user.enabledOverWorldDrops) {
            World.Environment playerEnvironment = player.getWorld().getEnvironment();

            if ((playerEnvironment == World.Environment.NORMAL && drop.getWorldType() != World.Environment.NORMAL) || (playerEnvironment == World.Environment.NETHER && drop.getWorldType() != World.Environment.NETHER)) {
                continue;
            }


            ItemStack item = new ItemBuilder(drop.getMaterial()).build();
            int dropExperience = drop.getExp();
            double chance = drop.getChance();

            if (RANDOM.nextDouble() < chance) {
                if (itemStack.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) && drop.isFortune()) {
                    int amount = DropUtil.addFortuneEnchant(drop.getMinAmount() == drop.getMaxAmount() ? drop.getMinAmount() : RANDOM.nextInt(drop.getMinAmount(), drop.getMaxAmount()), itemStack);
                    amount = DropUtil.isPermission(player, amount);

                    item.setAmount(amount);
                }
                drops.add(item);
                player.giveExp(dropExperience);
                player.playSound(player, Sound.ENTITY_AXOLOTL_SPLASH, 0.5f, (float) (Math.random() * 20.0) / 10.0f);
            }
        }

        event.setDropItems(user.dropOriginalBlock);
        DropUtil.addItemsToPlayer(player, drops, block);
        DropUtil.isMessage(drops, player, user);
    }
}