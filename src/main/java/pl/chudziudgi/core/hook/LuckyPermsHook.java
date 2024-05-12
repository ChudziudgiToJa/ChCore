package pl.chudziudgi.core.hook;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import pl.chudziudgi.core.ChCore;

public class LuckyPermsHook {

    public static void isLuckyPermsInstalled(final ChCore plugin) {
        Plugin placeholderAPI = Bukkit.getServer().getPluginManager().getPlugin("LuckyPerms");
        if (placeholderAPI != null) {
            plugin.getLogger().info("Wykryto plugin PlaceholderAPI");
        } else {
            plugin.getLogger().info("Nie Wykryto pluginu PlaceholderAPi serwer zostaje wyłączony");
            plugin.getServer().shutdown();
        }
    }
}
