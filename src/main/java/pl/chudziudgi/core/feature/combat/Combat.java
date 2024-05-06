package pl.chudziudgi.core.feature.combat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Combat {

    private final UUID identifier;
    private long leftTime;

    public Combat(final UUID identifier, final long time){
        this.identifier = identifier;
        this.leftTime = time;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public long getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(long leftTime) {
        this.leftTime = leftTime;
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(this.identifier);
    }
}
