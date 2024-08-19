package pl.chudziudgi.core.feature.chat;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.feature.user.User;
import pl.chudziudgi.core.feature.user.UserService;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ChatManager {

    @Getter
    private final Cache<UUID, Long> timeCache;
    @Getter
    private final Cache<UUID, String> messageCache;
    private final PluginConfiguration pluginConfiguration = ChCore.getInstance().getConfig();
    private static final UserService userService = ChCore.getInstance().getUserService();

    public ChatManager() {
        this.timeCache = CacheBuilder.newBuilder().expireAfterWrite(15000L, TimeUnit.SECONDS).build();
        this.messageCache = CacheBuilder.newBuilder().expireAfterWrite(15000L, TimeUnit.SECONDS).build();
    }


    public static void changeAutoMessageUserStatus(Player player) {
        User user = userService.findUserByNickName(player.getName());
        user.chatAutoMessageStatus = !user.chatAutoMessageStatus;
        ChatUtil.success(player, "Automatyczne wiadomości na chacie: " + (user.chatAutoMessageStatus ? "&awłączone" : "&cwyłączone"));
        player.closeInventory();
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
        this.pluginConfiguration.chatSettings.chatAutoMessage = !this.pluginConfiguration.chatSettings.chatAutoMessage;
        ChatUtil.success(commandSender, "Zmieniono status autowiadomości: " + (this.pluginConfiguration.chatSettings.chatAutoMessage ? "&awłączony" : "&cwłączony"));

    }

    public void switchChat(CommandSender commandSender) {
        this.pluginConfiguration.chatSettings.chatMessageBlock = !this.pluginConfiguration.chatSettings.chatMessageBlock;
        Bukkit.getOnlinePlayers().forEach(player -> ChatUtil.success(player, "Chat zostal: " + (this.pluginConfiguration.chatSettings.chatMessageBlock ? "&awlaczony" : "&cwylaczony") + " &7przez: &3" + commandSender.getName()));
    }

    public boolean canUseChat(Player player) {
        return !(userService.findUserByNickName(player.getName()).minedStone >= 100);
    }

}