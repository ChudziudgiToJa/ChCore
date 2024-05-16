package pl.chudziudgi.core.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.expansion.Relational;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.protection.ProtectionManager;

public class PlaceholderApiHook extends PlaceholderExpansion implements Relational {

    private final ProtectionManager protectionManager;

    public PlaceholderApiHook(ProtectionManager protectionManager) {
        this.protectionManager = protectionManager;
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
    public String onPlaceholderRequest(Player one, Player two, String identifier) {
        String result = "";
        if (one == null || two == null) return null;
        if (identifier.equalsIgnoreCase("inkognito")) {
            if (two.getDisplayName().equals(one.getName())) return result;
            if (two.getName().equals(one.getName())) return result;
            if (one.hasPermission("core.incognito.see")) {
                result = new MessageBuilder()
                        .setText(" &8(&9{NAME}&8)")
                        .addField("{NAME}", two.getName())
                        .build();
            }
        }
        return result;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        String result = "";
        if (params.equalsIgnoreCase("ochrona")) {
            if (protectionManager.hasProtection(player)) {
                if (UserManager.get((Player) player).incognito) {
                    result = " &e\uD83D\uDEE1 ";
                    return result;
                }
                result = " &e\uD83D\uDEE1";
            }
        }
        if (params.equalsIgnoreCase("vanish")) {
            User user = UserManager.get((Player) player);
            if (user.vanishStatus) {
                if (user.incognito || protectionManager.hasProtection(player)) {
                    result = " &9☯ ";
                    return result;
                }
                result = " &9☯";
            }
        }
        return result;
    }
}