package pl.chudziudgi.core.feature.drop;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class DropConfig extends OkaeriConfig {

    @Comment("Lista dropów")
    private List<Drop> dropList = Arrays.asList(
            new Drop("Diamenty",
                    0.02,
                    true,
                    1,
                    1,
                    2,
                    World.Environment.NORMAL,
                    new ItemStack(Material.DIAMOND)),

            new Drop("Czerwony proch",
                    0.07,
                    true,
                    1,
                    2,
                    1,
                    World.Environment.NORMAL,
                    new ItemStack(Material.REDSTONE)),

            new Drop("Węgiel",
                    0.09,
                    true,
                    1,
                    4,
                    1,
                    World.Environment.NORMAL,
                    new ItemStack(Material.COAL)),

            new Drop("Lapiz",
                    0.04,
                    true,
                    1,
                    4,
                    1,
                    World.Environment.NORMAL,
                    new ItemStack(Material.LAPIS_LAZULI)),

            new Drop("Złoto",
                    0.04,
                    true,
                    1,
                    3,
                    2,
                    World.Environment.NORMAL,
                    new ItemStack(Material.GOLD_ORE)),

            new Drop("Żelazo",
                    0.05,
                    true,
                    1,
                    3,
                    2,
                    World.Environment.NORMAL,
                    new ItemStack(Material.IRON_ORE)),

            new Drop("Miedź",
                    0.08,
                    true,
                    1,
                    4,
                    1,
                    World.Environment.NORMAL,
                    new ItemStack(Material.COPPER_ORE)),

            new Drop("Płomienna różdżka",
                    0.01,
                    false,
                    1,
                    1,
                    2,
                    World.Environment.NETHER,
                    new ItemStack(Material.BLAZE_ROD)),

            new Drop("Proch",
                    0.03,
                    false,
                    1,
                    3,
                    2,
                    World.Environment.NETHER,
                    new ItemStack(Material.GUNPOWDER)),

            new Drop("Perła",
                    0.02,
                    false,
                    1,
                    1,
                    3,
                    World.Environment.NETHER,
                    new ItemStack(Material.ENDER_PEARL)),

            new Drop("Odłamek netherytu",
                    0.01,
                    false,
                    1,
                    1,
                    3,
                    World.Environment.NETHER,
                    new ItemStack(Material.NETHERITE_SCRAP)),

            new Drop("Brodawka",
                    0.02,
                    false,
                    1,
                    3,
                    2,
                    World.Environment.NETHER,
                    new ItemStack(Material.NETHER_WART)),

            new Drop("Złoto",
                    0.05,
                    true,
                    1,
                    1,
                    2,
                    World.Environment.NETHER,
                    new ItemStack(Material.GOLD_INGOT)),

            new Drop("Odłaek złota",
                    0.04,
                    true,
                    1,
                    2,
                    1,
                    World.Environment.NETHER,
                    new ItemStack(Material.GOLD_NUGGET)
            ));


    public List<Drop> getDropList() {
        return this.dropList;
    }

}
