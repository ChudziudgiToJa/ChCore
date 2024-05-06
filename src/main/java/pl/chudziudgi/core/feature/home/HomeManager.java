package pl.chudziudgi.core.feature.home;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.LocationUtil;

public class HomeManager {

    public Boolean isHome(String var) {
        final Location location = LocationUtil.fromStringToLocation(var);
        return location != null;
    }

    public String getHomeString(User user , HomeType homeType) {
        if (homeType == HomeType.ONE) return user.homeLocation1;
        if (homeType == HomeType.TWO) return user.homeLocation2;
        if (homeType == HomeType.THREE) return user.homeLocation3;
        return null;
    }

    public void setHomeNull(User user ,HomeType homeType) {
        if (homeType == HomeType.ONE) {
            user.homeLocation1 = null;
        }
        if (homeType == HomeType.TWO) {
            user.homeLocation2 = null;
        }
        if (homeType == HomeType.THREE) {
            user.homeLocation3 = null;
        }
    }


    public void setHomeLocation(User user ,Player player,HomeType homeType) {
        if (homeType == HomeType.ONE) {
            user.homeLocation1 = LocationUtil.fromLocationToString(player.getLocation());;
        }
        if (homeType == HomeType.TWO) {
            user.homeLocation2 = LocationUtil.fromLocationToString(player.getLocation());;
        }
        if (homeType == HomeType.THREE) {
            user.homeLocation3 = LocationUtil.fromLocationToString(player.getLocation());;
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
            ChatUtil.error(player, "Masz już ustawiony dom");
            return;
        }
        setHomeLocation(user, player);
        ChatUtil.success(player, "Ustaiono nowy dom");
    }

}
