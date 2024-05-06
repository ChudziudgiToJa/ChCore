package pl.chudziudgi.core.feature.drop;

import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

public class Drop implements Serializable {
    private final String name;
    private final double chance;
    private final boolean fortune;
    private final int minAmount;
    private final int maxAmount;
    private final int exp;
    private final ItemStack itemStack;

    public Drop(final String name, final double chance, final boolean fortune, final int minAmount, final int maxAmount, final int exp, final ItemStack itemStack) {
        this.name = name;
        this.chance = chance;
        this.fortune = fortune;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.exp = exp;
        this.itemStack = itemStack;
    }
    public String getName() {
        return name;
    }
    public double getChance() {
        return chance;
    }
    public boolean isFortune() {
        return fortune;
    }
    public int getMinAmount() {
        return minAmount;
    }
    public int getMaxAmount() {
        return maxAmount;
    }
    public int getExp() {
        return exp;
    }
    public ItemStack getItemStack() {
        return itemStack;
    }
}