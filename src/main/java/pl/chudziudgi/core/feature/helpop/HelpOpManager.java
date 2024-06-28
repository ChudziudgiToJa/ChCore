package pl.chudziudgi.core.feature.helpop;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class HelpOpManager {

    private final Cache<UUID, Long> timeCache;

    public HelpOpManager() {
        this.timeCache = CacheBuilder.newBuilder().expireAfterWrite(15000L, TimeUnit.SECONDS).build();
    }

    public Cache<UUID, Long> getTimeCache() {
        return timeCache;
    }
}
