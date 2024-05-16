package pl.chudziudgi.core.feature.home;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.database.user.User;
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
        Player player = user.getPlayer();
        switch (homeType) {
            case ONE:
                user.homeLocation1 = null;
                break;
            case TWO:
                if (!player.hasPermission("core.home.iron")) {
                    ChatUtil.error(player,message);
                    break;
                }
                user.homeLocation2 = null;
                break;
            case THREE:
                if (!player.hasPermission("core.home.gold")) {
                    ChatUtil.error(player,message);
                    break;
                }
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

    public void tp(User user, Player player, HomeType type) {
        if (!isHome(getHomeString(user, type))) {
            ChatUtil.error(player, "Nie posaidasz domu.");
            player.closeInventory();
            return;
        }
        player.teleport(LocationUtil.fromStringToLocation(getHomeString(user, type)));
    }

    public void set(User user, Player player, HomeType type) {
        if (isHome(getHomeString(user, type))) {
            ChatUtil.error(player, "Masz juustawiony dom");
            return;
        }
        setHomeLocation(user, player, type);
        ChatUtil.success(player, "Ustaiono nowy dom");
    }
}
