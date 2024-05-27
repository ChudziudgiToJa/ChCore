package pl.chudziudgi.core.database.user;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.feature.drop.Drop;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@DatabaseTable(tableName = "users")
public class User implements Serializable {

    @DatabaseField(id = true)
    public UUID uuid;

    public transient Set<Drop> enabledDrops = new HashSet<>();
    @DatabaseField
    public boolean dropOrginalBlock;
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

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public Instant kitStart;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public Instant kitIron;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public Instant kitGold;

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

    @DatabaseField
    public int timeShop;

    public User() {
    }

    public User(UUID uuid) {
        this.uuid = uuid;

        this.enabledDrops = new HashSet<>();
        this.dropMessage = true;
        this.dropOrginalBlock = true;

        this.dEnchantedGoldenApple = 0;
        this.dGoldenApple = 0;
        this.dEnderPearl = 0;
        this.dTotemOfUndying = 0;
        this.dArrow = 0;
        this.dChorus = 0;

        this.incognito = false;

        this.vanishStatus = false;

        this.chatAutoMessageStatus = true;
        this.ignoreStatus = false;

        this.ignoredList = new ArrayList<>();

        this.timeShop = 0;
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
}
