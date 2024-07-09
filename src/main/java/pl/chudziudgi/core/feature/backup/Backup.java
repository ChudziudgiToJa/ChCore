package pl.chudziudgi.core.feature.backup;

import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.util.ItemStackUtil;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Backup implements Serializable {

    private final User user;
    private final String itemStack;
    private final int lvl;
    private final float exp;
    private final Instant instant;

    public Backup(User user, String itemStack, int lvl, float exp, Instant instant) {
        this.user = user;
        this.itemStack = itemStack;
        this.lvl = lvl;
        this.exp = exp;
        this.instant = instant;
    }

    public float getExp() {
        return exp;
    }

    public User getUser() {
        return user;
    }

    public ItemStack[] getItemStackList() {
        return ItemStackUtil.read(itemStack);
    }

    public int getLvl() {
        return lvl;
    }

    public Instant getInstant() {
        return instant;
    }

    public String getInstantFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
