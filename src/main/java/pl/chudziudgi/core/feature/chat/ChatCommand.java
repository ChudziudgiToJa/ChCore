package pl.chudziudgi.core.feature.chat;

import org.bukkit.command.CommandSender;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;
import pl.chudziudgi.core.util.ChatUtil;

@CommandInfo(name = "chat",
        player = true,
        usage = "clear/toggle/auto",
        perm = "core.command.chat.admin"
)
public class ChatCommand extends PluginCommand {

    private final ChatManager chatManager;
    private final ChatConfig chatConfig;

    public ChatCommand(ChatManager chatManager, ChatConfig chatConfig) {
        this.chatManager = chatManager;
        this.chatConfig = chatConfig;
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
            default:
                sendUsage(sender);
                break;
        }
    }
}