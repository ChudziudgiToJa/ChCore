package pl.chudziudgi.core.feature.customitem.magiccandle;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.combat.CombatManager;
import pl.chudziudgi.core.feature.customitem.CustomItemStack;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MagicCandleController implements Listener {

    private final ChCore plugin;
    private final CombatManager combatManager;
    private final Map<Player, Boolean> playerInOpening = new HashMap<>();

    public MagicCandleController(ChCore plugin, CombatManager combatManager) {
        this.plugin = plugin;
        this.combatManager = combatManager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        ItemStack candle = CustomItemStack.candle();
        ItemStack itemInHand = event.getItem();
        Player player = event.getPlayer();

        if (itemInHand == null) return;

        if (!itemInHand.isSimilar(candle)) return;

        Action action = event.getAction();
        if (action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR) {

        event.setCancelled(true);

        if (combatManager.inCombat(player)) {
            ChatUtil.error(player, "Nie możesz wykonywać tej czynności podczas walki");
            return;
        }

        if (playerInOpening.getOrDefault(player, false)) return;
        playerInOpening.put(player, true);
        candle.setAmount(1);
        player.getInventory().removeItem(candle);

        new BukkitRunnable() {
            final Random random = new Random();
            int counter = 0;

            @Override
            public void run() {
                if (counter >= 10) {
                    ItemStack randomItem = MagicCandleDrop.dropList.get(random.nextInt(MagicCandleDrop.dropList.size()));
                    player.getInventory().addItem(randomItem);
                    player.playSound(player, Sound.ITEM_GOAT_HORN_SOUND_0, 10, 10);
                    playerInOpening.put(player, false);
                    cancel();
                    return;
                }

                ItemStack randomItem = MagicCandleDrop.dropList.get(random.nextInt(MagicCandleDrop.dropList.size()));
                ChatUtil.sendTitle(player, "&bʟᴏꜱᴜᴊᴇ", randomItem.getItemMeta().getDisplayName(), 0, 20, 0);
                player.playSound(player, Sound.BLOCK_BONE_BLOCK_STEP, 10, 10);
                counter++;
            }
        }.runTaskTimer(plugin, 0, 10);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        playerInOpening.remove(player);
    }
}