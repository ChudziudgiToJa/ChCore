package pl.chudziudgi.core.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {
    public static Location fromStringToLocation(String p0) {
        if (p0 == null)
            return null;
        String[] split = p0.split(";");
        return new Location(Bukkit.getWorlds().get(0),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3]),
                Float.parseFloat(split[4]),
                Float.parseFloat(split[5]));
    }

    public static String fromLocationToString(Location loc) {
        return Bukkit.getWorlds().get(0) + ";" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch();
    }
}

