package pl.chudziudgi.core.feature.privatemessage.command;

import com.google.common.cache.Cache;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.feature.chat.ChatManager;
import pl.chudziudgi.core.feature.privatemessage.PrivateMessageManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.UUID;

@CommandInfo(name = "reply", usage = "<wiadomosc>", player = true, aliases = {"r"})
public class ReplyCommand extends PluginCommand {

    private final PrivateMessageManager privateMessageManager;
    private final ChatManager chatManager;

    public ReplyCommand(PrivateMessageManager privateMessageManager, ChatManager chatManager) {
        this.privateMessageManager = privateMessageManager;
        this.chatManager = chatManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sendUsage(sender);
            return;
        }
        Player player = (Player) sender;
        final Cache<UUID, UUID> lastMessageCache = privateMessageManager.getLastMessageCache();
        UUID lastMessagedUUID = lastMessageCache.getIfPresent(player.getUniqueId());

        if (lastMessagedUUID == null) {
            ChatUtil.error(player, "Nie masz komu odpisać.");
            return;
        }


        if (!player.hasPermission("core.chat.admin") && chatManager.canUseChat(player)) {
            ChatUtil.error(player, "Nie odblokowałeś jeszcze chatu! &8(wykop 100 kamienia)");
            return;
        }

        Player lastMessaged = Bukkit.getPlayer(lastMessagedUUID);

        if (lastMessaged == null) {
            ChatUtil.error(player, "Poprzednia osoba z, którą chatowałeś jest niedostępna");
            return;
        }

        if (lastMessaged == player) {
            ChatUtil.error(player, "Nie możesz pisać sam do siebie.");
            return;
        }

        if (privateMessageManager.ignore(player, lastMessaged)) {
            ChatUtil.error(player, "Poprzednia osoba z, którą chatowałeś jest niedostępna");
            return;
        }
        String message = StringUtils.join(args, " ");

        privateMessageManager.sendDirectMessage(player, lastMessaged, message, true);
        privateMessageManager.sendDirectMessage(lastMessaged, player, message, false);

        String socialSpyMessage = ChatUtil.fixColor(new MessageBuilder().setText("&c⚠ &3&lDM &8&l┃ &7{SENDER} &8➡ &3{RECIPIENT}&8: &7{MESSAGE}").addField("{SENDER}", player.getName()).addField("{RECIPIENT}", lastMessaged.getName()).addField("{MESSAGE}", message).build());

        Bukkit.getOnlinePlayers().forEach(all -> {
            if (UserManager.get(all).socialSpyStatus) {
                all.sendMessage(socialSpyMessage);
            }
        });

        ChatUtil.sendTitle(lastMessaged, "", "&3✉", 5, 40, 5);
        lastMessaged.playSound(lastMessaged, Sound.BLOCK_AMETHYST_BLOCK_STEP, 5, 5);
    }
}