package pl.chudziudgi.core.feature.drop;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

import java.util.Arrays;
import java.util.List;

public class DropConfig extends OkaeriConfig {

    public List<Drop> getDropItemList() {
        return dropItemList;
    }

    @Comment("Lista dropów")
    private List<Drop> dropItemList = Arrays.asList(
            new Drop("Diamenty",
                    0.02,
                    true,
                    1,
                    1,
                    2,
            "DIAMOND"),

            new Drop("Czerwony proch",
                    0.05,
                    true,
                    1,
                    2,
                    1,
                    "REDSTONE"),

            new Drop("Węgiel",
                    0.06,
                    true,
                    1,
                    4,
                    1,
                    "COAL"),

            new Drop("Lapiz",
                    0.04,
                    true,
                    1,
                    4,
                    1,
                    "LAPIS_LAZULI"),

            new Drop("Złoto",
                    0.03,
                    true,
                    1,
                    2,
                    2,
                    "GOLD_ORE"),

            new Drop("Żelazo",
                    0.02,
                    true,
                    1,
                    2,
                    2,
                    "IRON_ORE"),

            new Drop("Miedź",
                    0.05,
                    true,
                    1,
                    3,
                    1,
                    "COPPER_ORE"),

            new Drop("Emerald",
                    0.02,
                    false,
                    1,
                    1,
                    2,
                    "EMERALD"),

            new Drop("Obsydian",
                    0.02,
                    true,
                    1,
                    3,
                    1,
                    "OBSIDIAN"),

            new Drop("Perła",
                    0.02,
                    false,
                    1,
                    1,
                    3,
                    "ENDER_PEARL"),

            new Drop("Jabłko",
                    0.04,
                    false,
                    1,
                    2,
                    2,
                    "APPLE"),

            new Drop("Skóra",
                    0.03,
                    false,
                    1,
                    1,
                    3,
                    "LEATHER"),

            new Drop("Proch",
                    0.02,
                    false,
                    1,
                    2,
                    2,
                    "GUNPOWDER")
    );
}