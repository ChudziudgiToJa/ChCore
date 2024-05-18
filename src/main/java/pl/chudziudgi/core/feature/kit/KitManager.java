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

    public static void giveKitFree(Player player, KitType kitType) {
        switch (kitType) {
            case START:
                addItemsToPlayer(player, Kits.start());
                break;
            case IRON:
                addItemsToPlayer(player, Kits.iron());
                break;
            case GOLD:
                addItemsToPlayer(player, Kits.gold());
                break;
        }
    }

    public static void giveKit(Player player, KitType kitType) {
        User user = UserManager.get(player);
        Instant now = Instant.now();

        switch (kitType) {
            case START:
                if (canReceiveKit(user.kitStart, now)) {
                    user.kitStart = now;
                    addItemsToPlayer(player, Kits.start());
                } else {
                    ChatUtil.error(player, "Zestaw możesz odbierać co 24h.");
                    player.closeInventory();
                }
                break;
            case IRON:
                if (!player.hasPermission("core.kit.iron")) {
                    ChatUtil.error(player, "Nie posiadasz wymaganej rangi.");
                    return;
                }
                if (canReceiveKit(user.kitIron, now)) {
                    user.kitIron = now;
                    addItemsToPlayer(player, Kits.iron());
                } else {
                    ChatUtil.error(player, "Zestaw możesz odbierać co 24h.");
                    player.closeInventory();
                }
                break;
            case GOLD:
                if (!player.hasPermission("core.kit.gold")) {
                    ChatUtil.error(player, "Nie posiadasz wymaganej rangi.");
                    return;
                }                if (canReceiveKit(user.kitGold, now)) {
                    user.kitGold = now;
                    addItemsToPlayer(player, Kits.gold());
                } else {
                    ChatUtil.error(player, "Zestaw możesz odbierać co 24h.");
                    player.closeInventory();
                }
                break;
        }
    }

    private static boolean canReceiveKit(Instant before, Instant after) {
        if (before == null) return true;
        Duration duration = Duration.between(before, after);
        return duration.toHours() >= 24;
    }

    public static boolean canReceiveKit(Player player, KitType kitType) {
        Instant now = Instant.now();
        User user = UserManager.get(player);

        if (getUserKitTime(user, kitType) == null) return true;

        Duration duration = Duration.between(getUserKitTime(user, kitType), now);
        return duration.toHours() >= 24;
    }

    public static List<ItemStack> getList(KitType kitType) {
        return switch (kitType) {
            case START -> Kits.start();
            case IRON -> Kits.iron();
            case GOLD -> Kits.gold();
        };
    }

    public static String getName(KitType kitType) {
        return switch (kitType) {
            case START -> "start";
            case IRON -> "iron";
            case GOLD -> "gold";
        };
    }

    public static Instant getUserKitTime(User user, KitType kitType){
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
