package pl.chudziudgi.core.feature.home;

import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.guild.Guild;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import panda.std.Option;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.InventoryBuilder;
import pl.chudziudgi.core.api.ItemBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.teleport.TeleportManager;
import pl.chudziudgi.core.util.ChatUtil;

public class HomeGui {


    public static void openMain(final Player player, TeleportManager teleportManager, final ChCore core) {
        final InventoryBuilder inv = new InventoryBuilder("&9Co chcesz zrobić? (⊙_⊙)", InventoryType.HOPPER);

        FunnyGuilds funnyGuilds = core.getFunnyGuilds();
        net.dzikoysk.funnyguilds.user.UserManager userManager = funnyGuilds.getUserManager();

        Option<net.dzikoysk.funnyguilds.user.User> userOption = userManager.findByPlayer(player);
        if (!userOption.isPresent()) return;

        net.dzikoysk.funnyguilds.user.User user = userOption.get();
        Option<Guild> guildOption = user.getGuild();

        final ItemStack home = new ItemBuilder(Material.CHEST)
                .setTitle("&7Twoje domy")
                .addLore(
                        "",
                        "&7Kliknij &3▜&7▛, aby otworzyć",
                        ""
                )
                .build();

        final ItemStack klan = new ItemBuilder(guildOption.isPresent() ? Material.ENDER_CHEST : Material.STRUCTURE_VOID)
                .setTitle("&7Dom klanu")
                .addLore(
                        "",
                        guildOption.isPresent() ? "&7Kliknij &3▜&7▛, aby się teleportować." : "&cDołącz do klanu, aby korzystać z tej opcji.",
                        ""
                )
                .build();

        inv.setItem(1, home, e -> {
            openHome(player, teleportManager,  core);
        });

        inv.setItem(3, klan, e -> {
            Option<Location> guildHomeOption = guildOption.isPresent() ? guildOption.get().getHome() : Option.none();
            if (guildOption.isPresent()) {
                teleportManager.startTeleportation(player, guildHomeOption.get(), core);
                player.closeInventory();
            }
        });
        inv.open(player);
    }


    public static void openHome(final Player player, TeleportManager teleportManager, final ChCore plugin) {
        final HomeManager homeManager = new HomeManager();
        final InventoryBuilder inv = new InventoryBuilder("&9Lista domów " + player.getName(), InventoryType.HOPPER);
        final User user = UserManager.get(player);

        final ItemStack home1 = new ItemBuilder((homeManager.isHome(user.homeLocation1) ? Material.LIGHT_BLUE_DYE : Material.GRAY_DYE)).setTitle("&7DOM I").addLore("", (homeManager.isHome(user.homeLocation1) ? "&7Kliknij &3▜&7▛, aby teleportować się" : "&cNie masz ustawionego domu. /dom ustaw")).setGlow(homeManager.isHome(user.homeLocation1)).build();
        final ItemStack home2 = new ItemBuilder((homeManager.isHome(user.homeLocation2) ? Material.LIGHT_BLUE_DYE : Material.GRAY_DYE)).setTitle("&7DOM II").addLore("", (player.hasPermission("core.home.iron") ? (homeManager.isHome(user.homeLocation2) ? "&7Kliknij &3▜&7▛, aby teleportować się" : "&cNie masz ustawionego domu. /dom ustaw") : "&cNie posiadasz rangi &f&lIRON")).setGlow(homeManager.isHome(user.homeLocation2)).build();
        final ItemStack home3 = new ItemBuilder((homeManager.isHome(user.homeLocation3) ? Material.LIGHT_BLUE_DYE : Material.GRAY_DYE)).setTitle("&7DOM III").addLore("", (player.hasPermission("core.home.gold") ? (homeManager.isHome(user.homeLocation3) ? "&7Kliknij &3▜&7▛, aby teleportować się" : "&cNie masz ustawionego domu. /dom ustaw") : "&cNie posiadasz rangi &e&lGOLD")).setGlow(homeManager.isHome(user.homeLocation3)).build();


        inv.setItem(1, home1, e -> {
            homeManager.tp(user, player, HomeType.ONE, teleportManager, plugin);
            player.closeInventory();
        });
        inv.open(player);

        inv.setItem(2, home2, e -> {
            if (!player.hasPermission("core.home.iron")) return;
            homeManager.tp(user, player, HomeType.TWO, teleportManager, plugin);
            player.closeInventory();
        });
        inv.open(player);

        inv.setItem(3, home3, e -> {
            if (!player.hasPermission("core.home.gold")) return;
            homeManager.tp(user, player, HomeType.THREE, teleportManager, plugin);
            player.closeInventory();
        });
        inv.open(player);
    }

    public static void openSetHome(final Player player) {
        final HomeManager homeManager = new HomeManager();
        final InventoryBuilder inv = new InventoryBuilder("&9Ustawianie domów: " + player.getName(), InventoryType.HOPPER);
        final User user = UserManager.get(player);

        final ItemStack home1 = new ItemBuilder((homeManager.isHome(user.homeLocation1) ? Material.LIGHT_BLUE_DYE : Material.GRAY_DYE)).setTitle("&7DOM I").addLore("", (homeManager.isHome(user.homeLocation1) ? "&cMasz już dom. Aby go usunąć &c/sethome" : "&7Kliknij &3▜&7▛, aby ustawić dom")).setGlow(homeManager.isHome(user.homeLocation1)).build();
        final ItemStack home2 = new ItemBuilder((homeManager.isHome(user.homeLocation2) ? Material.LIGHT_BLUE_DYE : Material.GRAY_DYE)).setTitle("&7DOM II").addLore("", (player.hasPermission("core.home.iron") ? (homeManager.isHome(user.homeLocation2) ? "&cMasz już dom. Aby go usunąć &c/sethome" : "&7Kliknij &3▜&7▛, aby ustawić dom") : "&cNie posiadasz rangi &f&lIRON")).setGlow(homeManager.isHome(user.homeLocation2)).build();
        final ItemStack home3 = new ItemBuilder((homeManager.isHome(user.homeLocation3) ? Material.LIGHT_BLUE_DYE : Material.GRAY_DYE)).setTitle("&7DOM III").addLore("", (player.hasPermission("core.home.gold") ? (homeManager.isHome(user.homeLocation3) ? "&cMasz już dom. Aby go usunąć &c/sethome" : "&7Kliknij &3▜&7▛, aby ustawić dom") : "&cNie posiadasz rangi &e&lGOLD")).setGlow(homeManager.isHome(user.homeLocation3)).build();

        inv.setItem(1, home1, e -> {
            homeManager.set(user, player, HomeType.ONE);
            player.closeInventory();
        });
        inv.open(player);

        inv.setItem(2, home2, e -> {
            homeManager.set(user, player, HomeType.TWO);
            player.closeInventory();
        });
        inv.open(player);

        inv.setItem(3, home3, e -> {
            homeManager.set(user, player, HomeType.THREE);
            player.closeInventory();
        });
        inv.open(player);
    }

    public static void openDeleteHome(final Player player) {
        final HomeManager homeManager = new HomeManager();
        final InventoryBuilder inv = new InventoryBuilder("&9Usuwanie domów: " + player.getName(), InventoryType.HOPPER);
        final User user = UserManager.get(player);

        final ItemStack home1 = new ItemBuilder((homeManager.isHome(user.homeLocation1) ? Material.LIGHT_BLUE_DYE : Material.GRAY_DYE)).setTitle("&7DOM I").addLore("", (homeManager.isHome(user.homeLocation1) ? "&7Kliknij &3▜&7▛, aby usunąć dom" : "&CNie masz ustawionego domu.")).setGlow(homeManager.isHome(user.homeLocation1)).build();
        final ItemStack home2 = new ItemBuilder((homeManager.isHome(user.homeLocation2) ? Material.LIGHT_BLUE_DYE : Material.GRAY_DYE)).setTitle("&7DOM II").addLore("", (homeManager.isHome(user.homeLocation2) ? "&7Kliknij &3▜&7▛, aby usunąć dom" : "&cNie masz ustawionego domu.")).setGlow(homeManager.isHome(user.homeLocation2)).build();
        final ItemStack home3 = new ItemBuilder((homeManager.isHome(user.homeLocation3) ? Material.LIGHT_BLUE_DYE : Material.GRAY_DYE)).setTitle("&7DOM III").addLore("", (homeManager.isHome(user.homeLocation3) ? "&7Kliknij &3▜&7▛, aby usunąć dom" : "&cNie masz ustawionego domu.")).setGlow(homeManager.isHome(user.homeLocation3)).build();


        inv.setItem(1, home1, e -> {
            homeManager.setHomeNull(user, HomeType.ONE);
            player.closeInventory();
            ChatUtil.sendTitle(player, "", "&7Dom został &cusunięty&7.", 10,20,10);
            player.closeInventory();
        });
        inv.setItem(2, home2, e -> {
            homeManager.setHomeNull(user, HomeType.TWO);
            player.closeInventory();
            ChatUtil.sendTitle(player, "", "&7Dom został &cusunięty&7.", 10,20,10);
            player.closeInventory();
        });
        inv.setItem(3, home3, e -> {
            homeManager.setHomeNull(user, HomeType.THREE);
            player.closeInventory();
            ChatUtil.sendTitle(player, "", "&7Dom został &cusunięty&7.", 10,20,10);
            player.closeInventory();
        });
        inv.open(player);
    }
}
