package pl.chudziudgi.core.feature.shop.time;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.concurrent.CompletableFuture;

public class TimeShopManager {

    public static void buyItem(Player player, TimeShop timeShop) {
        User user = UserManager .get(player);
        if (!(user.timeCoin >= timeShop.getPrice())) {
            ChatUtil.sendTitle(player, "","&cNie posiadasz tylu monet czasu!",20,40,20);
            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 10, 10);
            return;
        }

        String command = new MessageBuilder().setText(timeShop.getCommand())
                .addField("{PLAYER}", player.getName())
                .build();

        user.timeCoin = user.timeCoin - timeShop.getPrice();
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        player.playSound(player, Sound.ITEM_GOAT_HORN_SOUND_1, 10, 10);
        ChatUtil.sendTitle(player, "", "&7Zakupiono " + timeShop.getName(), 20,20,20);
    }

    public static void addRank(Player player, String rank) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        net.luckperms.api.model.user.UserManager userManager = luckPerms.getUserManager();

        CompletableFuture<net.luckperms.api.model.user.User> userFuture = userManager.loadUser(player.getUniqueId());

        userFuture.thenAcceptAsync(user -> {
            if (user != null) {
                net.luckperms.api.node.Node node = Node.builder("group." + rank).build();
                user.data().add(node);
                userManager.saveUser(user);
            } else {
                ChatUtil.error(player, "Coś poszło nie tak skontaktuj się z administracją aby uzyskać pomoc &8(timeshop rank error)");
            }
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }
    public void toggle(Player player) {
        User user = UserManager.get(player);
        user.timeMessage = !user.timeMessage;
        ChatUtil.success(player, "Widoczność wiadomości o otrzymaniu monety czasu: " + (user.timeMessage ? "&awlaczono" : "&cwylaczono"));
        player.closeInventory();
    }
}
