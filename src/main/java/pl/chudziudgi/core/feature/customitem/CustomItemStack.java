package pl.chudziudgi.core.feature.customitem;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.ItemBuilder;

public class CustomItemStack {

    public static ItemStack candle() {
        return  new ItemBuilder(Material.LIGHT_BLUE_CANDLE)
                .setTitle("&fMagiczne świeca &e★")
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

    public static ItemStack boyfarmer() {
        return  new ItemBuilder(Material.CRYING_OBSIDIAN)
                .setTitle("&fGenerator obsydianiu &e★")
                .addLore(
                        "",
                        "&8Opis",
                        "&7Generator obsydianiu to przedmiot,",
                        "&7który po użyciu generuje obsydian w &b⬇.",
                        "&7Pamiętaj wymagane jest puste pole aby generator &n&fzadziałał",
                        "",
                        "&7Postaw &3▜&7▛&7, aby użyć."
                )
                .setGlow(true)
                .build();
    }
}
