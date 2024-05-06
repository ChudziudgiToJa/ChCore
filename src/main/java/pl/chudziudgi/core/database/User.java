package pl.chudziudgi.core.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.feature.drop.Drop;
import pl.chudziudgi.core.feature.drop.DropManager;

@DatabaseTable(tableName = "users")
public class User {
    public final Set<Drop> enabledDrops = new HashSet<>();

    public final Set<Drop> enabledNetherDrops = new HashSet<>();

    @DatabaseField(id = true)
    public UUID uuid;

    @DatabaseField
    public boolean dropCobbleStone;

    @DatabaseField
    public boolean dropNetherrack;

    @DatabaseField
    public boolean dropMessage;

    @DatabaseField
    public int dEnchantedGoldenApple;

    @DatabaseField
    public int dGoldenApple;

    @DatabaseField
    public int dEnderPearl;

    @DatabaseField
    public int dTotemOfUndying;

    @DatabaseField
    public int dArrow;

    @DatabaseField
    public String homeLocation1;

    @DatabaseField
    public String homeLocation2;

    @DatabaseField
    public String homeLocation3;

    User() {}

    public User(UUID uuid) {
        this.uuid = uuid;
        this.enabledDrops.addAll(DropManager.getDrops());
        this.dropMessage = true;
        this.dropCobbleStone = true;
        this.dropNetherrack = true;
        this.homeLocation1 = null;
        this.homeLocation2 = null;
        this.homeLocation3 = null;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    public boolean isDropEnabled(Drop drop) {
        return this.enabledDrops.contains(drop);
    }

    public void changeDropStatus(Drop p) {
        if (!this.enabledDrops.contains(p)) {
            this.enabledDrops.add(p);
        } else {
            this.enabledDrops.remove(p);
        }
    }

    public void setDropStatus(Drop p, boolean b) {
        if (b) {
            this.enabledDrops.add(p);
        } else {
            this.enabledDrops.remove(p);
        }
    }

    public boolean isNetherDropEnabled(Drop drop) {
        return this.enabledNetherDrops.contains(drop);
    }

    public void changeNetherDropStatus(Drop p) {
        if (!this.enabledNetherDrops.contains(p)) {
            this.enabledNetherDrops.add(p);
        } else {
            this.enabledNetherDrops.remove(p);
        }
    }

    public void setNetherDropStatus(Drop p, boolean b) {
        if (b) {
            this.enabledNetherDrops.add(p);
        } else {
            this.enabledNetherDrops.remove(p);
        }
    }
}
