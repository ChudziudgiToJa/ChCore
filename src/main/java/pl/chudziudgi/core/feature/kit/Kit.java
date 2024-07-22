package pl.chudziudgi.core.feature.kit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.ArrayList;

public class Kit implements Serializable {

    private final String name;
    private final String permission;
    private final int hour;
    private final Material material;
    private final ArrayList<ItemStack> itemStack;

    public Kit(String name, String permission, int hour, Material material, ArrayList<ItemStack> itemStack) {
        this.name = name;
        this.permission = permission;
        this.hour = hour;
        this.material = material;
        this.itemStack = itemStack;
    }

    public ArrayList<ItemStack> getItemStack() {
        return itemStack;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public int getHour() {
        return hour;
    }

    public Material getMaterial() {
        return material;
    }

}