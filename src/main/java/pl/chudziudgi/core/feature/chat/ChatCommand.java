package pl.chudziudgi.core.feature.chat;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.database.user.UserManager;
import pl.chudziudgi.core.util.ChatUtil;
import pl.chudziudgi.core.util.TimeEnum;

@CommandInfo(name = "chat",
        player = true,
        usage = "clear/toggle/auto",
        perm = "core.command.chat.admin"
)
public class ChatCommand extends PluginCommand {

    private final ChatManager chatManager;

    public ChatCommand(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sendUsage(sender);
            return;
        }

        switch (args[0].toLowerCase()) {
            case "toggle":
                chatManager.switchChat(sender);
                break;
            case "clear":
                chatManager.clearChat(sender);
                break;
            case "auto":
                chatManager.switchAuto(sender);
                break;
            case "set":

                Player target = Bukkit.getPlayer(args[1]);
                if (target == null || !target.isOnline()) {
                    ChatUtil.error(sender, "Gracz nie jest online");
                    return;
                }

                try {
                    int amount = Integer.parseInt(args[1]);
                    UserManager.get((Player) sender).minedStone = amount;
                    ChatUtil.success(sender, "Ustawiono wykopane bloki na &3" + amount + " &7dla &3" + target.getName());
                } catch (NumberFormatException e) {
                    ChatUtil.error(sender, "Błędny format liczby.");
                }
                break;
            default:
                sendUsage(sender);
                break;
        }
    }
}