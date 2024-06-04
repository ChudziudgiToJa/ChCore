package pl.chudziudgi.core.feature.shop.answer;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.customitem.CustomItemStack;
import pl.chudziudgi.core.util.ChatUtil;

public class AnswerManager {

    public static boolean isHave(Player player) {
        User user = UserManager.get(player);
        return user.answerCandle > 0;
    }

    public static void giveCandle(Player player) {
        User user = UserManager.get(player);
        if (!isHave(player)) {
            ChatUtil.error(player, "Nie posiadasz przedmiotu do odebrania.");
            return;
        }


        final ItemStack item = CustomItemStack.magicCandle();
        item.setAmount(user.answerCandle);
        user.answerCandle = 0;
        player.getInventory().addItem(item);
    }
}
