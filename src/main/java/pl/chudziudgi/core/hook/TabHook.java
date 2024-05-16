package pl.chudziudgi.core.hook;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import pl.chudziudgi.core.ChCore;

public class TabHook {
    public static void isTabInstalled(final ChCore plugin) {
        Plugin tab = Bukkit.getServer().getPluginManager().getPlugin("TAB");
        if (tab != null) {
            plugin.getLogger().info("Wykryto plugin TAB");
        } else {
            plugin.getLogger().info("Nie Wykryto pluginu TAB serwer zostaje wyłączony");
            plugin.getServer().shutdown();
        }
    }
}
