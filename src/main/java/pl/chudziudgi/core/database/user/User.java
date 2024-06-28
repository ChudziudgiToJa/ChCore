package pl.chudziudgi.core.database.user;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.feature.drop.Drop;
import pl.chudziudgi.core.feature.drop.DropUtil;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

@DatabaseTable(tableName = "users")
public class User implements Serializable {

    public ArrayList<Drop> enabledOverWorldDrops = new ArrayList<>();
    public ArrayList<Drop> enabledNetherDrops = new ArrayList<>();

    @DatabaseField(id = true)
    public UUID uuid;

    @DatabaseField
    public boolean dropOriginalBlock;
    @DatabaseField
    public boolean dropMessage;
    @DatabaseField
    public int minedStone;

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
    public boolean chatMagicCandleStatus;

    @DatabaseField
    public boolean incognito;

    @DatabaseField
    public boolean socialSpyStatus;

    @DatabaseField
    public boolean vanishStatus;

    @DatabaseField
    public int timeShop;

    @DatabaseField
    public int answerCandle;

    @DatabaseField
    public boolean breakBlock;

    @DatabaseField
    public boolean placeBlock;

    @DatabaseField
    public boolean useBlock;

    public User() {
    }

    public User(UUID uuid) {
        this.uuid = uuid;

        this.dropMessage = true;
        this.dropOriginalBlock = true;
        this.minedStone = 0;

        this.dEnchantedGoldenApple = 0;
        this.dGoldenApple = 0;
        this.dEnderPearl = 0;
        this.dTotemOfUndying = 0;
        this.dArrow = 0;
        this.dChorus = 0;

        this.incognito = false;

        this.vanishStatus = false;

        this.chatAutoMessageStatus = true;
        this.chatMagicCandleStatus = true;
        this.ignoreStatus = false;
        this.socialSpyStatus = false;

        this.ignoredList = new ArrayList<>();

        this.timeShop = 0;

        this.answerCandle = 0;

        this.useBlock = false;
        this.breakBlock = false;
        this.placeBlock = false;

    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    public void changeOverWorldDropStatus(Drop p) {
        if (!this.enabledOverWorldDrops.contains(p)) {
            this.enabledOverWorldDrops.add(p);
        } else {
            this.enabledOverWorldDrops.remove(p);
        }
    }

    public void setOverWorldDropStatus(Drop p, boolean b) {
        if (b) {
            this.enabledOverWorldDrops.add(p);
        } else {
            this.enabledOverWorldDrops.remove(p);
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
