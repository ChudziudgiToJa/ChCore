package pl.chudziudgi.core.database;

import org.bukkit.entity.Player;

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
