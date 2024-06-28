package pl.chudziudgi.core.feature.kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitManager {

    public static void giveKitFree(Player player, KitType kitType, KitStandard kitStandard) {
        List<ItemStack> ironKit = null;
        List<ItemStack> goldKit = null;
        switch (kitStandard) {
            case IRON -> ironKit = Kits.ironIron();
            case DIAMOND -> ironKit = Kits.ironDiamond();
            case NETHERITE -> ironKit = Kits.ironNetherite();
        }

        switch (kitStandard) {
            case IRON -> goldKit = Kits.goldIron();
            case DIAMOND -> goldKit = Kits.goldDiamoend();
            case NETHERITE -> goldKit = Kits.goldNetherite();
        }

        switch (kitType) {
            case START:
                addItemsToPlayer(player, Kits.start());
                break;
            case IRON:
                addItemsToPlayer(player, ironKit);
                break;
            case GOLD:
                addItemsToPlayer(player, goldKit);
                break;
        }
    }

    public static void giveKit(Player player, KitType kitType, KitStandard kitStandard) {
        User user = UserManager.get(player);
        Instant now = Instant.now();

        List<ItemStack> ironKit = null;
        List<ItemStack> goldKit = null;
        switch (kitStandard) {
            case IRON -> ironKit = Kits.ironIron();
            case DIAMOND -> ironKit = Kits.ironDiamond();
            case NETHERITE -> ironKit = Kits.ironNetherite();
        }

        switch (kitStandard) {
            case IRON -> goldKit = Kits.goldIron();
            case DIAMOND -> goldKit = Kits.goldDiamoend();
            case NETHERITE -> goldKit = Kits.goldNetherite();
        }

        switch (kitType) {
            case START:
                if (player.hasPermission("core.kit.admin")) {
                    addItemsToPlayer(player, Kits.start());
                    return;
                }
                if (!player.hasPermission("core.kit.start")) {
                    ChatUtil.error(player, "Nie posiadasz wymaganej rangi.");
                    return;
                }
                if (canReceiveKit(user.kitStart, now, 1)) {
                    user.kitStart = now;
                    addItemsToPlayer(player, Kits.start());
                } else {
                    ChatUtil.sendTitle(player, "","&7Zestaw możesz odbierać co 1h.",10,20,10);
                    player.closeInventory();
                }
                break;
            case IRON:
                if (player.hasPermission("core.kit.admin")) {
                    addItemsToPlayer(player, ironKit);
                    return;
                }
                if (!player.hasPermission("core.kit.iron")) {
                    ChatUtil.error(player, "Nie posiadasz wymaganej rangi &f&lIRON&7.");
                    return;
                }
                if (canReceiveKit(user.kitIron, now, 24)) {
                    user.kitIron = now;
                    addItemsToPlayer(player, ironKit);
                } else {
                    ChatUtil.sendTitle(player, "","&7Zestaw możesz odbierać co 24h.",10,20,10);
                    player.closeInventory();
                }
                break;
            case GOLD:
                if (player.hasPermission("core.kit.admin")) {
                    addItemsToPlayer(player, goldKit);
                    return;
                }
                if (!player.hasPermission("core.kit.gold")) {
                    ChatUtil.error(player, "Nie posiadasz wymaganej rangi &e&lGOLD&7.");
                    return;
                }
                if (canReceiveKit(user.kitGold, now, 24)) {
                    user.kitGold = now;
                    addItemsToPlayer(player, goldKit);
                } else {
                    ChatUtil.sendTitle(player, "","&7Zestaw możesz odbierać co 24h.",10,20,10);
                    player.closeInventory();
                }
                break;
        }
    }

    public static boolean canReceiveKit(Instant before, Instant after, int czas) {
        if (before == null) return true;
        Duration duration = Duration.between(before, after);
        return duration.toHours() >= czas;
    }

    public static boolean canReceiveKit(Player player, KitType kitType) {
        Instant now = Instant.now();
        User user = UserManager.get(player);

        if (getUserKitTime(user, kitType) == null) return true;

        Duration duration = Duration.between(getUserKitTime(user, kitType), now);
        return duration.toHours() >= 24;
    }

    public static List<ItemStack> getList(KitType kitType, KitStandard kitStandard) {
        List<ItemStack> ironKit = null;
        List<ItemStack> goldKit = null;
        switch (kitStandard) {
            case IRON -> ironKit = Kits.ironIron();
            case DIAMOND -> ironKit = Kits.ironDiamond();
            case NETHERITE -> ironKit = Kits.ironNetherite();
        }

        switch (kitStandard) {
            case IRON -> goldKit = Kits.goldIron();
            case DIAMOND -> goldKit = Kits.goldDiamoend();
            case NETHERITE -> goldKit = Kits.goldNetherite();
        }

        return switch (kitType) {
            case START -> Kits.start();
            case IRON -> ironKit;
            case GOLD -> goldKit;
        };
    }

    public static String getName(KitType kitType) {
        return switch (kitType) {
            case START -> "start";
            case IRON -> "iron";
            case GOLD -> "gold";
        };
    }

    public static Instant getUserKitTime(User user, KitType kitType) {
        return switch (kitType) {
            case START -> user.kitStart;
            case IRON -> user.kitIron;
            case GOLD -> user.kitGold;
        };
    }

    public static void addItemsToPlayer(final Player player, final List<ItemStack> items) {
        final HashMap<Integer, ItemStack> notStored = player.getInventory().addItem(items.toArray(new ItemStack[0]));
        for (final Map.Entry<Integer, ItemStack> en : notStored.entrySet()) {
            player.getWorld().dropItemNaturally(player.getLocation(), en.getValue());
        }
    }
}
