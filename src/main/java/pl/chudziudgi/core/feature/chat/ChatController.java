package pl.chudziudgi.core.feature.chat;

import com.google.common.cache.Cache;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.DataUtils;
import pl.chudziudgi.core.util.TimeEnum;

import java.util.UUID;

public class ChatController implements Listener {

    private final ChatManager chatManager;

    private final PluginConfiguration config;

    public ChatController(ChatManager chatManager, PluginConfiguration config) {
        this.chatManager = chatManager;
        this.config = config;
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        String message = event.getMessage();

        if (!player.hasPermission("core.chat.admin") && chatManager.canUseChat(player)) {
            ChatUtil.error(player, "Nie odblokowałeś jeszcze chatu! &8(wykop 100 kamienia) &8pozostało " + (100 - UserManager.get(player).minedStone));
            event.setCancelled(true);
            return;
        }

        if (!player.hasPermission("core.chat.admin") && config.chatSettings.chatMessageBlock) {
            ChatUtil.error(player, "Chat jest wylaczony!");
            event.setCancelled(true);
            return;
        }

        for (char c : message.toCharArray()) {
            if (!this.config.chatSettings.listaDozwolonychZnakow .contains(String.valueOf(c))) {
                ChatUtil.error(player, "Wiadomość zawiera niedozwolone znaki!");
                event.setCancelled(true);
                return;
            }
        }

        final Cache<UUID, String> messageCache = this.chatManager.getMessageCache();
        String lastMessage = messageCache.getIfPresent(player.getUniqueId());
        if (!player.hasPermission("core.chat.sameMessage") && message.equals(lastMessage)) {
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

            String chatFormat = config.chatSettings.chatFormat .replace("{PLAYER}", player.getName());

            chatFormat = PlaceholderAPI.setPlaceholders(player, chatFormat);
            chatFormat = PlaceholderAPI.setRelationalPlaceholders(player, recipient, chatFormat);

            event.setFormat(ChatUtil.fixColor(chatFormat) + message);

            this.chatManager.getTimeCache().put(player.getUniqueId(), System.currentTimeMillis() + TimeEnum.SECOND.getTime(5));
            this.chatManager.getMessageCache().put(player.getUniqueId(), message);
        }
    }
}