package pl.chudziudgi.core.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.feature.ochrona.ProtectionManager;

public class PlaceholderApiHook extends PlaceholderExpansion {

    private final ProtectionManager protectionManager;

    public PlaceholderApiHook(ProtectionManager protectionManager) {
        this.protectionManager = protectionManager;
    }

    @Override
    public @NotNull String getAuthor() {
        return "chudziudgi";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "klanmc";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }
    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        return protectionManager.placeholder(player, params);
    }

    public static void isPlaceholderAPIInstalled(final ChCore plugin) {
        Plugin placeholderAPI = Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI");
        if (placeholderAPI != null) {
            plugin.getLogger().info("Wykryto plugin PlaceholderAPI");
        } else {
            plugin.getLogger().info("Nie Wykryto pluginu PlaceholderAPi serwer zostaje wyłączony");
            plugin.getServer().shutdown();
        }
    }
}