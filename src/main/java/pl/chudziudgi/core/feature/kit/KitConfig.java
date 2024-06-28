package pl.chudziudgi.core.feature.kit;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

public class KitConfig extends OkaeriConfig {
    public boolean kitStarts = true;
    @Comment("<IRON/GOLD/NETHERITE>")
    public KitStandard kitType = KitStandard.IRON;

    public boolean isKitStarts() {
        return kitStarts;
    }

    public void setKitStarts(boolean kitStarts) {
        this.kitStarts = kitStarts;
    }

    public KitStandard getKitType() {
        return kitType;
    }

    public void setKitType(KitStandard kitType) {
        this.kitType = kitType;
    }


}
