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
    private List<Drop> overWorldDropList = Arrays.asList(
            new Drop("Diamenty",
                    0.02,
                    true,
                    1,
                    1,
                    2,
                    World.Environment.NORMAL,
                    Material.DIAMOND),

            new Drop("Czerwony proch",
                    0.05,
                    true,
                    1,
                    2,
                    1,
                    World.Environment.NORMAL,
                    Material.REDSTONE),

            new Drop("Węgiel",
                    0.06,
                    true,
                    1,
                    4,
                    1,
                    World.Environment.NORMAL,
                    Material.COAL),

            new Drop("Lapiz",
                    0.04,
                    true,
                    1,
                    4,
                    1,
                    World.Environment.NORMAL,
                    Material.LAPIS_LAZULI),

            new Drop("Złoto",
                    0.03,
                    true,
                    1,
                    2,
                    2,
                    World.Environment.NORMAL,
                    Material.GOLD_ORE),

            new Drop("Żelazo",
                    0.02,
                    true,
                    1,
                    2,
                    2,
                    World.Environment.NORMAL,
                    Material.IRON_ORE),

            new Drop("Miedź",
                    0.05,
                    true,
                    1,
                    3,
                    1,
                    World.Environment.NORMAL,
                    Material.COPPER_ORE),

            new Drop("Emerald",
                    0.02,
                    false,
                    1,
                    1,
                    2,
                    World.Environment.NORMAL,
                    Material.EMERALD),

            new Drop("Obsydian",
                    0.02,
                    true,
                    1,
                    3,
                    1,
                    World.Environment.NORMAL,
                    Material.OBSIDIAN),

            new Drop("Perła",
                    0.02,
                    false,
                    1,
                    1,
                    3,
                    World.Environment.NORMAL,
                    Material.ENDER_PEARL),

            new Drop("Jabłko",
                    0.04,
                    false,
                    1,
                    2,
                    2,
                    World.Environment.NORMAL,
                    Material.APPLE),

            new Drop("Skóra",
                    0.03,
                    false,
                    1,
                    1,
                    3,
                    World.Environment.NORMAL,
                    Material.LEATHER)
    );

    private List<Drop> netherDropList = Arrays.asList(

            new Drop("Płomienna różdżka",
                    0.01,
                    false,
                    1,
                    1,
                    2,
                    World.Environment.NETHER,
                    Material.BLAZE_ROD),

            new Drop("Proch",
                    0.03,
                    false,
                    1,
                    3,
                    2,
                    World.Environment.NETHER,
                    Material.GUNPOWDER),

            new Drop("Perła",
                    0.04,
                    false,
                    1,
                    1,
                    3,
                    World.Environment.NETHER,
                    Material.ENDER_PEARL),

            new Drop("Brodawka",
                    0.02,
                    false,
                    1,
                    3,
                    2,
                    World.Environment.NETHER,
                    Material.NETHER_WART),

            new Drop("Złoto",
                    0.05,
                    true,
                    1,
                    1,
                    2,
                    World.Environment.NETHER,
                    Material.GOLD_INGOT)
    );


    public List<Drop> getOverWorldDropList() {
        return this.overWorldDropList;
    }
    public List<Drop> getNetherDropList() {
        return netherDropList;
    }
}