package pl.chudziudgi.core.feature.tpa;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.teleport.TeleportManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TpaManager {

    private final Cache<UUID, UUID> requestCache;

    public TpaManager() {
        this.requestCache = CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .build();
    }

    public void sendTpaRequest(UUID senderId, UUID receiverId, final ChCore plugin) {
        requestCache.put(senderId, receiverId);
        new TpaTask(this, senderId).runTaskLater(plugin, 30 * 20);
    }

    public UUID getTpaRequest(UUID senderId) {
        return requestCache.getIfPresent(senderId);
    }

    public void acceptTpaRequest(UUID receiverId, final ChCore plugin) {
        UUID senderId = requestCache.asMap().entrySet().stream()
                .filter(entry -> entry.getValue().equals(receiverId))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        if (senderId != null) {
            Player sender = Bukkit.getPlayer(senderId);
            Player receiver = Bukkit.getPlayer(receiverId);
            if (sender != null && receiver != null) {
                TeleportManager.startTeleportation(sender, receiver.getLocation(), plugin);
                requestCache.invalidate(senderId);
            }
        }
    }

    public void invalidateRequest(UUID senderId) {
        requestCache.invalidate(senderId);
    }

    public void acceptAllTpaRequests(UUID receiverId, final ChCore plugin) {
        List<UUID> senderIds = requestCache.asMap().entrySet().stream()
                .filter(entry -> entry.getValue().equals(receiverId))
                .map(Map.Entry::getKey)
                .toList();

        Player receiver = Bukkit.getPlayer(receiverId);

        if (receiver != null) {
            for (UUID senderId : senderIds) {
                Player sender = Bukkit.getPlayer(senderId);

                if (sender == null) {
                    ChatUtil.error(receiver, "Nie masz żadnej prośby o teleportację.");
                    receiver.playSound(receiver, Sound.ENTITY_VILLAGER_NO, 10,10);
                    return;
                }
                TeleportManager.startTeleportation(sender, receiver.getLocation(), plugin);
                ChatUtil.success(receiver, "Zaakceptowałeś wszystkie prośby o teleportację.");
                receiver.playSound(receiver, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10,10);
                requestCache.invalidate(senderId);
            }
        }
    }
}