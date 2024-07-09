package pl.chudziudgi.core.database.user;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.feature.backup.Backup;
import pl.chudziudgi.core.feature.drop.Drop;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

@DatabaseTable(tableName = "users")
public class User implements Serializable {


    @DatabaseField(id = true)
    public UUID uuid;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public ArrayList<Drop> dropList;
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
    @DatabaseField
    public int dIce;


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
    public boolean chatQuestionStatus;

    @DatabaseField
    public boolean incognito;

    @DatabaseField
    public boolean socialSpyStatus;

    @DatabaseField
    public boolean vanishStatus;

    @DatabaseField
    public int timeCoin;
    @DatabaseField
    public boolean timeMessage;

    @DatabaseField
    public int answerCandle;

    @DatabaseField
    public boolean breakBlock;
    @DatabaseField
    public boolean placeBlock;
    @DatabaseField
    public boolean useBlock;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public ArrayList<Backup> backupList;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public ArrayList<Backup> backupAnswerList;


    public User() {
    }

    public User(UUID uuid) {
        this.uuid = uuid;

        this.dropList = new ArrayList<>();
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

        this.chatQuestionStatus = true;
        this.chatAutoMessageStatus = true;
        this.chatMagicCandleStatus = true;
        this.ignoreStatus = false;
        this.socialSpyStatus = false;

        this.ignoredList = new ArrayList<>();

        this.timeCoin = 0;
        this.timeMessage = true;

        this.answerCandle = 0;

        this.useBlock = false;
        this.breakBlock = false;
        this.placeBlock = false;

        this.backupList = new ArrayList<>();
        this.backupAnswerList = new ArrayList<>();

    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    public void changeOverWorldDropStatus(Drop p) {
        if (!this.dropList.contains(p)) {
            this.dropList.add(p);
        } else {
            this.dropList.remove(p);
        }
    }

    public void setOverWorldDropStatus(Drop p, boolean b) {
        if (b) {
            this.dropList.add(p);
        } else {
            this.dropList.remove(p);
        }
    }
}
