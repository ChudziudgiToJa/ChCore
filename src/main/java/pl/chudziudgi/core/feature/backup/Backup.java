package pl.chudziudgi.core.feature.backup;

import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.util.ItemStackUtil;

import java.io.Serializable;
import java.time.Instant;

public class Backup implements Serializable {

    private final User user;
    private final String itemStack;
    private final int lvl;
    private final Instant instant;

    public Backup(User user, String itemStack, int lvl, Instant instant) {
        this.user = user;
        this.itemStack = itemStack;
        this.lvl = lvl;
        this.instant = instant;
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
}
