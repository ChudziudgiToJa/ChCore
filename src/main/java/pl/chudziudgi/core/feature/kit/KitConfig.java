package pl.chudziudgi.core.feature.kit;

import eu.okaeri.configs.OkaeriConfig;

public class KitConfig extends OkaeriConfig {

    public boolean kitStauts = true;

    public boolean isKitStauts() {
        return kitStauts;
    }

    public void setKitStauts(boolean kitStauts) {
        this.kitStauts = kitStauts;
    }
}
