package pl.chudziudgi.core.feature.enderchest;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.chudziudgi.core.ChCore;

import java.util.Objects;

public class EnderChestController implements Listener {

    public EnderChestController(ChCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onOpenEnderChest(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.ENDER_CHEST) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            EnderChestGui.openkit(player, player);
        }
    }
}
