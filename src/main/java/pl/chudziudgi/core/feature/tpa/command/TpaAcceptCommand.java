package pl.chudziudgi.core.feature.tpa.command;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.ChCore;
import pl.chudziudgi.core.api.MessageBuilder;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.feature.tpa.TpaManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.UUID;

@CommandInfo(
        name = "tpaaccept",
        player = true,
        perm = "core.command.tpaccept",
        usage = "<gracz> <*/akceptuje wszystkie prośby>"
)

public class TpaAcceptCommand extends PluginCommand {

    private final TpaManager tpaManager;
    private final ChCore plugin;

    public TpaAcceptCommand(TpaManager tpaManager, ChCore plugin) {
        this.tpaManager = tpaManager;
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length != 1) {
            sendUsage(player);
            return;
        }

        if ("*".equals(args[0])) {
            tpaManager.acceptAllTpaRequests(player.getUniqueId(), plugin);
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            ChatUtil.error(player, "Gracz nie jest online.");
            return;
        }

        if (player.getUniqueId().equals(target.getUniqueId())) {
            ChatUtil.error(player, "Nie możesz akceptować prośby sam od siebie.");
            return;
        }

        UUID senderId = target.getUniqueId();
        UUID receiverId = player.getUniqueId();

        UUID requestedReceiverId = tpaManager.getTpaRequest(senderId);

        if (requestedReceiverId == null || !requestedReceiverId.equals(receiverId)) {
            ChatUtil.error(player, "Nie masz żadnej prośby o teleportację od tego gracza.");
            return;
        }

        tpaManager.acceptTpaRequest(receiverId, plugin);
        player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10,10);
        ChatUtil.success(player, "Zaakceptowałeś prośbę o teleportację od " + target.getName() + ".");
        ChatUtil.success(target, player.getName() + " zaakceptował twoją prośbę o teleportację.");
    }
}