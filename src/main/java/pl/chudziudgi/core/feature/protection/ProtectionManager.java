package pl.chudziudgi.core.feature.protection;

import com.google.common.collect.Maps;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.TimeEnum;

import java.util.Map;
import java.util.UUID;

public class ProtectionManager {

    private final Map<UUID, Long> protectionCache;

    public ProtectionManager() {
        this.protectionCache = Maps.newLinkedHashMap();
    }

    public void giveProtection(final Player player, TimeEnum timeEnum, int time) {
        this.protectionCache.put(player.getUniqueId(), System.currentTimeMillis() + timeEnum.getTime(time));
    }

    public boolean hasProtection(final OfflinePlayer player) {
        if (this.protectionCache.get(player.getUniqueId()) != null) {
            return this.protectionCache.get(player.getUniqueId()) > System.currentTimeMillis();
        }
        return false;
    }

    public void endProtection(final Player player) {
        this.protectionCache.remove(player.getUniqueId());
        ChatUtil.sendActionbar(player, "&6⌚ Ochrona &edobiegla konca");
    }

    public Map<UUID, Long> getProtectionCache() {
        return this.protectionCache;
    }

}