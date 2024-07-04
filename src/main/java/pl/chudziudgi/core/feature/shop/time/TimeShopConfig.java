package pl.chudziudgi.core.feature.shop.time;

import eu.okaeri.configs.OkaeriConfig;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class TimeShopConfig extends OkaeriConfig {

    private List<TimeShop> item = Arrays.asList(

            new TimeShop(
                    Material.IRON_HELMET,
                    "&fRanga &f&lIRON",
                    10,
                    "lp user {PLAYER} group add iron"
            ),
            new TimeShop(
                    Material.GOLDEN_HELMET,
                    "&fRanga &e&lGOLD",
                    19,
                    "lp user {PLAYER} group add gold"
            ),
            new TimeShop(
                    Material.LIGHT_BLUE_CANDLE,
                    "&fMagiczna świeca &e★",
                    321,
                    "customitem give candle 1 {PLAYER}"
            ),
            new TimeShop(
                    Material.IRON_SWORD,
                    "&fKit &f&lIRON",
                    5125,
                    "kit daj {PLAYER} iron"
            ),
            new TimeShop(
                    Material.GOLDEN_SWORD,
                    "&fKit &e&lGOLD",
                    166121,
                    "kit daj {PLAYER} gold"
            ),
            new TimeShop(
                    Material.WATER_BUCKET,
                    "&fWiadro wody",
                    73434,
                    "give {PLAYER} minecraft:water_bucket"
            ),
            new TimeShop(
                    Material.LAVA_BUCKET,
                    "&fWiadro lawy",
                    73434,
                    "give {PLAYER} minecraft:lava_bucket"
            ),
            new TimeShop(
                    Material.SUGAR_CANE,
                    "&fTrzcina cukrowa",
                    73434,
                    "give {PLAYER} minecraft:sugar_cane"
            ),
            new TimeShop(
                    Material.TOTEM_OF_UNDYING,
                    "&ftotem",
                    234,
                    "give {PLAYER} minecraft:totem_of_undying"
            ),
            new TimeShop(
                    Material.PURPLE_GLAZED_TERRACOTTA,
                    "&fGenerator obsydianiu &e❀ &8x5",
                    166121,
                    "customitem give obsydian 5 {PLAYER}"
            ),
            new TimeShop(
                    Material.GRAY_GLAZED_TERRACOTTA,
                    "&fGenerator kamienia &e❀ &8x5",
                    512,
                    "customitem give stone 5 {PLAYER}"
            )
    );

    public List<TimeShop> getItem() {
        return item;
    }
}
