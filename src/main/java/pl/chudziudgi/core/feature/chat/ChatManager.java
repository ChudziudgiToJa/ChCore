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

    private final Cache<UUID, Long> timeCache;
    private final Cache<UUID, String> messageCache;
    private final ChatConfig config;

    public ChatManager(ChatConfig config) {
        this.config = config;
        this.timeCache = CacheBuilder.newBuilder().expireAfterWrite(15000L, TimeUnit.SECONDS).build();
        this.messageCache = CacheBuilder.newBuilder().expireAfterWrite(15000L, TimeUnit.SECONDS).build();
    }

    public static void changeAutoMessageUserStatus(Player player) {
        User user = UserManager.get(player);
        user.chatAutoMessageStatus = !user.chatAutoMessageStatus;
        ChatUtil.success(player, "Automatyczne wiadomości na chacie: " + (user.chatAutoMessageStatus ? "&awłączone" : "&cwyłączone"));
        player.closeInventory();
    }

    public Cache<UUID, String> getMessageCache() {
        return messageCache;
    }

    public void clearChat(CommandSender commandSender) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            for (int i = 1; i < 50; i++) {
                player.sendMessage("");
            }
            ChatUtil.success(player, "&7Chat zostal wyczyszczony przez: &3" + commandSender.getName());
        });
    }

    public void switchAuto(CommandSender commandSender) {
        config.setChatAutoMessage(!config.getChatAutoMessage());
        ChatUtil.success(commandSender, "Zmieniono status autowiadomości: " + (config.getChatAutoMessage() ? "&awłączony" : "&cwłączony"));

    }

    public void switchChat(CommandSender commandSender) {
        this.config.setChatMessageBlock(!this.config.getChatMessageBlock());
        Bukkit.getOnlinePlayers().forEach(player -> ChatUtil.success(player, "Chat zostal: " + (config.getChatMessageBlock() ? "&awlaczony" : "&cwylaczony") + " &7przez: &3" + commandSender.getName()));
    }

    public boolean canUseChat(Player player) {
        return !(UserManager.get(player).minedStone >= 100);
    }

    public Cache<UUID, Long> getTimeCache() {
        return timeCache;
    }
}