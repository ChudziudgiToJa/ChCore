package pl.chudziudgi.core.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.chudziudgi.core.ChCore;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class Database {
    public static final HashMap<UUID, User> objects = new HashMap<>();
    public static Dao<User, String> dao;

    public static void load(final ChCore plugin) {
        try {
            final String databaseUrl = "jdbc:sqlite:" + new File(plugin.getDataFolder(), "users.db");
            final JdbcConnectionSource jdbcConnectionSource = new JdbcConnectionSource(databaseUrl);
            dao = DaoManager.createDao(jdbcConnectionSource, User.class);
            TableUtils.createTableIfNotExists(jdbcConnectionSource, User.class);
            loadObjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadObjects() {
        for (final Object object : dao) {
            final User it = (User) object;
            objects.put(it.getUuid(), it);
        }
    }

    public static void saveObject(final User user) {
        try {
            dao.createOrUpdate(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveDatabase() {
        for (User value : objects.values()) {
            saveObject(value);
        }
    }
}
