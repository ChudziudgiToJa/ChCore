package pl.chudziudgi.core.feature.chat.privatemessage;

import com.google.common.cache.Cache;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.UUID;

@CommandInfo(name = "msg", usage = "<gracz> <wiadomosc>", player = true)

public class MsgCommand extends PluginCommand {

    private final PrivateMessageManager privateMessageManager;

    public MsgCommand(PrivateMessageManager privateMessageManager) {
        this.privateMessageManager = privateMessageManager;
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sendUsage(sender);
            return;
        }

        Player player = (Player) sender;
        Player other = Bukkit.getPlayer(args[0]);
        final Cache<UUID, UUID> lastMessageCache = privateMessageManager.getLastMessageCache();

        if (other == player) {
            ChatUtil.error(player, "Nie możesz napisać sam do siebie.");
            return;
        }

        if (other == null) {
            ChatUtil.error(player, "Gracz jest offline");
            return;
        }

        String message = StringUtils.join(args, " ", 1, args.length);

        privateMessageManager.sendDirectMessage(player, other, message, true);
        privateMessageManager.sendDirectMessage(other, player, message, false);


        lastMessageCache.put(player.getUniqueId(), other.getUniqueId());
        lastMessageCache.put(other.getUniqueId(), player.getUniqueId());

    }
}