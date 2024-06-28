package pl.chudziudgi.core.feature.customitem.stonegenerator;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.combat.CombatManager;
import pl.chudziudgi.core.feature.customitem.CustomItemStack;
import pl.chudziudgi.core.feature.drop.DropUtil;
import pl.chudziudgi.core.util.ChatUtil;

public class StoneGeneratorController implements Listener {

    private final CombatManager combatManager;

    private final ChCore plugin;

    public StoneGeneratorController(CombatManager combatManager, ChCore plugin) {
        this.combatManager = combatManager;
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void giveBack(final BlockBreakEvent event) {
        Block block = event.getBlock();
        if (!isStoneGenerator(block)) return;
        event.setDropItems(false);
        DropUtil.addItemsToPlayer(event.getPlayer(), CustomItemStack.stoneGenerator(), block);
        ChatUtil.success(event.getPlayer(), "Pomyślnie zniszczono generator kamienia.");
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(final BlockBreakEvent event) {
        Block block = event.getBlock();
        Block blockUp = block.getLocation().add(0, 1, 0).getBlock();
        Block blockDown = block.getLocation().add(0, -1, 0).getBlock();

        if (!isStoneGenerator(blockDown)) return;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!plugin.getFunnyGuilds().getRegionManager().isInRegion(block.getLocation())) return;
                if (isNotValidBlockToChange(blockUp)) return;
                if (!isStoneGenerator(blockDown)) return;
                block.setType(getRandomStone());
            }
        }.runTaskLater(plugin, 40L);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace(final BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Block blockUp = block.getLocation().add(0, 1, 0).getBlock();

        if (!event.getItemInHand().isSimilar(CustomItemStack.stoneGenerator())) return;

        if (combatManager.inCombat(player)) {
            ChatUtil.error(player, "Nie możesz stawiać tego bloku podczas walki");
            event.setCancelled(true);
            return;
        }

        if (!this.plugin.getFunnyGuilds().getRegionManager().isInRegion(block.getLocation())) {
            ChatUtil.error(player, "Nie możesz stawiać tego bloku poza terenem klanu");
            event.setCancelled(true);
            return;
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!plugin.getFunnyGuilds().getRegionManager().isInRegion(block.getLocation())) return;
                        if (isNotValidBlockToChange(blockUp)) return;
                        if (!isStoneGenerator(block)) return;
                        blockUp.setType(getRandomStone());
                    }
                }.runTaskLater(plugin, 40L);
            }
        }.runTaskLater(plugin, 40L);

        ChatUtil.success(player, "Pomyślnie postawiono generator kamienia.");
    }

    private boolean isStoneGenerator(Block block) {
        return block.getType() == CustomItemStack.stoneGenerator().getType();
    }

    private boolean isNotValidBlockToChange(Block block) {
        Material type = block.getType();
        return type != Material.LAVA && type != Material.WATER && type != Material.AIR;
    }

    private Material getRandomStone() {
        Material[] stones = {Material.STONE, Material.ANDESITE, Material.GRANITE, Material.DIORITE, Material.DEEPSLATE};
        return stones[(int) (Math.random() * stones.length)];
    }
}
