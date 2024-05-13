package pl.chudziudgi.core.database.user;

import org.bukkit.entity.Player;
import pl.chudziudgi.core.database.Database;

public class UserManager extends Database {

    public static User getUser(final Player p) {
        if (objects.get(p.getUniqueId()) == null) {
            return createUser(new User(p.getUniqueId()));
        }
        return objects.get(p.getUniqueId());
    }

    public static User createUser(final User u) {
        objects.put(u.getPlayer().getUniqueId(), u);
        return u;
    }
}
