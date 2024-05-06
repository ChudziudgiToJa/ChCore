package pl.chudziudgi.core.feature.home;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.database.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class HomeGui {

    public static void openHome(final Player player) {
        final HomeManager homeManager = new HomeManager();
        final InventoryBuilder inv = new InventoryBuilder("Domy: " + player.getName(), InventoryType.HOPPER);
        final User user = UserManager.getUser(player);

        final ItemStack home1 = new ItemBuilder((homeManager.isHome(user.homeLocation1) ? Material.LIME_DYE : Material.RED_DYE)).setTitle("&7DOM I").addLore("", (homeManager.isHome(user.homeLocation1) ? "&aKliknij aby teleportować się." : "&cNie asz ustawionego domu. /sethome")).setGlow(homeManager.isHome(user.homeLocation1)).build();
        final ItemStack home2 = new ItemBuilder((homeManager.isHome(user.homeLocation2) ? Material.LIME_DYE : Material.RED_DYE)).setTitle("&7DOM II").addLore("", (homeManager.isHome(user.homeLocation2) ? "&aKliknij aby teleportować się." : "&cNie asz ustawionego domu. /sethome")).setGlow(homeManager.isHome(user.homeLocation2)).build();
        final ItemStack home3 = new ItemBuilder((homeManager.isHome(user.homeLocation3) ? Material.LIME_DYE : Material.RED_DYE)).setTitle("&7DOM III").addLore("", (homeManager.isHome(user.homeLocation3) ? "&aKliknij aby teleportować się." : "&cNie asz ustawionego domu. /sethome")).setGlow(homeManager.isHome(user.homeLocation3)).build();


        inv.setItem(1, home1, e -> {
            homeManager.tp(user, player, HomeType.ONE);
            player.closeInventory();
        });
        inv.open(player);

        inv.setItem(2, home2, e -> {
            homeManager.tp(user, player, HomeType.TWO);
            player.closeInventory();
        });
        inv.open(player);

        inv.setItem(3, home3, e -> {
            homeManager.tp(user, player, HomeType.THREE);
            player.closeInventory();
        });
        inv.open(player);
    }

    public static void openSetHome(final Player player) {
        final HomeManager homeManager = new HomeManager();
        final InventoryBuilder inv = new InventoryBuilder("Ustaw domy: " + player.getName(), InventoryType.HOPPER);
        final User user = UserManager.getUser(player);

        final ItemStack home1 = new ItemBuilder((homeManager.isHome(user.homeLocation1) ? Material.LIME_DYE : Material.RED_DYE)).setTitle("&7DOM I").addLore("", (homeManager.isHome(user.homeLocation1) ? "&cMasz już dom. Aby go usunąć &c/delhome" : "&aKliknij aby ustawić dom.")).setGlow(homeManager.isHome(user.homeLocation1)).build();
        final ItemStack home2 = new ItemBuilder((homeManager.isHome(user.homeLocation2) ? Material.LIME_DYE : Material.RED_DYE)).setTitle("&7DOM II").addLore("", (homeManager.isHome(user.homeLocation2) ? "&cMasz już dom. Aby go usunąć &c/delhome" : "&aKliknij aby ustawić dom.")).setGlow(homeManager.isHome(user.homeLocation2)).build();
        final ItemStack home3 = new ItemBuilder((homeManager.isHome(user.homeLocation3) ? Material.LIME_DYE : Material.RED_DYE)).setTitle("&7DOM III").addLore("", (homeManager.isHome(user.homeLocation3) ? "&cMasz już dom. Aby go usunąć &c/delhome" : "&aKliknij aby ustawić dom.")).setGlow(homeManager.isHome(user.homeLocation3)).build();


        inv.setItem(1, home1, e -> {
            homeManager.set(user ,player, HomeType.ONE);
            player.closeInventory();
        });
        inv.open(player);

        inv.setItem(2, home2, e -> {
            homeManager.set(user ,player, HomeType.TWO);
            player.closeInventory();
        });
        inv.open(player);

        inv.setItem(3, home3, e -> {
            homeManager.set(user ,player, HomeType.THREE);
            player.closeInventory();
        });
        inv.open(player);
    }

    public static void openDeleteHome(final Player player) {
        final HomeManager homeManager = new HomeManager();
        final InventoryBuilder inv = new InventoryBuilder("Usuń domy: " + player.getName(), InventoryType.HOPPER);
        final User user = UserManager.getUser(player);

        final ItemStack home1 = new ItemBuilder((homeManager.isHome(user.homeLocation1) ? Material.RED_DYE : Material.LIME_DYE)).setTitle("&7DOM I").addLore("", (homeManager.isHome(user.homeLocation1) ? "&cKliknij aby usunąć dom" : "&aNie masz ustawionego domu.")).setGlow(homeManager.isHome(user.homeLocation1)).build();
        final ItemStack home2 = new ItemBuilder((homeManager.isHome(user.homeLocation2) ? Material.RED_DYE : Material.LIME_DYE)).setTitle("&7DOM II").addLore("", (homeManager.isHome(user.homeLocation2) ? "&cKliknij aby usunąć dom" : "&aNie masz ustawionego domu.")).setGlow(homeManager.isHome(user.homeLocation2)).build();
        final ItemStack home3 = new ItemBuilder((homeManager.isHome(user.homeLocation3) ? Material.RED_DYE : Material.LIME_DYE)).setTitle("&7DOM III").addLore("", (homeManager.isHome(user.homeLocation3) ? "&cKliknij aby usunąć dom" : "&aNie masz ustawionego domu.")).setGlow(homeManager.isHome(user.homeLocation3)).build();


        inv.setItem(1, home1, e -> {
            homeManager.setHomeNull(user, HomeType.ONE);
            player.closeInventory();
            ChatUtil.success(player, "Usunięto dom");
            player.closeInventory();
        });
        inv.setItem(2, home2, e -> {
            homeManager.setHomeNull(user, HomeType.TWO);
            player.closeInventory();
            ChatUtil.success(player, "Usunięto dom");
            player.closeInventory();
        });
        inv.setItem(3, home3, e -> {
            homeManager.setHomeNull(user, HomeType.THREE);
            player.closeInventory();
            ChatUtil.success(player, "Usunięto dom");
            player.closeInventory();
        });
        inv.open(player);
    }
}
