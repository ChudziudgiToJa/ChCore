package pl.chudziudgi.core.feature.deposit;

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
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import panda.std.Option;
import pl.chudziudgi.core.ChCore;

import java.util.Objects;

public class DepositController implements Listener {

    private final ChCore plugin;

    public DepositController(ChCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();

        if (event.getItem().getType() == Material.ENCHANTED_GOLDEN_APPLE) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300 * 20, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300 * 20, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10 * 20, 4));
            player.setCooldown(Material.ENCHANTED_GOLDEN_APPLE, 300);
            return;
        }

        if (event.getItem().getType() == Material.GOLDEN_APPLE) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, 2));
            return;
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerUseBucket(PlayerBucketEmptyEvent event) {
        if (event.getBucket() == Material.PACKED_ICE) {
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
                    if (block.getType() == Material.PACKED_ICE) {
                        block.setType(Material.AIR);
                    }
                }
            }.runTaskLater(this.plugin, (60*2) * 20);
        }
    }
}