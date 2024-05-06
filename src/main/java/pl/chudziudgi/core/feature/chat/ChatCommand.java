package pl.chudziudgi.core.feature.chat;

import org.bukkit.command.CommandSender;
import pl.chudziudgi.core.api.command.PluginCommand;
import pl.chudziudgi.core.api.command.interfaces.CommandInfo;

@CommandInfo(name = "chat",
        player = true
)
public class ChatCommand extends PluginCommand {

    private final ChatManager chatManager;

    public ChatCommand(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args[0].equalsIgnoreCase("switch")) {
            chatManager.switchChat(sender);
            return;
        }
        if (args[0].equalsIgnoreCase("clear")) {
            chatManager.clearChat(sender);
        }

    }
}