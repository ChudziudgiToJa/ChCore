package pl.chudziudgi.core.hook;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import pl.chudziudgi.core.ChCore;

public class LuckyPermsHook {

    public static void isLuckyPermsInstalled(final ChCore plugin) {
        Plugin luckyPerm = Bukkit.getServer().getPluginManager().getPlugin("LuckyPerms");
        if (luckyPerm != null) {
            plugin.getLogger().info("Wykryto plugin LuckyPerms");
        } else {
            plugin.getLogger().info("Nie Wykryto pluginu LuckyPerms serwer zostaje wyłączony");
            plugin.getServer().shutdown();
        }
    }
}
