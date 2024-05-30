package pl.chudziudgi.core.feature.shop.time;

import eu.okaeri.configs.OkaeriConfig;

public class TimeShopConfig extends OkaeriConfig {

    private int goldPrice = 1450;
    private int ironPrice = 750;
    private int candlePrice = 400;

    public int getCandlePrice() {
        return candlePrice;
    }

    public int getGoldPrice() {
        return goldPrice;
    }

    public int getIronPrice() {
        return ironPrice;
    }
}
