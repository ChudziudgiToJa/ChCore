package pl.chudziudgi.core.database.user;

import org.bukkit.entity.Player;
import pl.chudziudgi.core.database.Database;

public class UserManager extends Database {

    public static User get(final Player p) {
        return objects.get(p.getUniqueId());
    }

    public static void createUser(final User u) {
        objects.put(u.getPlayer().getUniqueId(), u);
    }

    public static boolean isExists(final Player p) {
        return objects.containsKey(p.getUniqueId());
    }
}
