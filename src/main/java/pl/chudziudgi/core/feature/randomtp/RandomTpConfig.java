package pl.chudziudgi.core.feature.randomtp;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

public class RandomTpConfig extends OkaeriConfig {
    @Comment(" Wielkość mapy")
    private int worldSize = 1100;
    @Comment("Minimalna ilośc zasięgu random tp")
    private int minimalReachTp = 100;
    private int maxReachTp = 600;

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
