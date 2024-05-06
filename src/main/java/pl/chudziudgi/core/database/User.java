package pl.chudziudgi.core.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.feature.drop.Drop;
import pl.chudziudgi.core.feature.drop.DropManager;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


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

    User() {
    }

    public User(UUID uuid) {
        this.uuid = uuid;
        enabledDrops.addAll(DropManager.getDrops());
        this.dropMessage = true;
        this.dropCobbleStone = true;
        this.dropNetherrack = true;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }


    public boolean isDropEnabled(Drop drop) {
        return enabledDrops.contains(drop);
    }

    public void changeDropStatus(final Drop p) {
        if (!enabledDrops.contains(p)) {
            enabledDrops.add(p);
        } else enabledDrops.remove(p);
    }

    public void setDropStatus(final Drop p, final boolean b) {
        if (b) enabledDrops.add(p);
        else enabledDrops.remove(p);
    }


    public boolean isNetherDropEnabled(Drop drop) {
        return enabledNetherDrops.contains(drop);
    }

    public void changeNetherDropStatus(final Drop p) {
        if (!enabledNetherDrops.contains(p)) {
            enabledNetherDrops.add(p);
        } else enabledNetherDrops.remove(p);
    }

    public void setNetherDropStatus(final Drop p, final boolean b) {
        if (b) enabledNetherDrops.add(p);
        else enabledNetherDrops.remove(p);
    }
}

