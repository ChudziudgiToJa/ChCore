package pl.chudziudgi.core.feature.access;

import eu.okaeri.configs.OkaeriConfig;

public class AccessConfig extends OkaeriConfig {

    private int maxPlayers = 250;
    private int minimalForIron = 150;
    private int minimalForGold = 200;

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMinimalForIron() {
        return minimalForIron;
    }

    public void setMinimalForIron(int minimalForIron) {
        this.minimalForIron = minimalForIron;
    }

    public int getMinimalForGold() {
        return minimalForGold;
    }

    public void setMinimalForGold(int minimalForGold) {
        this.minimalForGold = minimalForGold;
    }
}
