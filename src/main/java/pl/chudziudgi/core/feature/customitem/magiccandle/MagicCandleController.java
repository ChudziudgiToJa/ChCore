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
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.feature.combat.CombatManager;
import pl.chudziudgi.core.feature.customitem.CustomItemStack;
import pl.chudziudgi.core.feature.deposit.DepositUtil;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MagicCandleController implements Listener {

    private final ChCore plugin;
    private final CombatManager combatManager;
    private final MagicCandleManager magicCandleManager;
    private final PluginConfiguration config;
    private final Map<Player, Boolean> playerInOpening = new HashMap<>();

    public MagicCandleController(ChCore plugin, CombatManager combatManager, MagicCandleManager magicCandleManager, PluginConfiguration config) {
        this.plugin = plugin;
        this.combatManager = combatManager;
        this.magicCandleManager = magicCandleManager;
        this.config = config;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        final Random random = new Random();
        ItemStack candle = CustomItemStack.magicCandle();
        ItemStack itemInHand = event.getItem();
        Player player = event.getPlayer();

        if (itemInHand == null) return;

        if (!itemInHand.isSimilar(candle)) return;
        event.setCancelled(true);

        if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) return;


        if (combatManager.inCombat(player)) {
            ChatUtil.error(player, "Nie możesz wykonywać tej czynności podczas walki");
            return;
        }

        if (playerInOpening.getOrDefault(player, false)) return;

        playerInOpening.put(player, true);
        candle.setAmount(1);
        player.getInventory().removeItem(candle);

        new BukkitRunnable() {
            int counter = 0;

            @Override
            public void run() {
                if (counter >= 10) {
                    ItemStack randomItem =  config.magicCandleSettings.dropList .get(random.nextInt(config.magicCandleSettings.dropList.size()));

                    DepositUtil.giveItems(player, randomItem);
                    player.playSound(player, Sound.ITEM_GOAT_HORN_SOUND_0, 10, 10);
                    playerInOpening.put(player, false);
                    ChatUtil.sendTitle(player, "&aᴡʏʟᴏꜱᴏᴡᴀɴᴏ &710/10", "&7" + getName(randomItem), 30, 30, 30);
                    magicCandleManager.brodCast(player, getName(randomItem));
                    cancel();
                    return;
                }
                ItemStack randomItem = config.magicCandleSettings.dropList.get(random.nextInt(config.magicCandleSettings.dropList.size()));
                ChatUtil.sendTitle(player, "&bʟᴏꜱᴜᴊᴇ &7"+ counter +" /10", "&7" + getName(randomItem), 0, 20, 0);
                player.playSound(player, Sound.BLOCK_BONE_BLOCK_STEP, 10, 10);
                counter++;
            }
        }.runTaskTimer(plugin, 0, 8);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        playerInOpening.remove(player);
    }


    public String getName(ItemStack randomItem) {
        if (randomItem.hasItemMeta() && randomItem.getItemMeta().hasDisplayName()) {
            return randomItem.getItemMeta().getDisplayName();
        } else {
            return randomItem.getType().toString().replace('_', ' ').toLowerCase();
        }
    }
}