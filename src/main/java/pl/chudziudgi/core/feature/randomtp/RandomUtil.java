package pl.chudziudgi.core.feature.randomtp;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Random;

public class RandomUtil {

    private static final Random random = new Random();
    private static final RandomTpConfig config = new RandomTpConfig();

    public static Location getRandomCord(Player player) {
        World world = player.getWorld();
        int maxX, maxZ;

        if (world.getEnvironment() == World.Environment.NETHER) {
            maxX = config.getMaxNetherReachTp();
            maxZ = config.getMinimalNetherReachTp();
        } else {
            maxX = config.getMaxReachTp();
            maxZ = config.getMinimalReachTp();
        }

        Location randomLocation = null;
        boolean foundSafeLocation = false;

        while (!foundSafeLocation) {
            int x = random.nextInt(2 * maxX) - maxX;
            int z = random.nextInt(2 * maxZ) - maxZ;
            int y = world.getHighestBlockYAt(x, z);

            randomLocation = new Location(world, x, y+1 , z);
            Material blockType = randomLocation.getBlock().getType();
            if (blockType != Material.WATER && blockType != Material.LAVA && blockType != Material.AIR) {
                foundSafeLocation = true;
            }
        }
        return randomLocation;
    }
}
