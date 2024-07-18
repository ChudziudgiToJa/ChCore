package pl.chudziudgi.core.feature.vanish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;

public class VanishManager {

    public boolean isVanished(final Player player) {
        User user = UserManager.get(player);
        return user.vanishStatus;
    }

    public void toggleVanish(final Player player, final boolean set) {
        User user = UserManager.get(player);
        if (set) {
            user.vanishStatus = true;
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (!onlinePlayer.hasPermission("core.vanish.see")) {
                    onlinePlayer.hidePlayer(player);
                }
            }
        } else {
            user.vanishStatus = false;
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.showPlayer(player);
            }

        }
    }

    public void toggleVanish(final Player player) {
        toggleVanish(player, !isVanished(player));
    }

    public void onJoin(Player player) {
        if (!player.hasPermission("core.vanish.see")) {
            Bukkit.getOnlinePlayers().forEach(online -> {
                if (!UserManager.isExists(player)) return;
                User user = UserManager.get(online);
                if (user == null) return;
                if (user.vanishStatus) {
                    player.hidePlayer(online);
                }
            });
        }
    }
}