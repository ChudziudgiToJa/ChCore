package pl.chudziudgi.core.feature.shop.time;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.Node;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.concurrent.CompletableFuture;

public class TimeShopManager {

    public static void buyItem(Player player, TimeShopItem timeShopItem) {
        final TimeShopConfig config = new TimeShopConfig();

        User user = UserManager.get(player);
        switch (timeShopItem) {
            case GOLD: {
                if (user.timeShop < config.getGoldPrice()) {
                    ChatUtil.error(player, "Nie posiadasz wystarczającej ilości monet czasu.");
                    return;
                }

                if (user.timeShop >= config.getGoldPrice()) {
                    user.timeShop = user.timeShop - config.getGoldPrice();
                    ChatUtil.info(player, "Sukces udało ci sie zakupić range: &e&lGOLD");
                    addRank(player, "gold");
                    return;
                }
            }
            case IRON: {
                if (user.timeShop < config.getIronPrice()) {
                    ChatUtil.error(player, "Nie posiadasz wystarczającej ilości monet czasu.");
                    return;
                }

                if (user.timeShop >= config.getIronPrice()) {
                    user.timeShop = user.timeShop - config.getIronPrice();
                    ChatUtil.info(player, "Sukces udało ci sie zakupić range: &f&lIRON");
                    addRank(player, "iron");
                }
            }
        }
    }

    public static boolean canBuy(Player player, TimeShopItem timeShopItem) {
        final TimeShopConfig config = new TimeShopConfig();
        User user = UserManager.get(player);

        switch (timeShopItem) {
            case IRON -> {
                return user.timeShop >= config.getIronPrice();
            }
            case GOLD -> {
                return user.timeShop >= config.getGoldPrice();
            }
            default -> {
                return false;
            }
        }
    }

    private static void addRank(Player player, String rank) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        net.luckperms.api.model.user.UserManager userManager = luckPerms.getUserManager();

        CompletableFuture<net.luckperms.api.model.user.User> userFuture = userManager.loadUser(player.getUniqueId());

        userFuture.thenAcceptAsync(user -> {
            if (user != null) {
                net.luckperms.api.node.Node node = Node.builder("group." + rank).build();
                user.data().add(node);
                userManager.saveUser(user);
            } else {
                ChatUtil.error(player, "Coś poszło nie tak skontaktuj się z administracją aby uzyskać pomoc &8(timeshop luckyperm error)");
            }
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }

}
