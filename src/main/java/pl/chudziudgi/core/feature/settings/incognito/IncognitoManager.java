package pl.chudziudgi.core.feature.settings.incognito;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class IncognitoManager {


    public void toggleInkognito(final Player player) {
        if (!UserManager.isExists(player)) return;
        User user = UserManager.get(player);
        TabPlayer tabPlayer = TabAPI.getInstance().getPlayer(player.getUniqueId());
        if (tabPlayer != null) {
            if (user.incognito) {
                user.incognito = false;
                player.setDisplayName(player.getName());
                tabPlayer.setTemporaryGroup("_DEFAULT_");
                ChatUtil.success(player, "Incognito zostało &cwylaczone");
                player.closeInventory();
            } else {
                player.setDisplayName("&kincognito");
                tabPlayer.setTemporaryGroup("incognito");
                user.incognito = true;
                ChatUtil.success(player, "Incognito zostało &awlaczone");
                player.closeInventory();
            }
        }
    }

    public void isIncognito(final Player player) {
        if (!UserManager.isExists(player)) return;
        User user = UserManager.get(player);
        if (user.incognito) {
            TabPlayer tabPlayer = TabAPI.getInstance().getPlayer(player.getUniqueId());
            if (tabPlayer != null) {
                tabPlayer.setTemporaryGroup("incognito");
                player.setDisplayName("&kincognito");
                ChatUtil.info(player, "Twoje incognito jest nadal &aAktywne");
            }
        }
    }
}
