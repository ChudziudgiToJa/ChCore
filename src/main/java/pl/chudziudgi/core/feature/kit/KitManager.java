package pl.chudziudgi.core.feature.kit;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.deposit.DepositUtil;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.DataUtils;

import java.util.HashMap;

public class KitManager {

    public void checkCanGet(Player player, Kit kit) {
        long currentTime = System.currentTimeMillis();
        User user = UserManager.get(player);
        HashMap<String, Long> kitList = user.kitList;

        Long kitTime = kitList.get(kit.getName());

        if (!player.hasPermission(kit.getPermission())) {
            ChatUtil.sendTitle(player, "", "&cNie posiadasz wymaganej rangi", 10, 30, 10);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 5, 5);
            player.closeInventory();
            return;
        }

        if (kitTime == null) {
            kitList.put(kit.getName(), currentTime + kit.getHour() * 3600000L);
            giveKit(player, kit);
            return;
        }

        if (currentTime >= kitTime) {
            kitList.put(kit.getName(), currentTime + kit.getHour() * 3600000L);
            giveKit(player, kit);
        } else {
            String remainingTimeString = DataUtils.durationToString(kitTime);
            ChatUtil.sendTitle(player, "", "&cMożesz odebrać za: &n" + remainingTimeString, 10, 30, 10);
            player.playSound(player.getLocation(), Sound.BLOCK_VINE_HIT, 5, 5);
            player.closeInventory();
        }
    }


    public void giveKit(Player player, Kit kit) {
        ItemStack[] itemsArray = new ItemStack[kit.getItemStack().size()];
        itemsArray = kit.getItemStack().toArray(itemsArray);
        DepositUtil.giveItems(player, itemsArray);

        player.closeInventory();
        ChatUtil.sendTitle(player, "", "&7Odebrano zestaw: &3&l" + kit.getName(), 10, 30, 10);
        player.playSound(player, Sound.ENTITY_VILLAGER_TRADE, 5, 5);
    }
}
