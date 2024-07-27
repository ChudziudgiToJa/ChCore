package pl.chudziudgi.core.feature.chat;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.List;

public class AutoMessageTask extends BukkitRunnable {

    private final PluginConfiguration config;
    private final List<String> messages;
    private int currentIndex;


    public AutoMessageTask(final ChCore plugin, PluginConfiguration config) {
        this.messages = config.chatSettings.listAutoMessage;
        this.config = config;
        this.currentIndex = 0;
        runTaskTimerAsynchronously(plugin, 0, 20L * 650L);
    }

    @Override
    public void run() {
        if (!config.chatSettings.chatAutoMessage) return;
        if (messages.isEmpty()) return;

        Bukkit.getOnlinePlayers().forEach(player -> {
            User user = UserManager.get(player);
            if (user == null) return;
            if (user.chatAutoMessageStatus) {
                player.playSound(player, Sound.ENTITY_COD_FLOP, 5, 5);
                ChatUtil.msg(player, "&3ⒾⓃⒻⓄ &7" + messages.get(currentIndex));
            }
        });
        currentIndex++;
        if (currentIndex >= messages.size()) {
            currentIndex = 0;
        }
    }
}
