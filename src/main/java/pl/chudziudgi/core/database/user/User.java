package pl.chudziudgi.core.database.user;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.ConfigLoader;
import pl.chudziudgi.core.feature.drop.Drop;
import pl.chudziudgi.core.feature.drop.DropConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@DatabaseTable(tableName = "users")
public class User implements Serializable {

    @DatabaseField(id = true)
    public UUID uuid;

    public transient Set<Drop> enabledNetherDrops = new HashSet<>();
    public transient Set<Drop> enabledDrops = new HashSet<>();
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
    public boolean chatAutoMessageStatus;

    @DatabaseField
    public boolean incognito;

    @DatabaseField
    public boolean vanishStatus;

    public User() {
    }

    public User(UUID uuid) {
        this.uuid = uuid;
        this.chatAutoMessageStatus = true;
        this.ignoredList = new ArrayList<>();
        this.enabledNetherDrops = new HashSet<>();
        this.enabledDrops = new HashSet<>();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
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
