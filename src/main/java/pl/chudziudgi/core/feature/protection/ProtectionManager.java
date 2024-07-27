package pl.chudziudgi.core.feature.protection;

import com.google.common.collect.Maps;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.config.PluginConfiguration;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.TimeEnum;

import java.util.Map;
import java.util.UUID;

public class ProtectionManager {

    private final PluginConfiguration config;


    private final Map<UUID, Long> protectionCache;

    public ProtectionManager(PluginConfiguration config) {
        this.config = config;
        this.protectionCache = Maps.newLinkedHashMap();
    }

    public void giveProtection(final Player player, TimeEnum timeEnum, int time) {
        this.protectionCache.put(player.getUniqueId(), System.currentTimeMillis() + timeEnum.getTime(time));
    }

    public void giveProtection(final Player player, long time) {
        this.protectionCache.put(player.getUniqueId(), time);
    }

    public boolean hasProtection(final OfflinePlayer player) {
        if (this.protectionCache.get(player.getUniqueId()) != null) {
            return this.protectionCache.get(player.getUniqueId()) > System.currentTimeMillis();
        }
        return false;
    }

    public void endProtection(final Player player) {
        this.protectionCache.remove(player.getUniqueId());
        ChatUtil.sendActionbar(player, this.config.protectionSettings.PROTECTION_END_MESSAGE);
    }

    public Map<UUID, Long> getProtectionCache() {
        return this.protectionCache;
    }
}