package pl.chudziudgi.core.feature.customitem.magiccandle;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class MagicCandleManager {

    public void toggle(Player player) {
        User user = UserManager.get(player);
        user.chatMagicCandleStatus = !user.chatMagicCandleStatus;
        ChatUtil.success(player, "Widoczność wiadomości o otworzeniu magicznej świecy: " + (user.chatMagicCandleStatus ? "&awlaczono" : "&cwylaczono"));
        player.closeInventory();
    }

    public void brodcast(Player target, String name) {
        Bukkit.getOnlinePlayers().forEach(player -> {
                    User user = UserManager.get(player);
                    if (user.chatMagicCandleStatus) {
                        ChatUtil.info(player, "&b" + target.getName() + "&7 otworzył &fMagiczną świece &e★ &7i wylosował: &b" + name);
                    }
                }
        );
    }
}
