package pl.chudziudgi.core.feature.kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitManager {

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
                if (canReceiveKit(user.kitIron, now)) {
                    user.kitIron = now;
                    addItemsToPlayer(player, Kits.iron());
                } else {
                    ChatUtil.error(player, "Zestaw możesz odbierać co 24h.");
                    player.closeInventory();
                }
                break;
            case GOLD:
                if (canReceiveKit(user.kitGold, now)) {
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

    public static List<ItemStack> getKitList(KitType kitType) {
        return switch (kitType) {
            case START -> Kits.start();
            case IRON -> Kits.iron();
            case GOLD -> Kits.gold();
        };
    }

    public static String getKitName(KitType kitType) {
        return switch (kitType) {
            case START -> "&3&lSTART";
            case IRON -> "&f&lIRON";
            case GOLD -> "&e&lGOLD";
        };
    }

    public static void addItemsToPlayer(final Player player, final List<ItemStack> items) {
        final HashMap<Integer, ItemStack> notStored = player.getInventory().addItem(items.toArray(new ItemStack[0]));
        for (final Map.Entry<Integer, ItemStack> en : notStored.entrySet()) {
            player.getWorld().dropItemNaturally(player.getLocation(), en.getValue());
        }
    }
}
