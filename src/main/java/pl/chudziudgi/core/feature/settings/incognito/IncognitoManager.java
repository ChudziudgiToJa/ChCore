package pl.chudziudgi.core.feature.settings.incognito;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.database.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

public class IncognitoManager {


    public void toggleInkognito(final Player player) {
        User user = UserManager.getUser(player);
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
}
