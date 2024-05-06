package pl.chudziudgi.core.feature.nether;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

public class NetherConfig extends OkaeriConfig {

    @Comment("Status czy nether jest on/off")
    boolean netherStatus = true;


    public String getNetherStartTime() {
        return netherStartTime;
    }

    public String getNetherEndTime() {
        return netherEndTime;
    }

    @Comment("format 00:00")
    String netherStartTime = "01:00";
    String netherEndTime = "01:40";

    String netherBlockPortal = "Event Piekło odbywa się w godzinach &8&l15-17&7.";

    public String getNetherBlockPortal() {
        return netherBlockPortal;
    }

    public boolean isNetherStatus() {
        return netherStatus;
    }

    public void setNetherStatus(boolean netherStatus) {
        this.netherStatus = netherStatus;
    }
}
