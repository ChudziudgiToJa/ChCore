package pl.chudziudgi.core.feature.guild.permission;

import net.dzikoysk.funnyguilds.event.guild.GuildCreateEvent;
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberDeputyEvent;
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberJoinEvent;
import net.dzikoysk.funnyguilds.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import panda.std.Option;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class PermissionController implements Listener {

    private final ChCore plugin;

    public PermissionController(final ChCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("core.perission.admin")) return;

        pl.chudziudgi.core.database.user.User user = UserManager.get(event.getPlayer());

        if (!this.plugin.getFunnyGuilds().getRegionManager().isInRegion(event.getBlock().getLocation())) return;

        if (!user.placeBlock) {
            ChatUtil.error(player, "Nie posiadasz uprawnienia. &8(&3Stawianie bloków&8)");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("core.perission.admin")) return;

        pl.chudziudgi.core.database.user.User user = UserManager.get(event.getPlayer());
        if (!this.plugin.getFunnyGuilds().getRegionManager().isInRegion(event.getBlock().getLocation())) return;
        if (!user.breakBlock) {
            ChatUtil.error(player, "Nie posiadasz uprawnienia. &8(&3Niszczenie bloków&8)");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("core.permission.admin")) {
            return;
        }

        pl.chudziudgi.core.database.user.User user = UserManager.get(player);

        if (event.getClickedBlock() != null) {
            Material clickedBlock = event.getClickedBlock().getType();

            if (!this.plugin.getFunnyGuilds().getRegionManager().isInRegion(event.getClickedBlock().getLocation())) {
                return;
            }

            if (!user.useBlock && (clickedBlock == Material.HOPPER
                    || clickedBlock == Material.CHEST
                    || clickedBlock == Material.TRAPPED_CHEST
                    || clickedBlock == Material.FURNACE
                    || clickedBlock == Material.SMOKER
                    || clickedBlock == Material.BLAST_FURNACE
                    || clickedBlock == Material.BREWING_STAND)) {

                ChatUtil.error(player, "Nie posiadasz uprawnienia. &8(&3Interakcja&8)");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onGuildCreate(GuildCreateEvent event) {
        Option<User> doerOption = event.getDoer();
        if (doerOption.isPresent()) {
            Player player = Bukkit.getPlayer(event.getDoer().get().getUUID());
            pl.chudziudgi.core.database.user.User user = UserManager.get(player);
            user.placeBlock = true;
            user.breakBlock = true;
            user.useBlock = true;
        }
    }

    @EventHandler
    public void onGuildMemberDeputy(GuildMemberDeputyEvent event) {
        Player player = Bukkit.getPlayer(event.getMember().getUUID());
        pl.chudziudgi.core.database.user.User user = UserManager.get(player);
        user.placeBlock = true;
        user.breakBlock = true;
        user.useBlock = true;
    }

    @EventHandler
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Player player = Bukkit.getPlayer(event.getMember().getUUID());
        pl.chudziudgi.core.database.user.User user = UserManager.get(player);
        user.placeBlock = false;
        user.breakBlock = false;
        user.useBlock = false;
    }
}
