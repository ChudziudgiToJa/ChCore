package pl.chudziudgi.core.feature.shop.answer;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.customitem.CustomItemStack;
import pl.chudziudgi.core.feature.shop.time.TimeShopManager;
import pl.chudziudgi.core.util.ChatUtil;

public class AnswerManager {

    public static boolean isHaveCandle(User user) {
        return user.answerCandle > 0;
    }

    public static void giveCandle(User user) {
        if (!isHaveCandle(user)) {
            ChatUtil.error(user.getPlayer(), "Nie posiadasz przedmiotu do odebrania.");
            return;
        }

        final ItemStack item = CustomItemStack.magicCandle();
        item.setAmount(user.answerCandle);
        user.answerCandle = 0;
        user.getPlayer().getInventory().addItem(item);
    }


    private static void is(User user) {
        TimeShopManager.addRank(user.getPlayer(), "gold");
    }
}