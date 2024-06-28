package pl.chudziudgi.core.feature.customitem;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.ItemBuilder;

public class CustomItemStack {

    public static ItemStack magicCandle() {
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

    public static ItemStack obsydianGenerator() {
        return  new ItemBuilder(Material.PURPLE_GLAZED_TERRACOTTA)
                .setTitle("&fGenerator obsydianiu &e❀")
                .addLore(
                        "",
                        "&8Opis",
                        "&7Generator obsydianiu to przedmiot,",
                        "&7który po użyciu generuje obsydian w &bdół ⬇&7.",
                        "&7Pamiętaj wymagane jest puste pole aby generator &n&fzadziałał",
                        "",
                        "&7Postaw &3▜&7▛&7, aby użyć."
                )
                .setGlow(true)
                .build();
    }

    public static ItemStack stoneGenerator() {
        return  new ItemBuilder(Material.GRAY_GLAZED_TERRACOTTA)
                .setTitle("&fGenerator kamienia &e❀")
                .addLore(
                        "",
                        "&8Opis",
                        "&7Generator kamienia to przedmiot,",
                        "&7który po użyciu generuje kamienia 1 blok w &bgórę ⬆&7.",
                        "&7Pamiętaj wymagane jest puste pole aby generator &n&fzadziałał",
                        "",
                        "&7Postaw &3▜&7▛&7, aby użyć."
                )
                .setGlow(true)
                .build();
    }
}
