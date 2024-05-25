package pl.chudziudgi.core.feature.randomtp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import java.util.Random;

public final class RandomUtil {

    public static final Random RANDOM_INSTANCE = new Random();
    private static final RandomTpConfig config = new RandomTpConfig();

    public static Double getRandDouble(final double min, final double max) {
        return RANDOM_INSTANCE.nextDouble() * (max - min) + min;
    }

    public static Double getRandomDouble(final double min, final double max) {
        return RANDOM_INSTANCE.nextDouble() * (max - min) + min;
    }
    public static Location getRandomCords(int i) {
        Location location = new Location(Bukkit.getWorlds().get(i), RandomUtil.getRandomDouble(-config.getMinimalReachTp(), config.getMaxReachTp()), 0.0, RandomUtil.getRandomDouble(-config.getMinimalReachTp(), config.getMaxReachTp()));
        while (location.getBlock().getBiome() == Biome.OCEAN || location.getBlock().getBiome() == Biome.DEEP_OCEAN || location.getBlock().getBiome() == Biome.RIVER || location.getBlock().getType() == Material.WATER || location.getBlock().getType() == Material.LAVA || !(location.getBlock().getType() == Material.AIR)) {
            location = new Location(Bukkit.getWorlds().get(0), RandomUtil.getRandomDouble(-config.getMinimalReachTp(), config.getMaxReachTp()), 0.0, RandomUtil.getRandomDouble(-config.getMinimalReachTp(), config.getMaxReachTp()));
        }
        location.setY((double) location.getWorld().getHighestBlockYAt(location.getBlockX() + 10, location.getBlockZ()) + 1);
        return location;
    }

}
