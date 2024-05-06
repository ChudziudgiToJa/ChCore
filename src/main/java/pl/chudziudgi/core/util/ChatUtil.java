package pl.chudziudgi.core.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ChatUtil {

    public static String fixColor(final String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> fixColor(final List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, fixColor(strings.get(i)));
        }
        return strings;
    }

    public static void sendActionbar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(fixColor(message)));
    }

    public static void sendTitle(Player player, String title, String subTitle, int fadeInTime, int stayTime, int fadeOutTime) {
        player.sendTitle(fixColor(title), fixColor(subTitle), fadeInTime, stayTime, fadeOutTime);
    }


    public static void msg(final CommandSender commandSender, final String string) {
        commandSender.sendMessage(fixColor(string));
    }
    public static void success(final CommandSender commandSender, final String message) {
        msg(commandSender, "&8[&a&l!&8] &7" + message);
    }

    public static void info(final CommandSender commandSender, final String message) {
        msg(commandSender, "&8[&d&l!&8] &7" + message);
    }

    public static void warning(final CommandSender commandSender, final String message) {
        msg(commandSender, "&8[&6&l!&8] &7" + message);
    }

    public static void error(final CommandSender commandSender, final String error) {
        msg(commandSender, "&8[&4&l!&8] &7" + error);
    }

    public static void broadcast(final String message) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            msg(onlinePlayer, message);
        }
    }
}
