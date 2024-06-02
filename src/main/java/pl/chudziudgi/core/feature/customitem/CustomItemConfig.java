package pl.chudziudgi.core.feature.customitem;

import eu.okaeri.configs.OkaeriConfig;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class CustomItemConfig extends OkaeriConfig {

    public List<Material> getDisableCrafting() {
        return disableCrafting;
    }

    public boolean isDiamondItem() {
        return diamondItem;
    }

    private boolean diamondItem = false;

    private List<Material> disableCrafting = Arrays.asList(
            Material.DIAMOND_HELMET,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS,
            Material.DIAMOND_SWORD
    );
}
