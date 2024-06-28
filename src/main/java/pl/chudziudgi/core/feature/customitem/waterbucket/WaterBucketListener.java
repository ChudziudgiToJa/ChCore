package pl.chudziudgi.core.feature.customitem.waterbucket;

import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.guild.Guild;
import net.dzikoysk.funnyguilds.guild.Region;
import net.dzikoysk.funnyguilds.guild.RegionManager;
import net.dzikoysk.funnyguilds.user.User;
import net.dzikoysk.funnyguilds.user.UserManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.scheduler.BukkitRunnable;
import panda.std.Option;
import pl.chudziudgi.core.ChCore;

public class WaterBucketListener implements Listener {

    private final ChCore plugin;

    public WaterBucketListener(ChCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerUseBucket(PlayerBucketEmptyEvent event) {
        if (event.getBucket() == Material.WATER_BUCKET) {
            Block block = event.getBlockClicked().getRelative(event.getBlockFace());
            Player player = event.getPlayer();

            FunnyGuilds funnyGuilds = this.plugin.getFunnyGuilds();
            UserManager userManager = funnyGuilds.getUserManager();
            Option<User> userOption = userManager.findByPlayer(player);

            if (!userOption.isPresent()) return;
            User user = userOption.get();

            RegionManager regionManager = funnyGuilds.getRegionManager();
            Option<Region> regionOption = regionManager.findRegionAtLocation(block.getLocation());


            if (regionOption.isPresent()) {
                Region region = regionOption.get();
                Guild guild = region.getGuild();
                if (guild.getMembers().equals(user)) return;
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (block.getType() == Material.WATER) {
                        block.setType(Material.AIR);
                    }
                }
            }.runTaskLater(this.plugin, 60 * 20);
        }
    }
}