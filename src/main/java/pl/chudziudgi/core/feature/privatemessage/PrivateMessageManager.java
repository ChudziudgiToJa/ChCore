package pl.chudziudgi.core.feature.privatemessage;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.UUID;

public class PrivateMessageManager {

    private Cache<UUID, UUID> lastMessageCache;

    public Cache<UUID, UUID> getLastMessageCache() {
        return lastMessageCache;
    }

    public PrivateMessageManager() {
        lastMessageCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build();
    }

    public void sendDirectMessage(Player sender, Player recipient, String message, Boolean send) {
        TextComponent messageComponent = new TextComponent();
        String senderDisplayName = sender.getName();
        String recipientDisplayName = recipient.getName();


        String arrow = "⬅";
        if (send) {
            arrow = "➡";
        }

        messageComponent.setText(ChatUtil.fixColor(new MessageBuilder().setText("&3&lDM &8&l┃ &7{SENDER} &8{ARROW} &3{RECIPIENT}&8: &7{MESSAGE}")
                .addField("{SENDER}", senderDisplayName)
                .addField("{RECIPIENT}", recipientDisplayName)
                .addField("{ARROW}", arrow)
                .addField("{MESSAGE}", message)
                .build()));

        messageComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + recipientDisplayName + " "));
        messageComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatUtil.fixColor("&8[&d&l!&8] &7Kliknij aby szybko odpisać")).create()));
        sender.spigot().sendMessage(messageComponent);
    }

    public boolean ignore(Player player, Player target) {
        User one = UserManager.get(player);
        User two = UserManager.get(target);

        if (one.ignoreStatus || two.ignoreStatus) {
            return true;
        }

        if (one.ignoredList.contains(target.getUniqueId())) {
            return true;
        }

        return two.ignoredList.contains(player.getUniqueId());
    }

    public void toggle(Player player) {
        User user = UserManager.get(player);
        user.ignoreStatus = !user.ignoreStatus;
        ChatUtil.success(player, "Ignorowanie wszystkich wiadomości prywatnych " + (user.ignoreStatus ? "&awlaczono" : "&cwylaczono"));
        player.closeInventory();
    }
}