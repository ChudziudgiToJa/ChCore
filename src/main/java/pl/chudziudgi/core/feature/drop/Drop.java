package pl.chudziudgi.core.feature.drop;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

public class Drop implements Serializable {
    private final String name;
    private final double chance;
    private final boolean fortune;
    private final int minAmount;
    private final int maxAmount;
    private final int exp;
    private final World.Environment worldType;
    private final Material material;

    public Drop(final String name, final double chance, final boolean fortune, final int minAmount, final int maxAmount, final int exp, World.Environment worldType, final Material material) {
        this.name = name;
        this.chance = chance;
        this.fortune = fortune;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.exp = exp;
        this.worldType = worldType;
        this.material = material;
    }

    public Material getMaterial() {
        return material;
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

    public World.Environment getWorldType() {
        return worldType;
    }
}