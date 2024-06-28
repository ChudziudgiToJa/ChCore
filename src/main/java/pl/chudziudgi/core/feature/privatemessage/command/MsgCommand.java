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
import pl.chudziudgi.core.feature.vanish.VanishManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.UUID;

@CommandInfo(name = "msg", usage = "<gracz> <wiadomosc>", player = true)

public class MsgCommand extends PluginCommand {

    private final PrivateMessageManager privateMessageManager;
    private final ChatManager chatManager;
    private final VanishManager vanishManager;

    public MsgCommand(PrivateMessageManager privateMessageManager, ChatManager chatManager, VanishManager vanishManager) {
        this.privateMessageManager = privateMessageManager;
        this.chatManager = chatManager;
        this.vanishManager = vanishManager;
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

        if (!player.hasPermission("core.chat.admin") && chatManager.canUseChat(player)) {
            ChatUtil.error(player, "Nie odblokowałeś jeszcze chatu! &8(wykop 100 kamienia) &8pozostało " + (100 - UserManager.get(player).minedStone));
            return;
        }

        if (other == null || vanishManager.isVanished(other)) {
            ChatUtil.error(player, "Poprzednia osoba z, którą chatowałeś jest niedostępna");
            return;
        }

        if (privateMessageManager.ignore(player, other)) {
            ChatUtil.error(player, "Poprzednia osoba z, którą chatowałeś jest niedostępna");
            return;
        }

        String message = StringUtils.join(args, " ", 1, args.length);

        privateMessageManager.sendDirectMessage(player, other, message, true);
        privateMessageManager.sendDirectMessage(other, player, message, false);

        String socialSpyMessage = ChatUtil.fixColor(new MessageBuilder().setText("&c⚠ &3&lDM &8&l┃ &7{SENDER} &8➡ &3{RECIPIENT}&8: &7{MESSAGE}").addField("{SENDER}", player.getName()).addField("{RECIPIENT}", other.getName()).addField("{MESSAGE}", message).build());

        Bukkit.getOnlinePlayers().forEach(all -> {
            if (UserManager.get(all).socialSpyStatus) {
                all.sendMessage(ChatUtil.fixColor(socialSpyMessage));
            }
        });


        lastMessageCache.put(player.getUniqueId(), other.getUniqueId());
        lastMessageCache.put(other.getUniqueId(), player.getUniqueId());

        ChatUtil.sendTitle(other, "", "&3✉", 5, 40, 5);
        other.playSound(other, Sound.BLOCK_BELL_USE, 5, 5);
    }
}