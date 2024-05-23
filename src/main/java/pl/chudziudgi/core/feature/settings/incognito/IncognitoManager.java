package pl.chudziudgi.core.feature.settings.incognito;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.net.MalformedURLException;
import java.net.URL;

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

//                PlayerProfile profile = player.getPlayerProfile();
//                PlayerTextures textures = profile.getTextures();
//                textures.clear();
//                profile.setTextures(textures);
//                profile.update();

                ChatUtil.success(player, "Incognito zostało &cwylaczone");
                player.closeInventory();
            } else {
//                setIncognitoSkin(player);
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
//                setIncognitoSkin(player);
                tabPlayer.setTemporaryGroup("incognito");
                player.setDisplayName("&kincognito");
                ChatUtil.info(player, "Twoje incognito jest nadal &aAktywne");
            }
        }
    }

    public void setIncognitoSkin(Player player) {
        try {
            URL skinUrl = new URL("http://textures.minecraft.net/texture/58058aea0b09af68b4205f96040ca9f5ab8bfd15bb34ced0cac40ec9610a42b");
            PlayerProfile profile = player.getPlayerProfile();
            PlayerTextures textures = profile.getTextures();

            textures.setSkin(skinUrl);

            profile.setTextures(textures);
            profile.update();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}