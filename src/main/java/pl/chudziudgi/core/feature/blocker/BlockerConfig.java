package pl.chudziudgi.core.feature.blocker;

import eu.okaeri.configs.OkaeriConfig;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class BlockerConfig extends OkaeriConfig {

    public List<Material> getDisableCrafting() {
        return disableCrafting;
    }

    private List<Material> disableCrafting = Arrays.asList(
            Material.OAK_BOAT,
            Material.SPRUCE_BOAT,
            Material.BIRCH_BOAT,
            Material.ACACIA_BOAT,
            Material.DARK_OAK_BOAT,
            Material.ACACIA_BOAT,
            Material.MANGROVE_BOAT,
            Material.BAMBOO_RAFT,
            Material.SPRUCE_BOAT,
            Material.LIGHT_BLUE_CANDLE,
            Material.DIAMOND_HELMET,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS,
            Material.DIAMOND_SWORD,
            Material.ARMOR_STAND
    );
}
