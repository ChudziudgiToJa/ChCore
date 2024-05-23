package pl.chudziudgi.core.feature.chat;

import com.google.common.cache.Cache;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.DataUtils;
import pl.chudziudgi.core.util.TimeEnum;

import java.util.UUID;

public class ChatController implements Listener {

    private final ChatManager chatManager;
    private final ChatConfig config;

    public ChatController(ChCore plugin, ChatManager chatManager, ChatConfig config) {
        this.chatManager = chatManager;
        this.config = config;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        String message = event.getMessage();

        if (config.getChatMessageBlock()) {
            ChatUtil.error(player, "Chat jest wylaczony!");
            event.setCancelled(true);
            return;
        }

        for (char c : message.toCharArray()) {
            if (!this.config.getListaDozwolonychZnakow().contains(String.valueOf(c))) {
                ChatUtil.error(player, "Wiadomość zawiera niedozwolone znaki!");
                event.setCancelled(true);
                return;
            }
        }

        final Cache<UUID, String> messageCache = this.chatManager.getMessageCache();
        String lastMessage = messageCache.getIfPresent(player.getUniqueId());
        if (!player.hasPermission("core.chat.slowmode") && message.equals(lastMessage)) {
            ChatUtil.error(player, "Wiadomości nie mogą sie powtarzać");
            event.setCancelled(true);
            return;
        }


        final Cache<UUID, Long> timeCache = this.chatManager.getTimeCache();
        if (!player.hasPermission("core.chat.slowmode") && timeCache.getIfPresent(player.getUniqueId()) != null && timeCache.getIfPresent(player.getUniqueId()) > System.currentTimeMillis()) {
            ChatUtil.error(player, "Nastepny raz mozesz napisac za: {TIME}".replace("{TIME}", DataUtils.durationToString(timeCache.getIfPresent(player.getUniqueId()))));
            event.setCancelled(true);
            return;
        }

        for (Player recipient : event.getRecipients()) {

            String chatFormat = config.getChatFormat().replace("{PLAYER}", player.getName());

            chatFormat = PlaceholderAPI.setPlaceholders(player, chatFormat);
            chatFormat = PlaceholderAPI.setRelationalPlaceholders(player, recipient, chatFormat);

            event.setFormat(ChatUtil.fixColor(chatFormat) + message);

            this.chatManager.getTimeCache().put(player.getUniqueId(), System.currentTimeMillis() + TimeEnum.SECOND.getTime(5));
            this.chatManager.getMessageCache().put(player.getUniqueId(), message);
        }
    }
}