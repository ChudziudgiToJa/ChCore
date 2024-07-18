package pl.chudziudgi.core.feature.abyss;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class AbyssTask extends BukkitRunnable {

    private int countdown;

    public AbyssTask(final ChCore plugin) {
        this.countdown = 60;
        this.runTaskTimerAsynchronously(plugin, 0, 400 * 20);
    }

    @Override
    public void run() {
        if (countdown == 60 || countdown == 30 || countdown == 10) {
            notifyPlayers(countdown, Sound.ENTITY_BAT_HURT, "&3ⓚⓄⓈⓏ &7Przedmioty z ziemi zostaną usunięte za &3&n" + countdown + "&7 sekund.");
        } else if (countdown == 0) {
            notifyPlayers(countdown, Sound.ITEM_GOAT_HORN_SOUND_0, "&3ⓚⓄⓈⓏ &7Przedmioty z ziemi zostały &3&nusunięte&7.");
            this.cancel();
        }
        countdown--;
    }

    private void notifyPlayers(int countdown, Sound sound, String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            User user = UserManager.get(player);
            if (user == null) return;
            if (!user.chatAbyssStatus) return;
            player.playSound(player.getLocation(), sound, 5, 5);
            ChatUtil.msg(player, message);
        }
    }
}