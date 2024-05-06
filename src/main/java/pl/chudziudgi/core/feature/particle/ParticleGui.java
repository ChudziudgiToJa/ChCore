package pl.chudziudgi.core.feature.particle;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.database.UserManager;

public class ParticleGui {
    public static void open(final Player player) {
        final InventoryBuilder inv = new InventoryBuilder("&7Kosmetyki", 9);
        final User user = UserManager.getUser(player);

        final ItemStack iron = new ItemBuilder(Material.IRON_INGOT)
<<<<<<< HEAD
                .setTitle("&8Dusze")
                .addLore(
                        "",
                        "&7dostępne od rangi &f&lIRON",
                        (player.hasPermission("core.particle.use")) ? "&7Status: " + (user.particleStatus ? "&aTAK" : "&cNIE") : "&cNie posiadasz dostępu do tego kosmetyku"
=======
                .setTitle("&8Kolor &f&lBIAŁY")
                .addLore(
                        "",
                        "dostępne od rangi &f&lIRON",
                        (player.hasPermission("core.particle.use")) ? "&7Status: " + (user.isParticleStatus() ? "&aTAK" : "&cNIE") : "&cNie posiadasz dostępu do tego kosmetyku"
>>>>>>> origin/master
                )
                .setGlow(user.particleStatus)
                .build();


        inv.setItem(1, iron, e -> {
            user.particleStatus = !user.particleStatus;
            open(player);
        });

        inv.open(player);
    }
}
