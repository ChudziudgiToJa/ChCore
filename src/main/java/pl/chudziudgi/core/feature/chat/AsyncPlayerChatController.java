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

public class AsyncPlayerChatController implements Listener {

    private final ChatManager chatManager;
    private final ChatConfig config;

    public AsyncPlayerChatController(ChCore plugin, ChatManager chatManager, ChatConfig config) {
        this.chatManager = chatManager;
        this.config = config;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if (config.getChatMessageBlock()) {
            ChatUtil.error(player, "Chat jest wylaczony!");
            event.setCancelled(true);
            return;
        }

        final Cache<UUID, Long> chatCache = this.chatManager.getChatCache();
        if (!player.hasPermission("core.chat.slowmode") && chatCache.getIfPresent(player.getUniqueId()) != null && chatCache.getIfPresent(player.getUniqueId()) > System.currentTimeMillis()) {
            ChatUtil.error(player, "Nastepny raz mozesz napisac za: {TIME}".replace("{TIME}", DataUtils.durationToString(chatCache.getIfPresent(player.getUniqueId()))));
            event.setCancelled(true);
            return;
        }

        for (Player recipient : event.getRecipients()) {
            String message = event.getMessage();

            String chatFormat = config.getChatFormat().replace("{PLAYER}", player.getName());

            chatFormat = PlaceholderAPI.setPlaceholders(player, chatFormat);
            chatFormat = PlaceholderAPI.setRelationalPlaceholders(player, recipient, chatFormat);

            event.setFormat(ChatUtil.fixColor(chatFormat) + message);
            this.chatManager.getChatCache().put(player.getUniqueId(), System.currentTimeMillis() + TimeEnum.SECOND.getTime(5));
        }
    }
}