package pl.chudziudgi.core.feature.deposit;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.chudziudgi.core.ChCore;

public class DepositController implements Listener {
    public DepositController(ChCore plugin) {
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

        if (event.getItem().getType() == Material.CHORUS_FRUIT) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 5));
            return;
        }
    }
}
