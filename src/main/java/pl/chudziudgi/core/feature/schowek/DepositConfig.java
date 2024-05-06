package pl.chudziudgi.core.feature.schowek;

import eu.okaeri.configs.OkaeriConfig;

public class DepositConfig extends OkaeriConfig {
    int enchantedGoldenAppleLimit = 1;
    int goldenAppleLimit = 8;
    int enderPearlLimit = 2;
    int totemOfUndyingLimit = 1;
    int ArrowLimit = 16;

    public int getEnchantedGoldenAppleLimit() {
        return enchantedGoldenAppleLimit;
    }

    public int getGoldenAppleLimit() {
        return goldenAppleLimit;
    }

    public int getEnderPearlLimit() {
        return enderPearlLimit;
    }

    public int getTotemOfUndyingLimit() {
        return totemOfUndyingLimit;
    }

    public int getArrowLimit() {
        return ArrowLimit;
    }
}
