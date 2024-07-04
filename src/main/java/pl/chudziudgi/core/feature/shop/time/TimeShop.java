package pl.chudziudgi.core.feature.shop.time;

import org.bukkit.Material;

import java.io.Serializable;
import java.util.ArrayList;

public class TimeShop implements Serializable {

    private final Material material;
    private final String name;
    private final int price;
    private final String command;

    public TimeShop(final Material material, final String name, final int price, final String command) {
        this.material = material;
        this.name = name;
        this.price = price;
        this.command = command;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCommand() {
        return command;
    }

}
