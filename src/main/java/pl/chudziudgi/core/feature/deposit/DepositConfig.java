package pl.chudziudgi.core.feature.deposit;

import eu.okaeri.configs.OkaeriConfig;

public class DepositConfig extends OkaeriConfig {
    int enchantedGoldenAppleLimit = 1;
    int goldenAppleLimit = 8;
    int enderPearlLimit = 2;
    int totemOfUndyingLimit = 1;
    int arrowLimit = 16;
    int chorusLimit = 5;

    public int getChorusLimit() {
        return chorusLimit;
    }

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
        return arrowLimit;
    }
}
