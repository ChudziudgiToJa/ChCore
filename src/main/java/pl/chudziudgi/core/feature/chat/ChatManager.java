package pl.chudziudgi.core.feature.chat;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ChatManager {

    private final Cache<UUID, Long> chatCache;
    private final ChatConfig config;

    public ChatManager(ChatConfig config) {
        this.config = config;
        this.chatCache = CacheBuilder.newBuilder().expireAfterWrite(15000L, TimeUnit.SECONDS).build();
    }

    public void clearChat(CommandSender commandSender) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            for (int i = 1; i < 50; i++) {
                player.sendMessage("");
            }
            ChatUtil.success(player, "&6Chat zostal wyczyszczony przez: &c" + commandSender.getName());
        });
    }

    public void switchChat(CommandSender commandSender) {
        this.config.setChatMessageBlock(!this.config.getChatMessageBlock());
        Bukkit.getOnlinePlayers().forEach(player -> ChatUtil.success(player, "&6Chat zostal: " + (config.getChatMessageBlock() ? "&awlaczony" : "&cwylaczony") + " &6przez: &c" + commandSender.getName()));
    }

    public Cache<UUID, Long> getChatCache() {
        return chatCache;
    }

    public static void changeAutoMessageUserStatus(Player player) {
        User user = UserManager.get(player);
        user.chatAutoMessageStatus = !user.chatAutoMessageStatus;
        ChatUtil.success(player, "Automatyczne wiadomości na chacie: " + (user.chatAutoMessageStatus ? "&awłączone" : "&cwyłączone"));
        player.closeInventory();
    }
}