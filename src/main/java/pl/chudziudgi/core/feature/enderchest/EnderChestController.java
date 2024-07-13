package pl.chudziudgi.core.feature.enderchest;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.chudziudgi.core.ChCore;

import java.util.Objects;

public class EnderChestController implements Listener {

    public EnderChestController(ChCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onOpenEnderChest(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Material material = Objects.requireNonNull(event.getClickedBlock()).getType();

        if (!(material == Material.ENDER_CHEST)) return;
        event.setCancelled(true);
        EnderChestGui.openkit(player, player);
    }
}
