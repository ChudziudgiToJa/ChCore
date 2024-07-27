package pl.chudziudgi.core.feature.world;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.HashMap;
import java.util.Map;

public class AntiRedStoneController implements Listener {

    private final Map<Player, Long> cooldown = new HashMap<>();
    private final Map<Block, Integer> delayedBlocks = new HashMap<>();
    private final long time = 5000;


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (!(event.getClickedBlock().getType() == Material.LEVER)) return;
        if (cooldown.containsKey(player)) {
            long lastUseTime = cooldown.get(player);
            long timeElapsed = System.currentTimeMillis() - lastUseTime;
            if (timeElapsed < time) {
                event.setCancelled(true);
                long timeRemaining = time - timeElapsed;
                ChatUtil.error(player, "Musisz poczekać jeszcze " + (timeRemaining / 1000) + "s przed kolejnym użyciem dzwigni.");
                return;
            }
        }
        cooldown.put(player, System.currentTimeMillis());
    }

    @EventHandler
    public void onPlayerPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!(event.getBlock().getType() == Material.REDSTONE) || !(event.getBlock().getType() == Material.REDSTONE_TORCH))
            return;

        if (cooldown.containsKey(player)) {
            long lastUseTime = cooldown.get(player);
            long timeElapsed = System.currentTimeMillis() - lastUseTime;
            if (timeElapsed < time) {
                event.setCancelled(true);
                long timeRemaining = time - timeElapsed;
                ChatUtil.error(player, "Musisz poczekać jeszcze " + (timeRemaining / 1000) + "s przed kolejnym użyciem redstone.");
                event.setCancelled(true);
                return;
            }
        }
        cooldown.put(player, System.currentTimeMillis());
    }
}