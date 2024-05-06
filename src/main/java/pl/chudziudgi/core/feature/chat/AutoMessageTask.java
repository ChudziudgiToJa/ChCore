package pl.chudziudgi.core.feature.chat;

import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.List;

public class AutoMessageTask extends BukkitRunnable {

    private final ChatConfig config;
    private final List<String> messages;
    private int currentIndex;


    public AutoMessageTask(final ChCore plugin, ChatConfig config) {
        this.config = config;
        this.messages = config.getListaAutoMessage();
        this.currentIndex = 0;
        runTaskTimerAsynchronously(plugin, 20L * 60, 20L * 100);
    }

    @Override
    public void run() {
        if (!config.getChatAutoMessage()) return;

        if (messages.isEmpty()) {
            return;
        }
        String messageToSend = messages.get(currentIndex);
        ChatUtil.broadcast(messageToSend);
        currentIndex++;

        if (currentIndex >= messages.size()) {
            currentIndex = 0;
        }
    }
}
