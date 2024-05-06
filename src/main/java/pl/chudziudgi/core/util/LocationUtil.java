package pl.chudziudgi.core.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Objects;

public class LocationUtil {

<<<<<<< HEAD
    public static Location fromStringToLocation(final String p0) {
=======
    public static Location deserialize(final String p0) {
>>>>>>> origin/master
        if (p0 == null) {
            return null;
        }
        final String[] split = p0.split(";");
        return new Location(Bukkit.getWorld(split[0]),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3]),
                Float.parseFloat(split[4]),
                Float.parseFloat(split[5]));
    }

<<<<<<< HEAD
    public static String fromLocationToString(final Location loc) {
=======
    public static String serialize(final Location loc) {
>>>>>>> origin/master
        return (Objects.requireNonNull(loc.getWorld()).getName() +
                ";" + loc.getBlockX()) +
                ";" + loc.getBlockY() +
                ";" + loc.getBlockZ() +
                ";" + loc.getYaw() +
                ";" + loc.getPitch();
    }
}
