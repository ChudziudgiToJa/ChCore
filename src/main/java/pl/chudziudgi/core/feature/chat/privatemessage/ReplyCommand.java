package pl.chudziudgi.core.feature.chat.privatemessage;

import com.google.common.cache.Cache;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.database.User;
import pl.chudziudgi.core.database.UserManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.UUID;

@CommandInfo(name = "reply", usage = "<wiadomosc>", player = true, aliases = {"r"})
public class ReplyCommand extends PluginCommand {

    private final PrivateMessageManager privateMessageManager;

    public ReplyCommand(PrivateMessageManager privateMessageManager) {
        this.privateMessageManager = privateMessageManager;
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

    }
}