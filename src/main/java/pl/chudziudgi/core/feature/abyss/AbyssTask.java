package pl.chudziudgi.core.feature.abyss;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class AbyssTask extends BukkitRunnable {

    private int countdown;

    public AbyssTask(final ChCore plugin) {
        this.countdown = 60;
        this.runTaskTimerAsynchronously(plugin, 40, 400 * 20);
    }

    @Override
    public void run() {
        if (countdown == 60 || countdown == 30 || countdown == 10) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (UserManager.get(player).chatAbyssStatus) {
                    player.playSound(player, Sound.ENTITY_BAT_HURT, 5, 5);
                    ChatUtil.msg(player, "&3ⓚⓄⓈⓏ &7Przedmioty z ziemi zostaną usunięte za &3&n" + countdown + "&7 sekund.");
                }
            }
        } else if (countdown == 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (UserManager.get(player).chatAbyssStatus) {
                    player.playSound(player, Sound.ITEM_GOAT_HORN_SOUND_0, 5, 5);
                    ChatUtil.msg(player, "&3ⓚⓄⓈⓏ &7Przedmioty z ziemi zostały &3&nusunięte&7.");
                }
            }
            this.cancel();
        }
        countdown--;
    }
}