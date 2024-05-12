package pl.chudziudgi.core.database;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.feature.drop.Drop;
import pl.chudziudgi.core.feature.drop.DropManager;

import java.io.Serializable;
import java.util.*;

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
    public int dChorus;

    @DatabaseField
    public String homeLocation1;
    @DatabaseField
    public String homeLocation2;
    @DatabaseField
    public String homeLocation3;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public ArrayList<UUID> ignoredList;
    @DatabaseField
    public boolean ignoreStatus;

    @DatabaseField
    public boolean incognito;

    User() {
    }

    public User(UUID uuid) {
        this.uuid = uuid;
        this.dropMessage = true;
        this.dropCobbleStone = true;
        this.dropNetherrack = true;
        this.ignoredList = new ArrayList<>();
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
