package pl.chudziudgi.core.feature.nether;

import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.combat.CombatManager;
import pl.chudziudgi.core.feature.randomtp.RandomUtil;
import pl.chudziudgi.core.util.ChatUtil;

public class NetherController implements Listener {
    private final CombatManager combatManager;

    private final NetherConfig netherConfig;

    public NetherController(ChCore plugin, CombatManager combatManager, NetherConfig netherConfig) {
        this.combatManager = combatManager;
        this.netherConfig = netherConfig;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);
        if (this.combatManager.inCombat(player)) {
            ChatUtil.error(player, "Nie motego zrobić podczas walki.");
            return;
        }
        if (!this.netherConfig.isNetherStatus()) {
            ChatUtil.success(player, this.netherConfig.getNetherBlockPortal());
            return;
        }
        if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
            event.getPlayer().teleport(RandomUtil.getRandomCords(0));
            ChatUtil.success(player, "Teleportowano cina &a&nZiemie!");
            return;
        }
        if (player.getWorld().getEnvironment() == World.Environment.NORMAL) {
            event.getPlayer().teleport(RandomUtil.getRandomCords(1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 5000, 0));
            ChatUtil.success(player, "Teleportowano cido &c&nPiekła");
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (this.netherConfig.isNetherStatus()) return;
        if (!player.hasPermission("core.nether.admin")) return;
        ChatUtil.success(player, "");
        ChatUtil.success(player, " &8| &7Wylogowasipodczas eventu &cpiekła");
        ChatUtil.success(player, " &8| &7zostateleportowany na &aziemie!");
        ChatUtil.success(player, " &8| ");
        ChatUtil.success(player, " &8| &4&lEVENT PIEKŁO");
        ChatUtil.success(player, " &8| &7dostw godzinach &8(&715-17&8)");
        ChatUtil.success(player, " &8| ");
        ChatUtil.success(player, "");
        player.playSound(player.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 10.0F, 10.0F);
        player.teleport(RandomUtil.getRandomCords(0));
    }
}
