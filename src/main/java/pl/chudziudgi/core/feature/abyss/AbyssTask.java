package pl.chudziudgi.core.feature.abyss;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.List;

public class AbyssTask extends BukkitRunnable {

    private int countdown;

    public AbyssTask(final ChCore plugin) {
        this.countdown = 60;
        runTaskTimerAsynchronously(plugin, 0, 300 * 20);
    }
    @Override
    public void run() {
        if (countdown == 60 || countdown == 30 || countdown == 10) {
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player, Sound.ENTITY_BAT_HURT, 5 ,5 ));
            Bukkit.getOnlinePlayers().forEach(player -> ChatUtil.info(player, "&8ⓚⓄⓈⓏ Przedmioty z ziemi zostaną usunięte za " + countdown + " sekund."));
        } else if (countdown == 0) {
            AbyssUtil.itemClear();
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player, Sound.ITEM_GOAT_HORN_SOUND_0, 5 ,5 ));
            Bukkit.getOnlinePlayers().forEach(player -> ChatUtil.info(player, "Przedmioty z ziemi zostały usunięte."));
            this.cancel();
        }
        countdown--;
    }
}