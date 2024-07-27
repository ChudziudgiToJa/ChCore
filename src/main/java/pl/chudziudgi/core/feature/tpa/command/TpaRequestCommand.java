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
import pl.chudziudgi.core.feature.teleport.TeleportManager;
import pl.chudziudgi.core.feature.tpa.TpaManager;
import pl.chudziudgi.core.feature.vanish.VanishManager;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.UUID;

@CommandInfo(
        name = "tpa",
        player = true,
        perm = "core.command.tpa",
        usage = "<gracz>"
)

public class TpaRequestCommand extends PluginCommand {

    private final TpaManager tpaManager;
    private final ChCore plugin;
    private final VanishManager vanishManager;

    public TpaRequestCommand(final TpaManager tpaManager, final VanishManager vanishManager, final ChCore plugin) {
        this.tpaManager = tpaManager;
        this.plugin = plugin;
        this.vanishManager = vanishManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (args.length != 1) {
            sendUsage(player);
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline() || vanishManager.isVanished(target)) {
            ChatUtil.error(player, "Gracz nie jest online.");
            return;
        }

        if (player.getUniqueId().equals(target.getUniqueId())) {
            ChatUtil.error(player, "Nie możesz wysłać prośby sam do siebie.");
            return;
        }

        if (TeleportManager.teleportTaskHashMap.containsKey(target.getUniqueId())) {
            ChatUtil.error(player, "Jesteś już podczas teleportacji nie możesz wysyłać nowych próśb.");
            return;
        }

        UUID senderId = player.getUniqueId();
        UUID receiverId = target.getUniqueId();

        if (tpaManager.getTpaRequest(senderId) != null) {
            ChatUtil.error(player, "Posiadasz już aktywną prośbę do &3" + target.getName());
            return;
        }

        TextComponent messageComponent = new TextComponent();
        messageComponent.setText(ChatUtil.fixColor(new MessageBuilder().setText("&8[&d&l!&8] &7Otrzymałeś prośbę o &b&nteleportację&7 od &3" + sender.getName())
                .addField("{SENDER}", sender.getName())
                .addField("{RECIPIENT}", target.getName())
                .build()));

        messageComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaaccept " + sender.getName()));
        messageComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatUtil.fixColor("&8[&d&l!&8] &7Kliknij aby zaakceptować teleportacje\n&8[&d&l!&8] &7prośba wygasa po 30 sekundach od otrzymania")).create()));

        target.spigot().sendMessage(messageComponent);

        tpaManager.sendTpaRequest(senderId, receiverId, this.plugin);
        ChatUtil.success(player, "Wysłano prośbę o teleportację do &3" + target.getName());

        target.playSound(target, Sound.BLOCK_NOTE_BLOCK_BELL, 10, 10);
        ChatUtil.sendTitle(target, "", "&3☣", 5, 30, 5);
    }
}