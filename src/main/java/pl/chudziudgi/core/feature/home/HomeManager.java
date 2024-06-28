package pl.chudziudgi.core.feature.home;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.feature.teleport.TeleportManager;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.LocationUtil;

public class HomeManager {

    public final String message = "Nie posiadasz wymaganej rangi";

    public boolean isHome(String locationString) {
        return LocationUtil.fromStringToLocation(locationString) != null;
    }

    public String getHomeString(User user, HomeType homeType) {
        return switch (homeType) {
            case ONE -> user.homeLocation1;
            case TWO -> user.homeLocation2;
            case THREE -> user.homeLocation3;
            default -> null;
        };
    }

    public void setHomeNull(User user, HomeType homeType) {
        switch (homeType) {
            case ONE:
                user.homeLocation1 = null;
                break;
            case TWO:
                user.homeLocation2 = null;
                break;
            case THREE:
                user.homeLocation3 = null;
                break;
        }
    }

    public void setHomeLocation(User user, Player player, HomeType homeType) {
        String locationString = LocationUtil.fromLocationToString(player.getLocation());
        switch (homeType) {
            case ONE:
                user.homeLocation1 = locationString;
                break;
            case TWO:
                if (!player.hasPermission("core.home.iron")) {
                    ChatUtil.error(player,message);
                    break;
                }
                user.homeLocation2 = locationString;
                break;
            case THREE:
                if (!player.hasPermission("core.home.iron")) {
                    ChatUtil.error(player,message);
                    break;
                }
                user.homeLocation3 = locationString;
                break;
        }
    }

    public void tp(User user, Player player, HomeType type, TeleportManager teleportManager, final ChCore core) {
        if (!isHome(getHomeString(user, type))) {
            return;
        }
        TeleportManager.startTeleportation(player, LocationUtil.fromStringToLocation(getHomeString(user, type)), core);
    }

    public void set(User user, Player player, HomeType type) {
        if (isHome(getHomeString(user, type))) {
            return;
        }
        setHomeLocation(user, player, type);
        ChatUtil.sendTitle(player, "", "&7Ustawiono nowy &3dom", 10,20,10);
    }
}
