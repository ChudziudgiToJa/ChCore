package pl.chudziudgi.core.feature.abyss;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

import java.util.List;

public class AbyssUtil {

    public static void itemClear() {
        for (World world : Bukkit.getWorlds()) {
            List<Entity> entList = world.getEntities();
            for (Entity current : entList) {
                if (current instanceof Item) {
                    current.remove();
                }
            }
        }
    }
}
