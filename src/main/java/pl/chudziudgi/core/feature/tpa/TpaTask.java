package pl.chudziudgi.core.feature.tpa;

import org.bukkit.scheduler.BukkitRunnable;
import pl.chudziudgi.core.ChCore;

import java.util.UUID;

public class TpaTask extends BukkitRunnable {

    private final TpaManager tpaManager;
    private final UUID senderId;

    public TpaTask(TpaManager tpaManager, UUID senderId) {
        this.tpaManager = tpaManager;
        this.senderId = senderId;
    }

    @Override
    public void run() {
        tpaManager.invalidateRequest(senderId);
    }
}