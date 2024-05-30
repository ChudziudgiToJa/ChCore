package pl.chudziudgi.core.feature.magiccandle;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.ItemBuilder;

public class MagicCandleUtill {

    public static ItemStack candle(int amoint) {
        return  new ItemBuilder(Material.SNIFFER_EGG, amoint)
                .setTitle("&fMagiczne jajo &e★")
                .addLore(
                        "",
                        "&8Opis",
                        "&7Magiczna świeca to tajemniczy przedmiot,",
                        "&7który po użyciu losowo przemienia się w cenny skarb.",
                        "",
                        "&7Kliknij &7▜&3▛&7, aby użyć."
                )
                .setGlow(true)
                .build();
    }
}
