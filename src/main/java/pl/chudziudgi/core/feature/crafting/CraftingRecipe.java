package pl.chudziudgi.core.feature.crafting;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.feature.customitem.CustomItemStack;

public class CraftingRecipe {

    public void loadCrafting(final ChCore core) {
        loadEnchantedGoldenApple(core);
        loadArrow(core);
        loadString(core);
        loadPackedIce(core);
        loadGravel(core);
        loadStoneRecipe(core);
        loadObsydianRecipe(core);
    }

    private void loadEnchantedGoldenApple(final ChCore core) {
        ItemStack itemStack = new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE).build();
        NamespacedKey namespacedKey = new NamespacedKey(core, "custom_enchanted_golden_apple");
        ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack);
        shapedRecipe.shape("121", "232", "121");
        shapedRecipe.setIngredient('3', Material.APPLE);
        shapedRecipe.setIngredient('2', Material.GOLD_BLOCK);
        shapedRecipe.setIngredient('1', Material.AIR);
        core.getServer().addRecipe(shapedRecipe);
    }

    private void loadArrow(final ChCore core) {
        ItemStack itemStack = new ItemBuilder(Material.ARROW, 4).build();
        NamespacedKey namespacedKey = new NamespacedKey(core, "custom_arrow");
        ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack);
        shapedRecipe.shape("113", "141", "211");
        shapedRecipe.setIngredient('4', Material.STICK);
        shapedRecipe.setIngredient('3', Material.IRON_NUGGET);
        shapedRecipe.setIngredient('2', Material.FLINT);
        shapedRecipe.setIngredient('1', Material.AIR);
        core.getServer().addRecipe(shapedRecipe);
    }

    private void loadString(final ChCore core) {
        ItemStack itemStack = new ItemBuilder(Material.STRING, 3).build();
        NamespacedKey namespacedKey = new NamespacedKey(core, "custom_string");
        ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack);
        shapedRecipe.shape("111", "222", "111");
        shapedRecipe.setIngredient('2', Material.LEATHER);
        shapedRecipe.setIngredient('1', Material.AIR);
        core.getServer().addRecipe(shapedRecipe);
    }

    private void loadPackedIce(final ChCore core) {
        ItemStack itemStack = new ItemBuilder(Material.PACKED_ICE, 8).build();
        NamespacedKey namespacedKey = new NamespacedKey(core, "custom_packed_ice");
        ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack);
        shapedRecipe.shape("111", "121", "111");
        shapedRecipe.setIngredient('2', Material.WATER_BUCKET);
        shapedRecipe.setIngredient('1', Material.LAPIS_LAZULI);
        core.getServer().addRecipe(shapedRecipe);
    }

    private void loadGravel(final ChCore core) {
        ItemStack itemStack = new ItemBuilder(Material.GRAVEL, 3).build();
        NamespacedKey namespacedKey = new NamespacedKey(core, "custom_gravel");
        ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack);
        shapedRecipe.shape("111", "222", "111");
        shapedRecipe.setIngredient('1', Material.COBBLESTONE);
        shapedRecipe.setIngredient('2', Material.SAND);
        core.getServer().addRecipe(shapedRecipe);
    }

    private void loadStoneRecipe(final ChCore core) {
        ItemStack itemStack = CustomItemStack.stoneGenerator();
        NamespacedKey stoneGenerator = new NamespacedKey(core, "custom_stone_generator");
        ShapedRecipe stoneGeneratorRecipe = new ShapedRecipe(stoneGenerator, itemStack);
        stoneGeneratorRecipe.shape("%%%", "%$%", "%%%");
        stoneGeneratorRecipe.setIngredient('%', Material.STONE);
        stoneGeneratorRecipe.setIngredient('$', Material.COPPER_BLOCK);
        core.getServer().addRecipe(stoneGeneratorRecipe);
    }

    private void loadObsydianRecipe(final ChCore core) {
        ItemStack itemStack = CustomItemStack.obsydianGenerator();
        NamespacedKey stoneGenerator = new NamespacedKey(core, "custom_obsydian_generator");
        ShapedRecipe stoneGeneratorRecipe = new ShapedRecipe(stoneGenerator, itemStack);
        stoneGeneratorRecipe.shape("%%%", "%$%", "%%%");
        stoneGeneratorRecipe.setIngredient('%', Material.COPPER_BLOCK);
        stoneGeneratorRecipe.setIngredient('$', Material.OBSIDIAN);
        core.getServer().addRecipe(stoneGeneratorRecipe);
    }
}
