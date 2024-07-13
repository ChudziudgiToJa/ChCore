package pl.chudziudgi.core.feature.customitem;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.ItemBuilder;

public class CustomItemRecipe {

    public static void loadStoneRecipe(final ChCore core) {
        ItemStack itemStack = CustomItemStack.stoneGenerator();
        NamespacedKey stoneGenerator = new NamespacedKey(core, "stone_generator");
        ShapedRecipe stoneGeneratorRecipe = new ShapedRecipe(stoneGenerator, itemStack);
        stoneGeneratorRecipe.shape("%%%", "%$%", "%%%");
        stoneGeneratorRecipe.setIngredient('%', Material.COPPER_INGOT);
        stoneGeneratorRecipe.setIngredient('$', Material.STONE);
        core.getServer().addRecipe(stoneGeneratorRecipe);
    }

    public static void loadObsydianRecipe(final ChCore core) {
        ItemStack itemStack = CustomItemStack.obsydianGenerator();
        NamespacedKey stoneGenerator = new NamespacedKey(core, "obsydian_generator");
        ShapedRecipe stoneGeneratorRecipe = new ShapedRecipe(stoneGenerator, itemStack);
        stoneGeneratorRecipe.shape("%%%", "%$%", "%%%");
        stoneGeneratorRecipe.setIngredient('%', Material.COPPER_BLOCK);
        stoneGeneratorRecipe.setIngredient('$', Material.OBSIDIAN);
        core.getServer().addRecipe(stoneGeneratorRecipe);
    }

    public static void loadIceRecipe(final ChCore core) {
        ItemStack itemStack = new ItemBuilder(Material.PACKED_ICE,8).build();
        NamespacedKey stoneGenerator = new NamespacedKey(core, "packed_ice");
        ShapedRecipe stoneGeneratorRecipe = new ShapedRecipe(stoneGenerator, itemStack);
        stoneGeneratorRecipe.shape("%%%", "%$%", "%%%");
        stoneGeneratorRecipe.setIngredient('%', Material.LAPIS_LAZULI);
        stoneGeneratorRecipe.setIngredient('$', Material.WATER_BUCKET);
        core.getServer().addRecipe(stoneGeneratorRecipe);
    }
}
