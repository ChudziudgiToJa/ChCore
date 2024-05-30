package pl.chudziudgi.core.feature.randomtp;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

public class RandomTpConfig extends OkaeriConfig {

    @Comment("nether")
    private int netherSize = 500;
    private int minimalNetherReachTp = 10;
    private int maxNetherReachTp = 250;
    @Comment("overworld")
    private int worldSize = 1100;
    private int minimalReachTp = 10;
    private int maxReachTp = 600;

    public int getNetherSize() {
        return netherSize;
    }

    public int getMinimalNetherReachTp() {
        return minimalNetherReachTp;
    }

    public int getMaxNetherReachTp() {
        return maxNetherReachTp;
    }

    public int getMaxReachTp() {
        return maxReachTp;
    }

    public int getWorldSize() {
        return worldSize;
    }

    public int getMinimalReachTp() {
        return minimalReachTp;
    }
}
